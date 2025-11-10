package questao1;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> mercado = new HashMap<>();
        mercado.put("volatilidadeImplicita", 0.35);
        mercado.put("curvaJuros", "DI1 futuro 2027");

        ContextoRisco contexto = new ContextoRisco(
                1_000_000.00,
                0.99,
                mercado
        );

        // Inicializa com VaR
        MotorDeRisco motor = new MotorDeRisco(FabricaAlgoritmoDeRisco.porChave("var"));
        System.out.println(motor.executar(contexto)); 

        // Troca em runtime para Perda Esperada (ES)
        motor.trocarAlgoritmo(FabricaAlgoritmoDeRisco.porChave("es"));
        System.out.println(motor.executar(contexto)); 

        // Troca em runtime para Teste de Estresse
        motor.trocarAlgoritmo(FabricaAlgoritmoDeRisco.porChave("stress"));
        System.out.println(motor.executar(contexto)); 
    }
}
