package questao2;

import java.time.LocalDateTime;
import java.util.HashMap;

public class SistemaBancarioLegado implements ISistemaBancarioLegado {
    @Override
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        HashMap<String, Object> resp = new HashMap<>();
        Object valorObj = parametros.get("valor");
        double valor = valorObj instanceof Number ? ((Number) valorObj).doubleValue() : 0.0;
        boolean aprovado = valor <= 1000.0;
        resp.put("status", aprovado ? "APROVADO" : "NEGADO");
        resp.put("codigo", aprovado ? "00" : "51");
        resp.put("mensagem", aprovado ? "OK" : "Limite insuficiente");
        resp.put("valor", valor);
        resp.put("moeda_codigo", parametros.get("moeda_codigo"));
        resp.put("data_hora", LocalDateTime.now().toString());
        return resp;
    }
}
