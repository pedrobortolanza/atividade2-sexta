package questao4;

public class NFe {
    public String xml;
    public String numero;
    public long validadeCertificadoEpochMillis;
    public boolean certificadoRevogado;
    public double valorProdutos;
    public double valorImpostosCalculado;
    public boolean inseridaEmBanco;

    public NFe(String xml, String numero, long validadeCertificadoEpochMillis, boolean certificadoRevogado, double valorProdutos) {
        this.xml = xml;
        this.numero = numero;
        this.validadeCertificadoEpochMillis = validadeCertificadoEpochMillis;
        this.certificadoRevogado = certificadoRevogado;
        this.valorProdutos = valorProdutos;
    }
}
