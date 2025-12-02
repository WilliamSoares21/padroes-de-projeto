package com.gof.criacional;

/**
 * Thread-Safe Singleton com Double-Checked Locking
 * 
 * Combina lazy initialization com thread-safety.
 * Usa sincronização apenas na primeira criação da instância,
 * evitando overhead de sincronização em chamadas subsequentes.
 */
public class ThreadSafeSingleton {
    
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {
        System.out.println("ThreadSafeSingleton: Instância criada");
    }
    
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
    
    public void executarOperacao() {
        System.out.println("ThreadSafeSingleton: Executando operação");
    }
}
