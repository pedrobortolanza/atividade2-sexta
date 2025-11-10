package questao2;

import java.util.HashMap;

public interface ISistemaBancarioLegado {
    HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros);
}
