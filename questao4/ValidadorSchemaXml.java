package questao4;

public class ValidadorSchemaXml implements Validador {
    @Override
    public String nome() { return "ValidadorSchemaXml"; }

    @Override
    public ResultadoValidacao validar(NFe nfe, ContextoValidacao ctx) {
        if (nfe.xml == null || !nfe.xml.startsWith("<nfe")) return ResultadoValidacao.falha("XML inválido contra XSD");
        return ResultadoValidacao.ok();
    }

    @Override
    public void rollback(NFe nfe, ContextoValidacao ctx) {}
}
