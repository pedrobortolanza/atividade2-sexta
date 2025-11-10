# Questão 3 – Sistema de Controle de Usina Nuclear

## 📘 Contexto

Você está modelando um sistema de **controle para uma usina nuclear** com estados complexos de operação.  
A usina pode estar em um dos seguintes estados:

- `DESLIGADA`
- `OPERACAO_NORMAL`
- `ALERTA_AMARELO`
- `ALERTA_VERMELHO`
- `EMERGENCIA`
- `MANUTENCAO` (modo especial)

---

## 🎯 Requisitos

- Cada **transição de estado** deve validar condições de temperatura, pressão e radiação.  
- Algumas transições são **bidirecionais**, outras são **unidirecionais**.  
- Deve-se **evitar transições circulares perigosas**.  
- O estado **EMERGENCIA** só pode ser ativado **após ALERTA_VERMELHO**.  
- Deve haver um **modo manutenção**, que sobreescreve os estados normais temporariamente.  

### Regras de transição:

| Transição | Condição |
|------------|-----------|
| `OPERACAO_NORMAL → ALERTA_AMARELO` | temperatura > 300°C |
| `ALERTA_AMARELO → ALERTA_VERMELHO` | temperatura > 400°C por mais de 30s |
| `ALERTA_VERMELHO → EMERGENCIA` | sistema de resfriamento falhar |

---

## 🧩 Padrões de Projeto Utilizados

### 1. State
Cada estado da usina (`DESLIGADA`, `OPERACAO_NORMAL`, `ALERTA_AMARELO`, `ALERTA_VERMELHO`, `EMERGENCIA`, `MANUTENCAO`) é representado como uma **classe concreta** que implementa a interface `EstadoUsina`.

**Motivo da escolha:**
O padrão *State* permite que o comportamento da usina mude dinamicamente conforme o estado atual, evitando o uso de estruturas condicionais complexas.  
Cada estado é responsável por **suas próprias regras de transição**, tornando o sistema extensível e seguro.

---

## 🧱 Princípios SOLID Aplicados

| Princípio | Aplicação |
|------------|------------|
| **SRP** | Cada classe de estado tem uma única função: gerenciar um estado específico. |
| **OCP** | Novos estados podem ser adicionados sem alterar os existentes. |
| **LSP** | Todos os estados respeitam a interface `EstadoUsina`. |
| **ISP** | A interface possui apenas métodos essenciais (`entrar`, `sair`, `avaliarTransicao`). |
| **DIP** | `UsinaNuclear` depende da abstração `EstadoUsina`, não de classes concretas. |

---

