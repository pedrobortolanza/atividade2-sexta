package questao4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CadeiaValidacao {
    private final List<Etapa> etapas = new ArrayList<>();
    private final int limiteFalhas;

    public CadeiaValidacao(int limiteFalhas) {
        this.limiteFalhas = limiteFalhas;
    }

    public CadeiaValidacao adicionar(Validador v, long timeoutMs) {
        etapas.add(new Etapa(v, timeoutMs));
        return this;
    }

    public ResultadoExecucao validar(NFe nfe, ContextoValidacao ctx) {
        int falhas = 0;
        boolean sucessoAteAqui = true;
        Queue<Validador> modificadores = new ArrayDeque<>();
        List<String> mensagens = new ArrayList<>();

        for (int i = 0; i < etapas.size(); i++) {
            Etapa e = etapas.get(i);
            String nome = e.validador.nome();
            long timeout = ctx.timeoutPara(nome, e.timeoutMs);

            if ((nome.equals("ValidadorRegrasFiscais") || nome.equals("ValidadorSefaz")) && !sucessoAteAqui) {
                mensagens.add(nome + ": pulado por dependência não satisfeita");
                continue;
            }

            try {
                Callable<ResultadoValidacao> tarefa = () -> e.validador.validar(nfe, ctx);
                Future<ResultadoValidacao> fut = ctx.executor.submit(tarefa);
                ResultadoValidacao r = fut.get(timeout, TimeUnit.MILLISECONDS);
                mensagens.add(nome + ": " + (r.sucesso ? "OK" : ("ERRO - " + r.mensagem)));
                if (!r.sucesso) {
                    falhas++;
                    sucessoAteAqui = false;
                } else if (r.modificouDocumento) {
                    modificadores.add(e.validador);
                }
            } catch (Exception ex) {
                mensagens.add(nome + ": ERRO - timeout ou exceção");
                falhas++;
                sucessoAteAqui = false;
            }

            if (falhas >= limiteFalhas) {
                mensagens.add("CircuitBreaker: interrompido após " + falhas + " falhas");
                break;
            }
        }

        if (!sucessoAteAqui) {
            while (!modificadores.isEmpty()) {
                Validador v = modificadores.poll();
                v.rollback(nfe, ctx);
            }
        }

        boolean sucessoGlobal = falhas == 0 && sucessoAteAqui;
        return new ResultadoExecucao(sucessoGlobal, mensagens);
    }
}
