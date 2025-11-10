package questao3;

public class EstadoOperacaoNormal implements EstadoUsina {
    @Override
    public String nome() { return "OPERACAO_NORMAL"; }

    @Override
    public void entrar(UsinaNuclear usina) {}

    @Override
    public void sair(UsinaNuclear usina) {}

    @Override
    public void avaliarTransicao(UsinaNuclear usina, CondicoesOperacionais cond) {
        if (usina.manutencaoAtiva()) return;
        if (cond.temperaturaCelsius > 300) {
            usina.transicionar(new EstadoAlertaAmarelo(), cond.instanteEpochMillis);
            return;
        }
    }
}
