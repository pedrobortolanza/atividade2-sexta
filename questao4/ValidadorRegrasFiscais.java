package questao4;

public class ValidadorRegrasFiscais implements Validador {
    @Override
    public String nome() { return "ValidadorRegrasFiscais"; }

    @Override
    public ResultadoValidacao validar(NFe nfe, ContextoValidacao ctx) {
        double impostos = nfe.valorProdutos * 0.18;
        nfe.valorImpostosCalculado = impostos;
        return ResultadoValidacao.okComModificacao("Impostos calculados");
    }

    @Override
    public void rollback(NFe nfe, ContextoValidacao ctx) {
        nfe.valorImpostosCalculado = 0.0;
    }
}
