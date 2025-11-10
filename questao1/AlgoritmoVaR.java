package questao1;

public class AlgoritmoVaR implements AlgoritmoDeRisco {

    @Override
    public String nome() { return "Value at Risk"; }

    @Override
    public ResultadoRisco calcular(ContextoRisco contexto) {
        String det = String.format("VaR dummy com CL=%.2f sobre portfólio=%.2f",
                contexto.nivelDeConfianca, contexto.valorDoPortfolio);
        return new ResultadoRisco(nome(), det);
    }
}
