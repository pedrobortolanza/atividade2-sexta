# Questão 2 – Integração com Sistema Bancário Legado

## 📘 Contexto
O sistema precisa integrar com um **sistema bancário legado** que possui:
- Interface incompatível com a atual (`processarTransacao(HashMap<String,Object>)`);
- Tipos de dados obsoletos;
- Codificação de moedas específica (USD=1, EUR=2, BRL=3).

A interface moderna utiliza:
```java
autorizar(String cartao, double valor, String moeda)
```

É necessário converter entre as duas interfaces de forma **bidirecional**.

---

## 🧩 Padrões de Projeto Utilizados

### 1. Adapter (bidirecional)
Foram criados dois adaptadores:

| Classe | Direção | Função |
|--------|----------|--------|
| `AdaptadorLegadoParaModerno` | Legado ➜ Moderno | Permite que o código moderno chame o sistema legado. |
| `AdaptadorModernoParaLegado` | Moderno ➜ Legado | Permite que o sistema legado utilize o processador moderno. |

**Motivo da escolha:**  
O padrão *Adapter* é ideal quando duas interfaces incompatíveis precisam interoperar sem modificar código existente.  
Resolve diretamente o problema de **compatibilidade entre sistemas** e garante **baixo acoplamento** entre módulos.

---

## ⚙️ Funcionamento

### Interfaces
- `ProcessadorTransacoes`: define o contrato moderno (`autorizar(...)`).
- `ISistemaBancarioLegado`: contrato do sistema antigo (`processarTransacao(...)`).

### Implementações
- `SistemaBancarioLegado`: simula o sistema antigo.
- `ProcessadorTransacoesBasico`: implementação moderna simples.
- `ConversorMoeda`: traduz moedas entre formato texto e códigos do legado.

### Adaptadores
- `AdaptadorLegadoParaModerno`: converte chamadas modernas para formato de `HashMap` do legado.  
  Também trata campo **“canal”** obrigatório, que não existe na interface moderna.
- `AdaptadorModernoParaLegado`: converte chamadas do legado para o formato moderno.

---

## 🧠 Justificativas de Design

| Necessidade do problema | Solução adotada | Padrão envolvido |
|--------------------------|-----------------|------------------|
| Converter interface moderna em interface legada | `AdaptadorLegadoParaModerno` | Adapter |
| Converter interface legada em interface moderna | `AdaptadorModernoParaLegado` | Adapter |
| Tratar campos obrigatórios do legado (ex.: “canal”) | Inclusão automática no adaptador | Adapter |
| Codificação de moedas (USD=1, EUR=2, BRL=3) | `ConversorMoeda` para tradução | Utility |
| Comunicação bidirecional | Dois adaptadores independentes | Adapter bidirecional |

---

## 🧱 Princípios SOLID aplicados

| Princípio | Aplicação |
|------------|------------|
| **SRP** | Cada adaptador lida com uma única direção de conversão. |
| **OCP** | Novos tipos de integração podem ser adicionados sem alterar os adaptadores existentes. |
| **LSP** | Ambos os adaptadores respeitam os contratos (`ProcessadorTransacoes`, `ISistemaBancarioLegado`). |
| **ISP** | Interfaces definem apenas métodos necessários (`autorizar`, `processarTransacao`). |
| **DIP** | Adaptadores dependem de abstrações, não de implementações concretas. |

## ✅ Benefícios do Design

- Integração transparente entre sistemas **modernos e legados**.  
- Nenhuma modificação no código legado.  
- Reutilização de ambos os sistemas com mínimo acoplamento.  
- Suporte completo a **bidirecionalidade** e **conversão de formatos**.  
- Cumprimento integral das **restrições do enunciado**.

---
