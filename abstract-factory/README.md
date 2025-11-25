# Abstract Factory + Template Method + Singleton

## 🎯 Problemas Identificados e Resolvidos

### 1. **Singleton não implementado**
- ❌ **Antes**: Factories criavam novas instâncias a cada chamada
- ✅ **Depois**: Implementado Singleton thread-safe com lazy initialization

### 2. **Conflito de arquitetura**
- ❌ **Antes**: Dois `DecoderFactory` (interface no pacote `decoder` e classe abstrata no pacote `factory`)
- ✅ **Depois**: Removida interface duplicada, mantida apenas a classe abstrata correta

### 3. **Classes inexistentes**
- ❌ **Antes**: Referências a `MsgRegistroCliente` e `MsgRegistroConta` não existentes
- ✅ **Depois**: Template Method usa os decoders corretos (`RegistrarClienteDecoder` e `RegistrarContaDecoder`)

### 4. **Template Method mal estruturado**
- ❌ **Antes**: Referência a interface `Decoder` incorreta
- ✅ **Depois**: Template Method correto usando abstract factory pattern

### 5. **Falta de implementação**
- ❌ **Antes**: `TextoFixoDecoderTemplate` não existia
- ✅ **Depois**: Criado e integrado ao sistema

## 🏗️ Arquitetura Final

```
📦 com.gof.criacional
├── 🏭 factory/
│   ├── DecoderFactory (Abstract Class)           ← Factory Method + Static Factory
│   ├── CSVDecoderFactory (Singleton)            ← Concrete Factory
│   ├── XMLDecoderFactory (Singleton)            ← Concrete Factory
│   └── TextoFixoDecoderFactory (Singleton)      ← Concrete Factory
│
├── 📝 decoder/
│   ├── DecoderTemplate (Abstract Class)         ← Template Method
│   ├── RegistrarClienteDecoder (Abstract)       ← Product Interface
│   ├── RegistrarContaDecoder (Abstract)         ← Product Interface
│   │
│   ├── csv/
│   │   ├── CSVDecoderTemplate                   ← Concrete Template
│   │   ├── RegistrarClienteCSVDecoder           ← Concrete Product
│   │   └── RegistrarContaCSVDecoder             ← Concrete Product
│   │
│   ├── xml/
│   │   ├── XMLDecoderTemplate                   ← Concrete Template
│   │   ├── RegistrarClienteXMLDecoder           ← Concrete Product
│   │   └── RegistrarContaXMLDecoder             ← Concrete Product
│   │
│   └── textofixo/
│       ├── TextoFixoDecoderTemplate             ← Concrete Template
│       ├── RegistrarClienteTextoFixoDecoder     ← Concrete Product
│       └── RegistrarContaTextoFixoDecoder       ← Concrete Product
│
└── 🔧 service/
    └── ServicoIntegracao                         ← Client
```

## 🎨 Padrões de Projeto Aplicados

### 1. **Abstract Factory Pattern**
Fornece uma interface para criar famílias de objetos relacionados sem especificar suas classes concretas.

```java
DecoderFactory factory = DecoderFactory.fabricaParaOrigem("xml");
RegistrarClienteDecoder clienteDecoder = factory.createRegistrarClienteDecoder();
RegistrarContaDecoder contaDecoder = factory.createRegistrarContaDecoder();
```

### 2. **Singleton Pattern**
Garante que cada factory tenha apenas uma instância na aplicação.

```java
public class XMLDecoderFactory extends DecoderFactory {
    private static XMLDecoderFactory instance;
    
    private XMLDecoderFactory() {} // Construtor privado
    
    public static synchronized XMLDecoderFactory getInstance() {
        if (instance == null) {
            instance = new XMLDecoderFactory();
        }
        return instance;
    }
}
```

**Vantagens do Singleton:**
- ✅ Economia de memória
- ✅ Controle de acesso global
- ✅ Thread-safe (synchronized)
- ✅ Lazy initialization

### 3. **Template Method Pattern**
Define o esqueleto de um algoritmo, permitindo que subclasses redefinam etapas específicas.

```java
public abstract class DecoderTemplate {
    public abstract DecoderFactory getFactory(); // Hook method
    
    // Template methods
    public void registrarCliente(String textoMsg) {
        RegistrarClienteDecoder decoder = getFactory().createRegistrarClienteDecoder();
        decoder.decode(textoMsg);
    }
    
    public void registrarConta(String textoMsg) {
        RegistrarContaDecoder decoder = getFactory().createRegistrarContaDecoder();
        decoder.decode(textoMsg);
    }
}
```

## 🚀 Como Usar

### Forma 1: Usando Abstract Factory diretamente
```java
DecoderFactory factory = DecoderFactory.fabricaParaOrigem("xml");
ServicoIntegracao servico = new ServicoIntegracao(factory);
servico.processar();
```

### Forma 2: Usando Template Method
```java
DecoderTemplate template = new XMLDecoderTemplate();
template.registrarCliente("João Silva, 12345678900");
template.registrarConta("Conta Corrente 001");
```

## 🔍 Verificação do Singleton

O código demonstra que o Singleton está funcionando corretamente:

```
Factory CSV 1: com.gof.criacional.factory.CSVDecoderFactory@56c7729a
Factory CSV 2: com.gof.criacional.factory.CSVDecoderFactory@56c7729a
São a mesma instância? ✅ SIM (Singleton funcionando)
```

Os endereços de memória são idênticos, confirmando que é a mesma instância.

## 🎓 Conceitos de Java Champion

1. **Thread Safety**: Singleton implementado com `synchronized` para evitar problemas de concorrência
2. **Lazy Initialization**: Instância criada apenas quando necessária
3. **Factory Method estático**: `fabricaParaOrigem()` centraliza a criação
4. **Switch Expressions (Java 14+)**: Sintaxe moderna e concisa
5. **Separation of Concerns**: Cada padrão com responsabilidade bem definida
6. **Open/Closed Principle**: Aberto para extensão (novos formatos), fechado para modificação

## 🏆 Benefícios da Solução

✅ **Reutilização**: Factories são singletons reutilizáveis  
✅ **Manutenibilidade**: Código organizado e bem estruturado  
✅ **Extensibilidade**: Fácil adicionar novos formatos  
✅ **Performance**: Singleton evita criação desnecessária de objetos  
✅ **Flexibilidade**: Template Method permite customização do fluxo  
✅ **Testabilidade**: Fácil criar mocks das factories  

## 📊 Executando

```bash
cd abstract-factory
mvn clean compile
mvn exec:java -Dexec.mainClass="com.gof.criacional.Main"
```

## 📚 Referências

- **Gang of Four (GoF)**: Design Patterns - Elements of Reusable Object-Oriented Software
- **Effective Java (Joshua Bloch)**: Item 3 - Enforce the singleton property with a private constructor
- **Head First Design Patterns**: Abstract Factory, Template Method e Singleton
