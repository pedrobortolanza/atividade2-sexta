package questao4;

import java.util.concurrent.Executors;

public class Main  {
    public static void main(String[] args) {
        RepositorioNFe repo = new RepositorioNFe();
        ServicoSefaz sefaz = new ServicoSefaz();
        ContextoValidacao ctx = new ContextoValidacao(System.currentTimeMillis(), Executors.newCachedThreadPool(), repo, sefaz);
        ctx.timeoutsMs.put("ValidadorSchemaXml", 1000L);
        ctx.timeoutsMs.put("ValidadorCertificadoDigital", 1000L);
        ctx.timeoutsMs.put("ValidadorRegrasFiscais", 1500L);
        ctx.timeoutsMs.put("ValidadorBancoDados", 1000L);
        ctx.timeoutsMs.put("ValidadorSefaz", 3000L);

        NFe nfe = new NFe("<nfe>...</nfe>", "NF123", System.currentTimeMillis() + 86_400_000L, false, 1000.0);

        CadeiaValidacao cadeia = new CadeiaValidacao(3)
                .adicionar(new ValidadorSchemaXml(), 1000)
                .adicionar(new ValidadorCertificadoDigital(), 1000)
                .adicionar(new ValidadorRegrasFiscais(), 1500)
                .adicionar(new ValidadorBancoDados(), 1000)
                .adicionar(new ValidadorSefaz(), 3000);

        ResultadoExecucao res = cadeia.validar(nfe, ctx);
        System.out.println(res);

        NFe nfe2 = new NFe("<nfe>...</nfe>", "NF123", System.currentTimeMillis() - 1000L, false, 800.0);
        ResultadoExecucao res2 = cadeia.validar(nfe2, ctx);
        System.out.println(res2);
    }
}
