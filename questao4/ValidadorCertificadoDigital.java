package questao4;

public class ValidadorCertificadoDigital implements Validador {
    @Override
    public String nome() { return "ValidadorCertificadoDigital"; }

    @Override
    public ResultadoValidacao validar(NFe nfe, ContextoValidacao ctx) {
        if (nfe.certificadoRevogado) return ResultadoValidacao.falha("Certificado revogado");
        if (nfe.validadeCertificadoEpochMillis < ctx.agoraEpochMillis) return ResultadoValidacao.falha("Certificado expirado");
        return ResultadoValidacao.ok();
    }

    @Override
    public void rollback(NFe nfe, ContextoValidacao ctx) {}
}
