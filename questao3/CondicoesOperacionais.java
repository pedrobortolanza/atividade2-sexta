package questao3;

public class CondicoesOperacionais {
    public final double temperaturaCelsius;
    public final double pressao;
    public final double radiacao;
    public final boolean resfriamentoOk;
    public final long instanteEpochMillis;

    public CondicoesOperacionais(double temperaturaCelsius, double pressao, double radiacao, boolean resfriamentoOk, long instanteEpochMillis) {
        this.temperaturaCelsius = temperaturaCelsius;
        this.pressao = pressao;
        this.radiacao = radiacao;
        this.resfriamentoOk = resfriamentoOk;
        this.instanteEpochMillis = instanteEpochMillis;
    }
}
