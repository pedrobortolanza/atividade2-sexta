package questao2;

public interface ProcessadorTransacoes {
    RespostaAutorizacao autorizar(String cartao, double valor, String moeda);
}