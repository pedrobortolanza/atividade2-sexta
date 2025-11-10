package questao3;

public class EstadoManutencao implements EstadoUsina {
    @Override
    public String nome() { return "MANUTENCAO"; }

    @Override
    public void entrar(UsinaNuclear usina) {}

    @Override
    public void sair(UsinaNuclear usina) {}

    @Override
    public void avaliarTransicao(UsinaNuclear usina, CondicoesOperacionais cond) {}
}
