package questao2;

public final class ConversorMoeda {
    private ConversorMoeda() {}
    public static int paraCodigo(String moeda) {
        if (moeda == null) throw new IllegalArgumentException("Moeda nula");
        switch (moeda.toUpperCase()) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: throw new IllegalArgumentException("Moeda não suportada: " + moeda);
        }
    }
    public static String deCodigo(int codigo) {
        switch (codigo) {
            case 1: return "USD";
            case 2: return "EUR";
            case 3: return "BRL";
            default: throw new IllegalArgumentException("Código de moeda não suportado: " + codigo);
        }
    }
}
