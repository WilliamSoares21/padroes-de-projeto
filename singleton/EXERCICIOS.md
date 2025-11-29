# Exercícios Práticos - Padrão Singleton

## Exercício 1: Implementação Básica

Implemente um `ConfigurationManager` usando o padrão Singleton que:
- Armazene configurações em um Map
- Permita adicionar e recuperar configurações
- Use a implementação Bill Pugh

<details>
<summary>Solução</summary>

```java
public class ConfigurationManager {
    
    private final Map<String, String> configs = new HashMap<>();
    
    private ConfigurationManager() {}
    
    private static class Holder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }
    
    public static ConfigurationManager getInstance() {
        return Holder.INSTANCE;
    }
    
    public void setConfig(String key, String value) {
        configs.put(key, value);
    }
    
    public String getConfig(String key) {
        return configs.get(key);
    }
}
```
</details>

## Exercício 2: Logger Singleton

Crie um sistema de logging simples usando Singleton que:
- Escreva logs em uma lista interna
- Tenha métodos para diferentes níveis (INFO, WARNING, ERROR)
- Permita recuperar todos os logs

<details>
<summary>Solução</summary>

```java
public class Logger {
    
    private final List<String> logs = new ArrayList<>();
    
    private Logger() {}
    
    private static class Holder {
        private static final Logger INSTANCE = new Logger();
    }
    
    public static Logger getInstance() {
        return Holder.INSTANCE;
    }
    
    public void info(String message) {
        log("INFO", message);
    }
    
    public void warning(String message) {
        log("WARNING", message);
    }
    
    public void error(String message) {
        log("ERROR", message);
    }
    
    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        logs.add(String.format("[%s] %s: %s", timestamp, level, message));
    }
    
    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}
```
</details>

## Exercício 3: Cache Manager

Implemente um `CacheManager` thread-safe que:
- Armazene pares chave-valor em um Map
- Tenha tempo de expiração para cada entrada
- Limpe automaticamente entradas expiradas

<details>
<summary>Solução</summary>

```java
public class CacheManager {
    
    private static volatile CacheManager instance;
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    
    private static class CacheEntry {
        String value;
        long expirationTime;
        
        CacheEntry(String value, long ttl) {
            this.value = value;
            this.expirationTime = System.currentTimeMillis() + ttl;
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }
    
    private CacheManager() {}
    
    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }
    
    public void put(String key, String value, long ttlMillis) {
        cache.put(key, new CacheEntry(value, ttlMillis));
    }
    
    public String get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null && !entry.isExpired()) {
            return entry.value;
        }
        cache.remove(key);
        return null;
    }
    
    public void clearExpired() {
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
}
```
</details>

## Exercício 4: Análise de Código

Identifique os problemas na implementação abaixo:

```java
public class BadSingleton {
    private static BadSingleton instance;
    
    public BadSingleton() {
        System.out.println("Creating instance");
    }
    
    public static BadSingleton getInstance() {
        if (instance == null) {
            instance = new BadSingleton();
        }
        return instance;
    }
}
```

<details>
<summary>Problemas Identificados</summary>

1. **Construtor público:** Permite criar múltiplas instâncias fora do padrão
2. **Não é thread-safe:** Múltiplas threads podem criar várias instâncias
3. **Sem volatile:** Em ambientes multi-thread pode haver problemas de visibilidade
4. **Lazy unsafe:** Sem sincronização adequada

**Correção:**
```java
public class GoodSingleton {
    private static volatile GoodSingleton instance;
    
    private GoodSingleton() {
        System.out.println("Creating instance");
    }
    
    public static GoodSingleton getInstance() {
        if (instance == null) {
            synchronized (GoodSingleton.class) {
                if (instance == null) {
                    instance = new GoodSingleton();
                }
            }
        }
        return instance;
    }
}
```
</details>

## Exercício 5: Comparação de Implementações

Compare o desempenho entre:
1. EagerSingleton
2. LazySingleton
3. ThreadSafeSingleton
4. BillPughSingleton

Crie um teste que:
- Chame getInstance() 1.000.000 de vezes
- Meça o tempo de execução
- Compare os resultados

<details>
<summary>Código de Teste</summary>

```java
public class PerformanceTest {
    
    private static final int ITERATIONS = 1_000_000;
    
    @Test
    void testPerformance() {
        measurePerformance("Eager", EagerSingleton::getInstance);
        measurePerformance("Lazy", LazySingleton::getInstance);
        measurePerformance("ThreadSafe", ThreadSafeSingleton::getInstance);
        measurePerformance("BillPugh", BillPughSingleton::getInstance);
    }
    
    private void measurePerformance(String name, Supplier<?> supplier) {
        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            supplier.get();
        }
        long duration = System.nanoTime() - start;
        System.out.printf("%s: %.2f ms%n", name, duration / 1_000_000.0);
    }
}
```
</details>

## Desafio Final: Connection Pool

Implemente um `ConnectionPool` Singleton que:
- Gerencie um pool de 10 conexões
- Forneça conexões disponíveis
- Retorne conexões ao pool após uso
- Seja thread-safe
- Bloqueie quando não houver conexões disponíveis

Este é um exercício avançado que combina Singleton com Object Pool pattern.
