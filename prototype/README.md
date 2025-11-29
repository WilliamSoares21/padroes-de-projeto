# 🎓 Padrão de Projeto Prototype (GoF)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-red.svg)](https://maven.apache.org/)
[![Design Pattern](https://img.shields.io/badge/Pattern-Creational-blue.svg)](https://refactoring.guru/design-patterns/prototype)

> Projeto educacional demonstrando o padrão de projeto **Prototype** do catálogo Gang of Four (GoF), com ênfase em **custo de criação de objetos** e **cópia profunda vs. cópia rasa**.

## 📚 Sobre o Padrão Prototype

O **Prototype** é um padrão de projeto criacional que permite criar novos objetos copiando instâncias existentes (protótipos), ao invés de criar objetos do zero. É especialmente útil quando:

- A criação de objetos é **custosa** (tempo, recursos, complexidade)
- É necessário criar **variações** de objetos similares
- Queremos **esconder** a complexidade de criação do cliente

### 🎯 Problema que Resolve

```java
// ❌ Sem Prototype: Criação custosa repetida
Circle circle1 = new Circle(); // 100ms
circle1.configure();           // operações complexas

Circle circle2 = new Circle(); // 100ms novamente!
circle2.configure();           // repetir configuração

// ✅ Com Prototype: Clonagem instantânea
Circle circle1 = new Circle(); // 100ms (uma única vez)
circle1.configure();

Circle circle2 = circle1.clone(); // 0ms! Clone instantâneo
```

## 🏗️ Estrutura do Projeto

```
prototype/
├── src/main/java/com/gof/criacional/
│   ├── Shape.java          # Classe abstrata (Prototype)
│   ├── Circle.java         # Protótipo concreto
│   ├── Rectangle.java      # Protótipo concreto
│   ├── Border.java         # Classe auxiliar (demonstra cópia profunda)
│   └── Main.java           # Demonstração didática
├── pom.xml
└── README.md
```

### 📦 Componentes

| Classe | Tipo | Responsabilidade |
|--------|------|------------------|
| `Shape` | Abstrata | Define o contrato de clonagem e implementa cópia profunda base |
| `Circle` | Concreta | Protótipo de círculo com simulação de criação custosa |
| `Rectangle` | Concreta | Protótipo de retângulo com simulação de criação custosa |
| `Border` | Auxiliar | Objeto mutável para demonstrar cópia profunda |
| `Main` | Demonstração | Três cenários educacionais completos |

## 🔬 Conceitos Demonstrados

### 1️⃣ Custo de Criação vs. Clonagem

O projeto simula operações custosas de criação usando `Thread.sleep(100ms)`:

```java
// Criação original: ~100ms
Circle circle = new Circle();  // ⏳ Operação custosa

// Clonagem: ~0ms
Circle clone = circle.clone();  // ⚡ Instantâneo!
```

**Saída:**
```
🔨 Criando protótipo original Circle...
⏳ Criando Circle... (operação custosa)
✓ Circle criado com sucesso!
⏱️  Tempo de criação: 102ms

⚡ Clonando Circle existente...
⏱️  Tempo de clonagem: 0ms
💡 A clonagem foi INSTANTÂNEA!
```

### 2️⃣ Cópia Profunda (Deep Copy) vs. Cópia Rasa (Shallow Copy)

#### ❌ Cópia Rasa (Problema)
```java
// Copia apenas a referência
this.border = target.border;  
// ⚠️ Original e clone compartilham o MESMO objeto!
```

#### ✅ Cópia Profunda (Solução Implementada)
```java
// Cria uma NOVA instância
this.border = new Border(target.border);  
// ✅ Original e clone são INDEPENDENTES!
```

**Demonstração Prática:**
```java
Circle original = new Circle();
original.border.thickness = 3;

Circle clone = original.clone();
clone.border.thickness = 10;  // Modifica apenas o clone

System.out.println(original.border.thickness); // 3 (não afetado!)
System.out.println(clone.border.thickness);    // 10
```

### 3️⃣ Independência de Objetos

O projeto verifica que os clones são **instâncias completamente independentes**:

```java
System.out.println(original == clone);              // false
System.out.println(original.border == clone.border); // false (cópia profunda!)
```

## 🚀 Como Executar

### Pré-requisitos

- ☕ **Java 21** ou superior
- 📦 **Maven 3.8+**

### Executar o Projeto

```bash
# Clonar o repositório
git clone https://github.com/WilliamSoares21/padroes-de-projeto.git
cd padroes-de-projeto/prototype

# Compilar e executar
mvn clean compile exec:java -Dexec.mainClass="com.gof.criacional.Main"
```

### Saída Esperada

A execução apresenta **3 partes educacionais**:

1. **Parte 1**: Comparação de tempo (criação vs. clonagem)
2. **Parte 2**: Demonstração de cópia profunda
3. **Parte 3**: Visualização e testes de independência

```
========================================
  PADRÃO PROTOTYPE - DEMONSTRAÇÃO
========================================

--- PARTE 1: Custo de Criação vs Clonagem ---

🔨 Criando protótipo original Circle...
⏱️  Tempo de criação: 102ms

⚡ Clonando Circle existente...
⏱️  Tempo de clonagem: 0ms

--- PARTE 2: Cópia Profunda vs Cópia Rasa ---

📋 Estado ANTES da modificação do Border do clone:
Circle Original - Border: Border{thickness=3, style='Sólida'}
Circle Clonado  - Border: Border{thickness=3, style='Sólida'}

🔧 Modificando Border do Circle CLONADO...

📋 Estado DEPOIS da modificação do Border do clone:
Circle Original - Border: Border{thickness=3, style='Sólida'}
Circle Clonado  - Border: Border{thickness=10, style='Pontilhada'}

✅ RESULTADO:
   O Border do original NÃO foi afetado!
   Isso demonstra que fizemos uma CÓPIA PROFUNDA (Deep Copy).

--- PARTE 3: Visualização e Independência ---

Círculo Original == Círculo Clonado? false (objetos independentes)
Border Original == Border Clonado? false (cópia profunda!)
```

## 💡 Aprendizados Principais

### ✅ Quando Usar Prototype

- Criação de objetos é **cara** (I/O, rede, cálculos complexos)
- Necessário criar **múltiplas variações** de objetos similares
- Sistema deve ser **independente** de como objetos são criados
- Classes a serem instanciadas são especificadas em **tempo de execução**

### ❌ Quando NÃO Usar

- Criação de objetos é **simples e barata**
- Não há necessidade de **isolar** lógica de criação
- Objetos não têm **estado complexo** para copiar
- Cópia profunda é **difícil** de implementar (referências circulares)

## 🔍 Detalhes de Implementação

### Construtor de Cópia (Copy Constructor)

```java
public Shape(Shape target) {
    if (target != null) {
        this.x = target.x;           // Primitivos: cópia direta
        this.y = target.y;
        this.color = target.color;   // String: imutável (seguro)
        
        // Objeto mutável: CÓPIA PROFUNDA
        this.border = (target.border != null) 
            ? new Border(target.border)  // Nova instância!
            : null;
    }
}
```

### Método clone()

```java
@Override
public Shape clone() {
    return new Circle(this);  // Usa construtor de cópia
}
```

## 📖 Padrões Relacionados

| Padrão | Relação |
|--------|---------|
| **Abstract Factory** | Prototype pode substituir Abstract Factory quando há muitas classes |
| **Singleton** | Prototype Registry pode armazenar protótipos únicos |
| **Composite** | Árvores de Composite podem ser clonadas usando Prototype |
| **Decorator** | Decorators podem ser copiados junto com objetos usando Prototype |

## 🎯 Conceitos de Java Utilizados

- ✅ Classes abstratas e herança
- ✅ Polimorfismo
- ✅ Interface `Cloneable`
- ✅ Construtores de cópia (Copy Constructor)
- ✅ Cópia profunda de objetos
- ✅ Medição de tempo com `System.currentTimeMillis()`
- ✅ Simulação de operações custosas com `Thread.sleep()`

## 📚 Referências

- [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612) (GoF)
- [Refactoring Guru - Prototype Pattern](https://refactoring.guru/design-patterns/prototype)
- [Source Making - Prototype Pattern](https://sourcemaking.com/design_patterns/prototype)

## 👨‍💻 Autor

**William Soares**
- GitHub: [@WilliamSoares21](https://github.com/WilliamSoares21)

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais.

---

⭐ **Se este projeto foi útil para seus estudos, considere deixar uma estrela no repositório!**
