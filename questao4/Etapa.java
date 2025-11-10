package questao4;

public class Etapa {
    public final Validador validador;
    public final long timeoutMs;

    public Etapa(Validador validador, long timeoutMs) {
        this.validador = validador;
        this.timeoutMs = timeoutMs;
    }
}
