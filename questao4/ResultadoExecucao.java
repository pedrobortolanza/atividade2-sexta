package questao4;

import java.util.List;

public class ResultadoExecucao {
    public final boolean sucesso;
    public final List<String> mensagens;

    public ResultadoExecucao(boolean sucesso, List<String> mensagens) {
        this.sucesso = sucesso;
        this.mensagens = mensagens;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(sucesso ? "SUCESSO" : "FALHA");
        sb.append("\n");
        for (String m : mensagens) sb.append("- ").append(m).append("\n");
        return sb.toString();
    }
}
