# Questão 4 – Sistema de Validação de NF-e

## 📘 Contexto

O sistema valida **documentos fiscais eletrônicos (NF-e)** aplicando **várias regras em sequência**, onde cada validador é especializado em um aspecto do documento.

---

## 🎯 Requisitos do Problema

- Cada **validador especializado** verifica um aspecto da NF-e.  
- A cadeia deve suportar **validações condicionais** (ex: se um falhar, pular outro).  
- Implementar **circuit breaker** que interrompe após **3 falhas**.  
- Implementar **rollback** para validadores que modificam o documento.  
- Cada validador possui **timeout individual**.

---

## 🧩 Padrões de Projeto Utilizados

### 1. Chain of Responsibility
A sequência de validadores (`ValidadorSchemaXml`, `ValidadorCertificadoDigital`, `ValidadorRegrasFiscais`, `ValidadorBancoDados`, `ValidadorSefaz`) é encadeada e processada na ordem.

**Motivo da escolha:**  
Permite adicionar, remover ou reordenar validadores sem alterar a lógica principal.  
Cada validador é independente e decide se a cadeia deve continuar.

---

### 2. Command + Rollback
Cada `Validador` é tratado como um **comando autônomo** que pode:
- Executar (`validar(...)`)
- Desfazer (`rollback(...)`)

**Motivo da escolha:**  
Atende à exigência de rollback quando um validador modifica o documento e validações posteriores falham.

---

### 3. Circuit Breaker
Implementado dentro de `CadeiaValidacao`, interrompe o fluxo após **3 falhas consecutivas**, evitando sobrecarga e ações desnecessárias.

**Motivo da escolha:**  
Evita processamento redundante e protege o sistema de falhas em cascata.

---

### 4. Timeout
Cada etapa possui tempo máximo configurável (`Future.get(timeout)`).

**Motivo da escolha:**  
Garante controle de execução e previne bloqueios em chamadas externas (como SEFAZ).

---

## 🧠 Justificativas de Design

| Requisito | Solução | Padrão |
|------------|----------|--------|
| Encadear múltiplas validações | `CadeiaValidacao` com lista de `Etapa` | **Chain of Responsibility** |
| Parar após 3 falhas | Contador interno e interrupção | **Circuit Breaker** |
| Validadores independentes | Interface `Validador` | **Strategy / Command** |
| Reverter mudanças | `rollback()` chamado ao final | **Command + Rollback** |
| Controle de tempo de execução | `Future.get(timeout)` | **Timeout Handling** |
| Dependências condicionais (3 e 5 só se anteriores passarem) | Regras internas em `CadeiaValidacao` | **Conditional Chain** |

---

## 🧱 Princípios SOLID aplicados

| Princípio | Aplicação |
|------------|------------|
| **SRP** | Cada validador executa uma única regra de negócio. |
| **OCP** | Novos validadores podem ser adicionados sem alterar o núcleo. |
| **LSP** | Todos os validadores respeitam o contrato `Validador`. |
| **ISP** | Interface pequena e específica (`validar`, `rollback`). |
| **DIP** | `CadeiaValidacao` depende de abstrações (`Validador`), não de implementações concretas. |

---

## 🔍 Validadores Implementados

| Ordem | Classe | Função | Tipo |
|-------|---------|--------|------|
| 1 | `ValidadorSchemaXml` | Verifica estrutura XML contra XSD | Somente leitura |
| 2 | `ValidadorCertificadoDigital` | Verifica validade e revogação do certificado | Somente leitura |
| 3 | `ValidadorRegrasFiscais` | Calcula impostos | Modifica documento |
| 4 | `ValidadorBancoDados` | Verifica duplicidade e insere número | Modifica documento + rollback |
| 5 | `ValidadorSefaz` | Consulta online da SEFAZ | Somente leitura |

---

