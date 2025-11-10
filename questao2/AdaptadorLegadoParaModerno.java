package questao2;

import java.time.LocalDateTime;
import java.util.HashMap;

public class AdaptadorLegadoParaModerno implements ProcessadorTransacoes {
    private final ISistemaBancarioLegado legado;
    private final String canalObrigatorio;

    public AdaptadorLegadoParaModerno(ISistemaBancarioLegado legado, String canalObrigatorio) {
        this.legado = legado;
        this.canalObrigatorio = canalObrigatorio;
    }

    @Override
    public RespostaAutorizacao autorizar(String cartao, double valor, String moeda) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("numero_cartao", cartao);
        req.put("valor", valor);
        req.put("moeda_codigo", ConversorMoeda.paraCodigo(moeda));
        req.put("canal", canalObrigatorio);
        req.put("data_hora", LocalDateTime.now().toString());
        HashMap<String, Object> r = legado.processarTransacao(req);
        String status = String.valueOf(r.get("status"));
        boolean aprovado = "APROVADO".equalsIgnoreCase(status);
        String codigo = String.valueOf(r.get("codigo"));
        String mensagem = String.valueOf(r.get("mensagem"));
        int moedaCodigo = (int) r.get("moeda_codigo");
        String moedaStr = ConversorMoeda.deCodigo(moedaCodigo);
        double valorResp = ((Number) r.get("valor")).doubleValue();
        return new RespostaAutorizacao(aprovado, codigo, mensagem, moedaStr, valorResp);
    }
}
