package questao4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class ContextoValidacao {
    public final Map<String, Long> timeoutsMs = new HashMap<>();
    public final long agoraEpochMillis;
    public final ExecutorService executor;
    public final RepositorioNFe repositorio;
    public final ServicoSefaz servicoSefaz;

    public ContextoValidacao(long agoraEpochMillis, ExecutorService executor, RepositorioNFe repositorio, ServicoSefaz servicoSefaz) {
        this.agoraEpochMillis = agoraEpochMillis;
        this.executor = executor;
        this.repositorio = repositorio;
        this.servicoSefaz = servicoSefaz;
    }

    public long timeoutPara(String nomeValidador, long padraoMs) {
        return timeoutsMs.getOrDefault(nomeValidador, padraoMs);
    }
}
