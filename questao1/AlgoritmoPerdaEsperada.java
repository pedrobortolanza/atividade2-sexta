package questao1;

public class AlgoritmoPerdaEsperada implements AlgoritmoDeRisco {

    @Override
    public String nome() { return "Perda Esperada"; }

    @Override
    public ResultadoRisco calcular(ContextoRisco contexto) {
        Object iv = contexto.dadosDeMercado.getOrDefault("volatilidadeImplicita", "n/d");
        String det = "ES dummy usando volatilidadeImplicita=" + iv;
        return new ResultadoRisco(nome(), det);
    }
}
