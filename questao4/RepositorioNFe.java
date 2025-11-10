package questao4;

import java.util.HashSet;
import java.util.Set;

public class RepositorioNFe {
    private final Set<String> numeros = new HashSet<>();

    public synchronized boolean existeNumero(String numero) {
        return numeros.contains(numero);
    }

    public synchronized void inserirNumero(String numero) {
        numeros.add(numero);
    }

    public synchronized void removerNumero(String numero) {
        numeros.remove(numero);
    }
}
