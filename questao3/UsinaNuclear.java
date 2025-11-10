package questao3;

public class UsinaNuclear {
    private EstadoUsina estadoAtual;
    private EstadoUsina estadoAnterior;
    private long instanteUltimaTransicao;
    private long inicioAcima400;
    private boolean acima400;
    private boolean manutencaoAtiva;
    private EstadoUsina estadoAntesManutencao;

    public UsinaNuclear() {
        this.estadoAtual = new EstadoDesligada();
        this.estadoAtual.entrar(this);
        this.instanteUltimaTransicao = System.currentTimeMillis();
    }

    public synchronized void avaliar(CondicoesOperacionais cond) {
        if (manutencaoAtiva) {
            estadoAtual.avaliarTransicao(this, cond);
            return;
        }
        if (cond.temperaturaCelsius > 400) {
            if (!acima400) {
                acima400 = true;
                inicioAcima400 = cond.instanteEpochMillis;
            }
        } else {
            acima400 = false;
            inicioAcima400 = 0;
        }
        estadoAtual.avaliarTransicao(this, cond);
    }

    public synchronized void transicionar(EstadoUsina novo, long agora) {
        if (novo.getClass().equals(estadoAtual.getClass())) return;
        if (estadoAnterior != null && novo.getClass().equals(estadoAnterior.getClass())) {
            if (agora - instanteUltimaTransicao < 5000) return;
        }
        estadoAtual.sair(this);
        estadoAnterior = estadoAtual;
        estadoAtual = novo;
        instanteUltimaTransicao = agora;
        estadoAtual.entrar(this);
    }

    public void ativarManutencao() {
        if (manutencaoAtiva) return;
        manutencaoAtiva = true;
        estadoAntesManutencao = estadoAtual;
        transicionar(new EstadoManutencao(), System.currentTimeMillis());
    }

    public void desativarManutencao() {
        if (!manutencaoAtiva) return;
        manutencaoAtiva = false;
        if (estadoAntesManutencao != null) {
            transicionar(estadoAntesManutencao, System.currentTimeMillis());
            estadoAntesManutencao = null;
        }
    }

    public boolean manutencaoAtiva() { return manutencaoAtiva; }
    public boolean tempAcima400PorMaisDe30s(long agora) { return acima400 && (agora - inicioAcima400) > 30000; }
    public EstadoUsina estadoAtual() { return estadoAtual; }
}
