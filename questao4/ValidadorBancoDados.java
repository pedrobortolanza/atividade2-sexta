package questao4;

public class ValidadorBancoDados implements Validador {
    @Override
    public String nome() { return "ValidadorBancoDados"; }

    @Override
    public ResultadoValidacao validar(NFe nfe, ContextoValidacao ctx) {
        if (ctx.repositorio.existeNumero(nfe.numero)) return ResultadoValidacao.falha("Duplicidade de número");
        ctx.repositorio.inserirNumero(nfe.numero);
        nfe.inseridaEmBanco = true;
        return ResultadoValidacao.okComModificacao("Número inserido");
    }

    @Override
    public void rollback(NFe nfe, ContextoValidacao ctx) {
        if (nfe.inseridaEmBanco) {
            ctx.repositorio.removerNumero(nfe.numero);
            nfe.inseridaEmBanco = false;
        }
    }
}
