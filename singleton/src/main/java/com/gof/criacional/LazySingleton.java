package com.gof.criacional;

/**
 * Lazy Initialization Singleton
 * 
 * A instância é criada apenas quando solicitada pela primeira vez.
 * Economiza recursos, mas NÃO é thread-safe.
 * Adequado apenas para ambientes single-thread.
 */
public class LazySingleton {
    
    private static LazySingleton instance;
    
    private LazySingleton() {
        System.out.println("LazySingleton: Instância criada");
    }
    
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
    
    public void executarOperacao() {
        System.out.println("LazySingleton: Executando operação");
    }
}
