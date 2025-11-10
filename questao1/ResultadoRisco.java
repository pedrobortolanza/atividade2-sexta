package questao1;

public final class ResultadoRisco {
    public final String algoritmo;
    public final String detalhes;

    public ResultadoRisco(String algoritmo, String detalhes) {
        this.algoritmo = algoritmo;
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        return "[" + algoritmo + "] " + detalhes;
    }
}
