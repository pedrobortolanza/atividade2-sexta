package questao1;

import java.util.Collections;
import java.util.Map;

public final class ContextoRisco {

    public final double valorDoPortfolio;

    public final double nivelDeConfianca;

    public final Map<String, Object> dadosDeMercado;

    public ContextoRisco(double valorDoPortfolio,
                         double nivelDeConfianca,
                         Map<String, Object> dadosDeMercado) {
        this.valorDoPortfolio = valorDoPortfolio;
        this.nivelDeConfianca = nivelDeConfianca;
        this.dadosDeMercado = dadosDeMercado == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(dadosDeMercado);
    }

    @Override
    public String toString() {
        return "ContextoRisco{" +
                "valorDoPortfolio=" + valorDoPortfolio +
                ", nivelDeConfianca=" + nivelDeConfianca +
                ", dadosDeMercado=" + dadosDeMercado +
                '}';
    }
}
