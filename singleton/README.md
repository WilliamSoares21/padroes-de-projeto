# Padrão de Projeto Singleton

## Objetivo

O padrão Singleton garante que uma classe tenha apenas uma única instância durante todo o ciclo de vida da aplicação e fornece um ponto de acesso global a essa instância.

## Quando Usar

- Quando é necessário ter exatamente uma instância de uma classe
- Para gerenciamento de recursos compartilhados (conexões, caches, pools)
- Para coordenação de ações em todo o sistema
- Para logging, configuração ou registro de aplicação

## Implementações Disponíveis

### 1. EagerSingleton (Inicialização Antecipada)

```java
private static final EagerSingleton INSTANCE = new EagerSingleton();
```

**Características:**
- Instância criada no carregamento da classe
- Thread-safe por padrão
- Simples de implementar
- Pode desperdiçar recursos se nunca usado

**Quando usar:** Quando a instância será sempre utilizada e o custo de criação é baixo.

### 2. LazySingleton (Inicialização Preguiçosa)

```java
public static LazySingleton getInstance() {
    if (instance == null) {
        instance = new LazySingleton();
    }
    return instance;
}
```

**Características:**
- Instância criada apenas no primeiro uso
- Economiza recursos
- NÃO é thread-safe
- Adequado apenas para ambientes single-thread

**Quando usar:** Aplicações single-thread onde lazy initialization é importante.

### 3. ThreadSafeSingleton (Double-Checked Locking)

```java
public static ThreadSafeSingleton getInstance() {
    if (instance == null) {
        synchronized (ThreadSafeSingleton.class) {
            if (instance == null) {
                instance = new ThreadSafeSingleton();
            }
        }
    }
    return instance;
}
```

**Características:**
- Thread-safe
- Lazy initialization
- Usa sincronização apenas na primeira criação
- Requer palavra-chave `volatile`

**Quando usar:** Aplicações multi-thread com necessidade de lazy initialization.

### 4. BillPughSingleton (Recomendado)

```java
private static class SingletonHolder {
    private static final BillPughSingleton INSTANCE = new BillPughSingleton();
}

public static BillPughSingleton getInstance() {
    return SingletonHolder.INSTANCE;
}
```

**Características:**
- Melhor abordagem para Java
- Lazy initialization
- Thread-safe sem sincronização explícita
- Usa o mecanismo de carregamento de classes da JVM

**Quando usar:** É a implementação recomendada na maioria dos casos.

## Exemplo Prático: DatabaseConnection

O projeto inclui um exemplo real de gerenciador de conexão com banco de dados que demonstra:
- Configuração centralizada
- Controle de estado da conexão
- Execução de queries
- Garantia de instância única

## Estrutura do Projeto

```
src/main/java/com/gof/criacional/
├── Main.java                          # Demonstrações práticas
└── singleton/
    ├── EagerSingleton.java            # Inicialização antecipada
    ├── LazySingleton.java             # Inicialização preguiçosa
    ├── ThreadSafeSingleton.java       # Thread-safe com DCL
    ├── BillPughSingleton.java         # Bill Pugh (recomendado)
    └── DatabaseConnection.java        # Exemplo prático

src/test/java/com/gof/criacional/
└── MainTest.java                      # Testes unitários completos
```

## Como Executar

### Executar as demonstrações:
```bash
mvn clean compile exec:java
```

### Executar os testes:
```bash
mvn test
```

## Vantagens do Padrão Singleton

1. **Controle de acesso:** Único ponto de acesso à instância
2. **Economia de recursos:** Apenas uma instância em memória
3. **Estado global:** Mantém estado consistente na aplicação
4. **Lazy initialization:** Criação sob demanda (em algumas implementações)

## Desvantagens

1. **Acoplamento:** Cria dependências globais no código
2. **Testabilidade:** Dificulta testes unitários e mocks
3. **Concorrência:** Requer cuidado em ambientes multi-thread
4. **Violação do SRP:** Controla própria criação e lógica de negócio

## Alternativas Modernas

- **Injeção de Dependência:** Frameworks como Spring gerenciam escopo singleton
- **Enums:** Singleton thread-safe garantido pela JVM
- **Arquitetura funcional:** Evitar estado compartilhado

## Testes

O projeto inclui testes abrangentes:
- Verificação de instância única
- Testes de concorrência (thread-safety)
- Validação de estado compartilhado
- Testes com múltiplas threads simultâneas

## Referências

- **Gang of Four (GoF):** Design Patterns - Elements of Reusable Object-Oriented Software
- **Effective Java (Joshua Bloch):** Item sobre Singleton
- **Java Concurrency in Practice:** Padrões thread-safe
