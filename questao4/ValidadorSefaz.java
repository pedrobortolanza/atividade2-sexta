package questao4;

public class ValidadorSefaz implements Validador {
    @Override
    public String nome() { return "ValidadorSefaz"; }

    @Override
    public ResultadoValidacao validar(NFe nfe, ContextoValidacao ctx) throws Exception {
        boolean ok = ctx.servicoSefaz.consultaValida(nfe);
        if (!ok) return ResultadoValidacao.falha("SEFAZ rejeitou");
        return ResultadoValidacao.ok();
    }

    @Override
    public void rollback(NFe nfe, ContextoValidacao ctx) {}
}
