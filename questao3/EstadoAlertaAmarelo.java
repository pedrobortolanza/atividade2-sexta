package questao3;

public class EstadoAlertaAmarelo implements EstadoUsina {
    @Override
    public String nome() { return "ALERTA_AMARELO"; }

    @Override
    public void entrar(UsinaNuclear usina) {}

    @Override
    public void sair(UsinaNuclear usina) {}

    @Override
    public void avaliarTransicao(UsinaNuclear usina, CondicoesOperacionais cond) {
        if (usina.manutencaoAtiva()) return;
        if (cond.temperaturaCelsius <= 300) {
            usina.transicionar(new EstadoOperacaoNormal(), cond.instanteEpochMillis);
            return;
        }
        if (cond.temperaturaCelsius > 400 && usina.tempAcima400PorMaisDe30s(cond.instanteEpochMillis)) {
            usina.transicionar(new EstadoAlertaVermelho(), cond.instanteEpochMillis);
        }
    }
}
