package questao3;

public class Main {
    public static void main(String[] args) throws Exception {
        UsinaNuclear usina = new UsinaNuclear();
        long t = System.currentTimeMillis();

        usina.avaliar(new CondicoesOperacionais(290, 1.0, 1.0, true, t));
        System.out.println(usina.estadoAtual().nome());

        usina.avaliar(new CondicoesOperacionais(320, 1.0, 1.0, true, t + 1000));
        System.out.println(usina.estadoAtual().nome());

        usina.avaliar(new CondicoesOperacionais(410, 1.0, 1.0, true, t + 2000));
        System.out.println(usina.estadoAtual().nome());

        usina.avaliar(new CondicoesOperacionais(410, 1.0, 1.0, true, t + 33050));
        System.out.println(usina.estadoAtual().nome());

        usina.avaliar(new CondicoesOperacionais(410, 1.0, 1.0, false, t + 34050));
        System.out.println(usina.estadoAtual().nome());

        usina.ativarManutencao();
        System.out.println(usina.estadoAtual().nome());

        usina.desativarManutencao();
        System.out.println(usina.estadoAtual().nome());

        usina.avaliar(new CondicoesOperacionais(290, 1.0, 1.0, true, t + 60000));
        System.out.println(usina.estadoAtual().nome());
    }
}
