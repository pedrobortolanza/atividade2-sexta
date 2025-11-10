package questao3;

public class EstadoDesligada implements EstadoUsina {
    @Override
    public String nome() { return "DESLIGADA"; }

    @Override
    public void entrar(UsinaNuclear usina) {}

    @Override
    public void sair(UsinaNuclear usina) {}

    @Override
    public void avaliarTransicao(UsinaNuclear usina, CondicoesOperacionais cond) {
        if (!usina.manutencaoAtiva()) {
            if (cond.temperaturaCelsius <= 300 && cond.resfriamentoOk) {
                usina.transicionar(new EstadoOperacaoNormal(), cond.instanteEpochMillis);
            }
        }
    }
}
