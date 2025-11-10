package questao1;


public class MotorDeRisco {

    private AlgoritmoDeRisco algoritmo; // Strategy atual

    public MotorDeRisco(AlgoritmoDeRisco algoritmoInicial) {
        this.algoritmo = algoritmoInicial;
    }

    public void trocarAlgoritmo(AlgoritmoDeRisco proximo) {
        this.algoritmo = proximo;
    }


    public ResultadoRisco executar(ContextoRisco contexto) {
        if (algoritmo == null) {
            throw new IllegalStateException("Nenhum algoritmo configurado no MotorDeRisco");
        }
        return algoritmo.calcular(contexto);
    }
}
