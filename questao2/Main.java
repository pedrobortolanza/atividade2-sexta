package questao2;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ISistemaBancarioLegado legadoReal = new SistemaBancarioLegado();
        ProcessadorTransacoes p1 = new AdaptadorLegadoParaModerno(legadoReal, "ECOMMERCE");

        System.out.println(p1.autorizar("4111111111111111", 250.0, "BRL"));
        System.out.println(p1.autorizar("4111111111111111", 1250.0, "USD"));

        ProcessadorTransacoes motorModerno = new ProcessadorTransacoesBasico();
        ISistemaBancarioLegado adaptadoAoLegado = new AdaptadorModernoParaLegado(motorModerno);

        HashMap<String, Object> req = new HashMap<>();
        req.put("numero_cartao", "5555444433332222");
        req.put("valor", 400.0);
        req.put("moeda_codigo", ConversorMoeda.paraCodigo("EUR"));
        req.put("canal", "PDV");
        System.out.println(adaptadoAoLegado.processarTransacao(req));
    }
}
