# Diagrama UML - Padrão Singleton

## Estrutura Geral

```
┌─────────────────────────────────┐
│      <<Singleton>>              │
│      ClassName                  │
├─────────────────────────────────┤
│ - instance: ClassName           │
├─────────────────────────────────┤
│ - ClassName()                   │
│ + getInstance(): ClassName      │
│ + operation(): void             │
└─────────────────────────────────┘
```

## Implementações do Projeto

### EagerSingleton
```
┌─────────────────────────────────┐
│      EagerSingleton             │
├─────────────────────────────────┤
│ - INSTANCE: EagerSingleton      │
│   (static final)                │
├─────────────────────────────────┤
│ - EagerSingleton()              │
│ + getInstance(): EagerSingleton │
│ + executarOperacao(): void      │
└─────────────────────────────────┘
```

### LazySingleton
```
┌─────────────────────────────────┐
│      LazySingleton              │
├─────────────────────────────────┤
│ - instance: LazySingleton       │
│   (static)                      │
├─────────────────────────────────┤
│ - LazySingleton()               │
│ + getInstance(): LazySingleton  │
│ + executarOperacao(): void      │
└─────────────────────────────────┘
```

### ThreadSafeSingleton
```
┌──────────────────────────────────────┐
│      ThreadSafeSingleton             │
├──────────────────────────────────────┤
│ - instance: ThreadSafeSingleton      │
│   (static volatile)                  │
├──────────────────────────────────────┤
│ - ThreadSafeSingleton()              │
│ + getInstance(): ThreadSafeSingleton │
│ + executarOperacao(): void           │
└──────────────────────────────────────┘
```

### BillPughSingleton
```
┌────────────────────────────────────┐
│      BillPughSingleton             │
├────────────────────────────────────┤
│ <<inner class>>                    │
│ + SingletonHolder                  │
│   - INSTANCE: BillPughSingleton    │
├────────────────────────────────────┤
│ - BillPughSingleton()              │
│ + getInstance(): BillPughSingleton │
│ + executarOperacao(): void         │
└────────────────────────────────────┘
```

### DatabaseConnection (Exemplo Prático)
```
┌─────────────────────────────────────┐
│      DatabaseConnection             │
├─────────────────────────────────────┤
│ - instance: DatabaseConnection      │
│   (static volatile)                 │
│ - configuracoes: Map<String,String> │
│ - conectado: boolean                │
├─────────────────────────────────────┤
│ - DatabaseConnection()              │
│ + getInstance(): DatabaseConnection │
│ + configurar(String, String): void  │
│ + conectar(): void                  │
│ + desconectar(): void               │
│ + isConectado(): boolean            │
│ + executarQuery(String): void       │
└─────────────────────────────────────┘
```

## Relacionamentos

```
   Cliente
      │
      │ usa
      ▼
 getInstance()
      │
      │ retorna
      ▼
   Instância Única
```

## Sequência de Criação

### Eager Initialization
```
ClassLoader  →  Carrega Classe  →  Cria INSTANCE  →  Pronta para uso
```

### Lazy Initialization
```
Cliente  →  getInstance()  →  Verifica null  →  Cria instance  →  Retorna
         →  getInstance()  →  Retorna existente
```

### Double-Checked Locking
```
Thread 1  →  getInstance()  →  Check 1  →  Lock  →  Check 2  →  Cria  →  Unlock
Thread 2  →  getInstance()  →  Retorna existente
```
