# Questão 1 – Sistema de Cálculo de Risco

## 📘 Contexto
Sistema financeiro que processa **métricas de risco** usando diferentes algoritmos:  
- Value at Risk (VaR)  
- Expected Shortfall (Perda Esperada)  
- Stress Testing (Teste de Estresse)

O cliente precisa **trocar o algoritmo de risco dinamicamente**, sem conhecer detalhes técnicos.

---

## 🧩 Padrões de Projeto Utilizados

### 1. Strategy (`AlgoritmoDeRisco`)
Permite alternar entre diferentes algoritmos em tempo de execução.  
Cada cálculo de risco é encapsulado em uma **estratégia** concreta:
- `AlgoritmoVaR`
- `AlgoritmoPerdaEsperada`
- `AlgoritmoTesteEstresse`

**Motivo da escolha:**  
O padrão *Strategy* resolve diretamente o requisito de **intercambialidade dinâmica** dos algoritmos de risco.  
Evita condicionais (if/switch) no cliente e segue o **Princípio Aberto/Fechado (OCP)**.

---

### 2. Context Object (`ContextoRisco`)
Agrupa e compartilha parâmetros complexos entre os algoritmos, como:
- Valor do portfólio  
- Nível de confiança  
- Dados de mercado (mapa de parâmetros financeiros)

**Motivo da escolha:**  
Os algoritmos precisam de vários parâmetros, e centralizá-los num objeto imutável evita duplicação, facilita manutenção e respeita o **Princípio da Responsabilidade Única (SRP)**.

---

### 3. Simple Factory (`FabricaAlgoritmoDeRisco`)
Responsável por instanciar os algoritmos com base em uma **chave de negócio** (ex.: `"var"`, `"es"`, `"stress"`).  
O cliente não precisa conhecer construtores nem classes concretas.

**Motivo da escolha:**  
Garante que o cliente possa **trocar de algoritmo sem conhecer detalhes de implementação**, conforme a restrição do enunciado.

---

### 4. Context Holder / Orquestrador (`MotorDeRisco`)
Mantém a estratégia atual e executa o cálculo.  
Permite **trocar o algoritmo em runtime** de forma segura e centralizada.

**Motivo da escolha:**  
Separa a lógica de orquestração do cálculo da lógica de cada algoritmo, reforçando o **Princípio da Inversão de Dependência (DIP)** e **SRP**.

---

### 5. DTO (`ResultadoRisco`)
Padroniza a saída do cálculo, desacoplando o cliente das implementações concretas.

---

## 🧠 Justificativas Gerais

| Requisito | Solução |
|------------|----------|
| Algoritmos intercambiáveis | Padrão **Strategy** |
| Compartilhar contexto complexo | Padrão **Context Object** |
| Trocar algoritmo sem conhecer detalhes | Padrão **Simple Factory** |
| Execução e troca dinâmica em runtime | Classe **MotorDeRisco** |
| Estrutura modular e extensível | Aplicação dos princípios **SOLID** |

---

## 🧱 Princípios SOLID aplicados

| Princípio | Aplicação |
|------------|------------|
| **SRP** | Cada classe tem uma única responsabilidade (estratégia, contexto, resultado, orquestrador). |
| **OCP** | Novos algoritmos podem ser adicionados sem alterar código existente. |
| **LSP** | Todas as estratégias respeitam o contrato `AlgoritmoDeRisco`. |
| **ISP** | Interface mínima e específica (métodos `nome()` e `calcular()`). |
| **DIP** | O cliente e o motor dependem de abstrações, não de implementações. |

---

