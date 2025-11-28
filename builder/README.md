# Builder Pattern - Projeto Educacional

![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Maven-3.9+-blue)
![License](https://img.shields.io/badge/License-Educational-green)

## 📚 Sobre o Projeto

Este é um projeto **educacional** desenvolvido para demonstrar a implementação do padrão de projeto **Builder** (Construtor), um dos padrões criacionais do GoF (Gang of Four).

O projeto apresenta uma implementação completa e profissional do padrão Builder aplicado a um sistema de gerenciamento de cursos online, com duas entidades principais: `Course` (Curso) e `Module` (Módulo).

### 🎯 Objetivos Educacionais

- Demonstrar a implementação prática do padrão Builder
- Ilustrar boas práticas de programação Java moderna
- Apresentar conceitos de imutabilidade e validação de dados
- Exemplificar a aplicação dos princípios SOLID
- Servir como material de estudo e referência

---

## 🏗️ O Padrão Builder

### O que é?

O **Builder** é um padrão de projeto criacional que permite construir objetos complexos passo a passo. Ele separa a construção de um objeto complexo de sua representação, permitindo que o mesmo processo de construção crie diferentes representações.

### Quando usar?

- Quando você precisa criar objetos com **muitos parâmetros opcionais**
- Para evitar **construtores telescópicos** (múltiplos construtores com diferentes combinações de parâmetros)
- Quando o processo de construção deve permitir **diferentes representações** do objeto
- Para criar **objetos imutáveis** com validações complexas

### Vantagens

✅ **Legibilidade**: Código mais limpo e fácil de entender  
✅ **Flexibilidade**: Facilita adicionar novos parâmetros opcionais  
✅ **Imutabilidade**: Objetos podem ser construídos como imutáveis  
✅ **Validação**: Validações centralizadas no momento da construção  
✅ **Fluent Interface**: API intuitiva com encadeamento de métodos  

---

## 🔧 Estrutura do Projeto

### Classes Principais

#### `Course` (Curso)
Representa um curso online completo com informações como:
- ID, título e instrutor (obrigatórios)
- Descrição, preço e data de lançamento (opcionais)
- Lista de módulos
- Horas estimadas e status de publicação

```java
Course course = new Course.Builder(
        "curso-java-basico",
        "Java Básico",
        "Prof. Maria")
    .description("Curso introdutório de Java")
    .price(BigDecimal.ZERO)
    .addModule(module1)
    .addModule(module2)
    .estimatedHours(4)
    .releaseDate(LocalDate.of(2025, 3, 1))
    .published(true)
    .build();
```

#### `Module` (Módulo)
Representa um módulo de um curso com:
- Título (obrigatório)
- Conteúdo e duração (opcionais)
- Lista de recursos

```java
Module module = new Module.Builder("Introdução ao Java")
    .content("Conceitos básicos: JDK, JRE, JVM")
    .durationMinutes(30)
    .addResource("slides-intro.pdf")
    .build();
```

### Características da Implementação

#### 🔒 Imutabilidade
- Classes marcadas como `final` (não podem ser estendidas)
- Todos os atributos são `final`
- Listas retornadas são imutáveis (`List.copyOf()`)
- Cópias defensivas nas coleções

#### ✓ Validações Robustas
- Campos obrigatórios validados no construtor do Builder
- Validações de regras de negócio no método `build()`
- Proteção contra valores nulos com fallbacks seguros
- Mensagens de erro descritivas

#### 💰 Precisão Financeira
- Uso de `BigDecimal` para valores monetários (não `double`)
- Garante precisão exata em operações financeiras
- Segue boas práticas da indústria

#### 🎨 Código Limpo
- Métodos com responsabilidade única (SRP)
- Nomenclatura descritiva e auto-explicativa
- Otimizações de performance (constantes, capacidade inicial)
- Separação de concerns

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 21** ou superior
- **Maven 3.9+**

### Compilar o Projeto

```bash
mvn clean compile
```

### Executar o Programa

```bash
mvn exec:java
```

### Executar os Testes

```bash
mvn test
```

### Saída Esperada

```
CURSO: Java Básico - Projeto Educacional
ID: curso-java-basico
Instrutor: Prof. Maria
Preco: Gratuito
Horas Estimadas: 4h
Lancamento: 01/03/2025
Status: Publicado

Modulos (2):
----------------------------------------
1) Introdução ao Java
   Duracao: 30 minutos
   Recursos:
     - slides-intro.pdf

----------------------------------------
2) Variáveis e Controle
   Duracao: 45 minutos
   Recursos:
     - exercicios-variaveis.zip
```

---

## 📖 Conceitos Demonstrados

### Padrões de Projeto
- ✅ **Builder Pattern**: Construção fluente de objetos complexos
- ✅ **Immutable Object**: Objetos imutáveis e thread-safe

### Princípios SOLID
- ✅ **SRP**: Responsabilidade única (métodos auxiliares)
- ✅ **OCP**: Aberto para extensão (Builder permite novos atributos)
- ✅ **LSP**: Classes finais previnem herança incorreta
- ✅ **ISP**: Interfaces mínimas (apenas getters necessários)
- ✅ **DIP**: Dependência de abstrações (List, BigDecimal)

### Boas Práticas Java
- ✅ Uso de `BigDecimal` para valores monetários
- ✅ Validação rigorosa de entrada
- ✅ Cópias defensivas de coleções
- ✅ Uso de `List.copyOf()` (Java 10+)
- ✅ Extração de constantes
- ✅ StringBuilder com capacidade inicial
- ✅ Métodos auxiliares privados

---

## 📂 Estrutura de Diretórios

```
builder/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/gof/criacional/
│   │           ├── Course.java          # Classe principal com Builder interno
│   │           ├── Module.java          # Classe de módulo com Builder interno
│   │           └── Main.java            # Exemplo de uso
│   └── test/
│       └── java/
│           └── com/gof/criacional/
│               └── MainTest.java        # Testes unitários
├── pom.xml                              # Configuração Maven
├── README.md                            # Este arquivo
└── RELATORIO_MELHORIAS.md              # Relatório técnico detalhado
```

---

## 🧪 Testes

O projeto inclui testes unitários abrangentes que cobrem:
- ✅ Construção bem-sucedida com todos os parâmetros
- ✅ Validação de campos obrigatórios
- ✅ Validação de regras de negócio
- ✅ Imutabilidade das coleções
- ✅ Valores padrão corretos
- ✅ Casos extremos e exceções

Execute `mvn test` para verificar todos os testes.

---

## 📝 Documentação Adicional

Para uma análise técnica detalhada das melhorias implementadas, consulte:
- **[RELATORIO_MELHORIAS.md](RELATORIO_MELHORIAS.md)**: Relatório completo com justificativas técnicas

---

## 🎓 Aprendizado

Este projeto é ideal para:
- 📘 Estudantes de Engenharia de Software
- 👨‍💻 Desenvolvedores aprendendo padrões de projeto
- 🔍 Profissionais buscando referência de código limpo
- 🏫 Professores procurando material didático

---

## 🤝 Contribuições

Este é um projeto educacional. Sinta-se à vontade para:
- Estudar e modificar o código
- Usar como referência em seus estudos
- Compartilhar com outros estudantes
- Sugerir melhorias educacionais

---

## 📚 Referências

- **Design Patterns: Elements of Reusable Object-Oriented Software** - Gang of Four
- **Effective Java** (3rd Edition) - Joshua Bloch
- **Clean Code** - Robert C. Martin
- [Refactoring Guru - Builder Pattern](https://refactoring.guru/design-patterns/builder)

---

## 📄 Licença

Este projeto é de uso **educacional** e está disponível para fins de aprendizado.

---

## ✨ Autor

Projeto desenvolvido como material educacional para estudo de padrões de projeto.

---

**⭐ Se este projeto foi útil para seus estudos, considere dar uma estrela!**
