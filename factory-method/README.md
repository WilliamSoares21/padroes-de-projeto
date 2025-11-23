# 🏭 Factory Method Pattern - Exemplo Didático

## 📋 Sobre o Projeto

Este projeto demonstra de forma prática e didática a implementação do padrão de projeto **Factory Method** usando um sistema de gerenciamento de veículos. O código foi desenvolvido para fins educacionais, facilitando o entendimento de como e por que utilizar este padrão.

## 🎯 Objetivo

Ilustrar como o Factory Method resolve o problema de criação de objetos, promovendo:
- ✅ Desacoplamento entre criação e uso
- ✅ Facilidade de extensão
- ✅ Código limpo e manutenível
- ✅ Princípios SOLID (Open/Closed, Single Responsibility)

## 🔧 Estrutura do Projeto

```
src/main/java/
├── Veiculo.java              # Produto abstrato
├── Van.java                  # Produto concreto
├── Onibus.java               # Produto concreto
├── VeiculoFactory.java       # Creator abstrato
├── VanFactory.java           # Creator concreto
├── OnibusFactory.java        # Creator concreto
├── GerenciadorVeiculo.java   # Cliente que usa as fábricas
└── Main.java                 # Exemplos de uso
```

## 🧩 Como o Padrão Funciona

### Diagrama Conceitual
```
Cliente → VeiculoFactory (abstrata) → Veiculo (abstrato)
              ↓                           ↓
         VanFactory                     Van
         OnibusFactory                  Onibus
```

### Fluxo de Execução

1. **Cliente cria uma fábrica:**
   ```java
   VeiculoFactory factory = new VanFactory();
   ```

2. **Fábrica cria o produto apropriado:**
   ```java
   Veiculo veiculo = factory.criarVeiculo(); // Retorna Van
   ```

3. **Cliente usa o produto através da interface:**
   ```java
   veiculo.exibirDetalhes(); // Polimorfismo em ação
   ```

### 🔑 Conceito Principal

> **"Deixe as subclasses decidirem qual classe instanciar"**

O código cliente (`Main`, `GerenciadorVeiculo`) **não conhece** as classes concretas (`Van`, `Onibus`). Ele trabalha apenas com abstrações (`VeiculoFactory`, `Veiculo`).

## 💡 Exemplos Práticos no Código

### Exemplo 1: Criação Básica
```java
VeiculoFactory vanFactory = new VanFactory();
Veiculo van = vanFactory.criarVeiculo();
van.exibirDetalhes();
```

### Exemplo 2: Gerenciador (uso real)
```java
GerenciadorVeiculo gerenciador = new GerenciadorVeiculo();
gerenciador.adicionarVeiculo(new VanFactory());
gerenciador.adicionarVeiculo(new OnibusFactory());
gerenciador.listarVeiculos();
```

### Exemplo 3: Escolha Dinâmica
```java
String tipo = "onibus"; // Poderia vir do usuário
VeiculoFactory factory = escolherFactory(tipo);
Veiculo veiculo = factory.criarVeiculo();
```

## 🚀 Como Executar

### Pré-requisitos
- Java 8 ou superior
- Maven

### Compilar e Executar
```bash
# Compilar
mvn clean compile

# Executar
mvn exec:java -Dexec.mainClass="Main"

# Ou usando Java diretamente
cd target/classes
java Main
```

## 📚 O Que Você Aprende

### Problemas Resolvidos
- ❌ **Antes:** `if (tipo == "van") return new Van();` - acoplamento direto
- ✅ **Depois:** `factory.criarVeiculo();` - delegação da criação

### Vantagens Demonstradas

| Vantagem | Como o projeto demonstra |
|----------|--------------------------|
| **Extensibilidade** | Adicionar `Caminhao` requer apenas criar 2 novas classes |
| **Desacoplamento** | `GerenciadorVeiculo` não importa Van ou Onibus |
| **Polimorfismo** | Método `processarVeiculo()` aceita qualquer factory |
| **Manutenibilidade** | Mudanças em Van não afetam Onibus ou código cliente |

### Princípios SOLID Aplicados

- **S**ingle Responsibility: Cada fábrica cria um tipo específico
- **O**pen/Closed: Aberto para novas fábricas, fechado para modificações
- **L**iskov Substitution: Qualquer factory pode substituir outra
- **D**ependency Inversion: Dependemos de abstrações, não concreções

## 🎓 Quando Usar Factory Method

✅ **Use quando:**
- Você não sabe antecipadamente quais tipos de objetos criar
- Quer delegar a criação para subclasses
- Deseja promover baixo acoplamento
- Precisa facilitar testes (mock de factories)

❌ **Não use quando:**
- Tem apenas um tipo de objeto
- A criação é trivial (simples `new`)
- Adiciona complexidade desnecessária

## 🔄 Extensão do Projeto

Para adicionar um novo tipo de veículo (ex: `Caminhao`):

```java
// 1. Criar o produto
public class Caminhao extends Veiculo {
    String getCapacidade() { return "2"; }
    String getTipo() { return "Caminhão"; }
}

// 2. Criar a fábrica
public class CaminhaoFactory extends VeiculoFactory {
    public Veiculo createVeiculo() { return new Caminhao(); }
}

// 3. Usar (código existente permanece inalterado!)
gerenciador.adicionarVeiculo(new CaminhaoFactory());
```

## 📖 Recursos Adicionais

- [Refactoring.Guru - Factory Method](https://refactoring.guru/pt-br/design-patterns/factory-method)
- [Design Patterns - Gang of Four](https://www.amazon.com.br/Padr%C3%B5es-Projetos-Solu%C3%A7%C3%B5es-Reutiliz%C3%A1veis-Orientados/dp/8573076100)

## 📝 Licença

Este projeto é de código aberto para fins educacionais.

---

**Desenvolvido para estudo de Padrões de Projeto** 🎯
