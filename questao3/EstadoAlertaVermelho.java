package questao3;

public class EstadoAlertaVermelho implements EstadoUsina {
    @Override
    public String nome() { return "ALERTA_VERMELHO"; }

    @Override
    public void entrar(UsinaNuclear usina) {}

    @Override
    public void sair(UsinaNuclear usina) {}

    @Override
    public void avaliarTransicao(UsinaNuclear usina, CondicoesOperacionais cond) {
        if (usina.manutencaoAtiva()) return;
        if (!cond.resfriamentoOk) {
            usina.transicionar(new EstadoEmergencia(), cond.instanteEpochMillis);
            return;
        }
        if (cond.temperaturaCelsius <= 400) {
            usina.transicionar(new EstadoAlertaAmarelo(), cond.instanteEpochMillis);
        }
    }
}
