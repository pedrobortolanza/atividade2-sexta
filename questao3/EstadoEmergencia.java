package questao3;

public class EstadoEmergencia implements EstadoUsina {
    @Override
    public String nome() { return "EMERGENCIA"; }

    @Override
    public void entrar(UsinaNuclear usina) {}

    @Override
    public void sair(UsinaNuclear usina) {}

    @Override
    public void avaliarTransicao(UsinaNuclear usina, CondicoesOperacionais cond) {
        if (usina.manutencaoAtiva()) return;
        if (cond.resfriamentoOk && cond.temperaturaCelsius <= 300) {
            usina.transicionar(new EstadoDesligada(), cond.instanteEpochMillis);
        }
    }
}
