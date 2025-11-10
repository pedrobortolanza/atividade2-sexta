package questao4;

public interface Validador {
    String nome();
    ResultadoValidacao validar(NFe nfe, ContextoValidacao ctx) throws Exception;
    void rollback(NFe nfe, ContextoValidacao ctx);
}
