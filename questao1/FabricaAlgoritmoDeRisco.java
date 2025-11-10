package questao1;

public final class FabricaAlgoritmoDeRisco {

    private FabricaAlgoritmoDeRisco() {}

    public static AlgoritmoDeRisco porChave(String chave) {
        if (chave == null) throw new IllegalArgumentException("Chave do algoritmo não pode ser nula");
        switch (chave.toLowerCase()) {
            case "var":
            case "value_at_risk":
                return new AlgoritmoVaR();
            case "es":
            case "perda_esperada":
                return new AlgoritmoPerdaEsperada();
            case "stress":
            case "teste_estresse":
                return new AlgoritmoTesteEstresse();
            default:
                throw new IllegalArgumentException("Algoritmo desconhecido: " + chave);
        }
    }
}
