package questao3;

public interface EstadoUsina {
    String nome();
    void entrar(UsinaNuclear usina);
    void sair(UsinaNuclear usina);
    void avaliarTransicao(UsinaNuclear usina, CondicoesOperacionais cond);
}
