package questao1;


public class AlgoritmoTesteEstresse implements AlgoritmoDeRisco {

    @Override
    public String nome() { return "Teste de Estresse"; }

    @Override
    public ResultadoRisco calcular(ContextoRisco contexto) {
        String det = "Stress dummy (cenário=2008-like) portfólio=" + contexto.valorDoPortfolio;
        return new ResultadoRisco(nome(), det);
    }
}
