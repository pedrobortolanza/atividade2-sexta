package questao4;

public class ResultadoValidacao {
    public final boolean sucesso;
    public final String mensagem;
    public final boolean modificouDocumento;

    public ResultadoValidacao(boolean sucesso, String mensagem, boolean modificouDocumento) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.modificouDocumento = modificouDocumento;
    }

    public static ResultadoValidacao ok() {
        return new ResultadoValidacao(true, "OK", false);
    }

    public static ResultadoValidacao okComModificacao(String msg) {
        return new ResultadoValidacao(true, msg, true);
    }

    public static ResultadoValidacao falha(String msg) {
        return new ResultadoValidacao(false, msg, false);
    }
}
