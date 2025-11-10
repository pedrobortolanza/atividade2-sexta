package questao2;

public class ProcessadorTransacoesBasico implements ProcessadorTransacoes {
    @Override
    public RespostaAutorizacao autorizar(String cartao, double valor, String moeda) {
        boolean aprovado = valor <= 500.0;
        String codigo = aprovado ? "00" : "51";
        String mensagem = aprovado ? "OK" : "Negado pelo motor moderno";
        return new RespostaAutorizacao(aprovado, codigo, mensagem, moeda, valor);
    }
}
