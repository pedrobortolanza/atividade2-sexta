package questao2;

import java.util.HashMap;

public class AdaptadorModernoParaLegado implements ISistemaBancarioLegado {
    private final ProcessadorTransacoes processador;

    public AdaptadorModernoParaLegado(ProcessadorTransacoes processador) {
        this.processador = processador;
    }

    @Override
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        String cartao = String.valueOf(parametros.get("numero_cartao"));
        double valor = ((Number) parametros.get("valor")).doubleValue();
        int moedaCodigo = ((Number) parametros.get("moeda_codigo")).intValue();
        String moeda = ConversorMoeda.deCodigo(moedaCodigo);
        RespostaAutorizacao resp = processador.autorizar(cartao, valor, moeda);
        HashMap<String, Object> saida = new HashMap<>();
        saida.put("status", resp.aprovado ? "APROVADO" : "NEGADO");
        saida.put("codigo", resp.codigo);
        saida.put("mensagem", resp.mensagem);
        saida.put("valor", resp.valor);
        saida.put("moeda_codigo", ConversorMoeda.paraCodigo(resp.moeda));
        return saida;
    }
}
