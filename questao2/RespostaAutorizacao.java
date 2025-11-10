package questao2;

public class RespostaAutorizacao {
    public final boolean aprovado;
    public final String codigo;
    public final String mensagem;
    public final String moeda;
    public final double valor;

    public RespostaAutorizacao(boolean aprovado, String codigo, String mensagem, String moeda, double valor) {
        this.aprovado = aprovado;
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.moeda = moeda;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "RespostaAutorizacao{" +
                "aprovado=" + aprovado +
                ", codigo='" + codigo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", moeda='" + moeda + '\'' +
                ", valor=" + valor +
                '}';
    }
}
