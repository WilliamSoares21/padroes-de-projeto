package com.gof.criacional;

/**
 * Eager Initialization Singleton
 * 
 * A instância é criada no momento do carregamento da classe.
 * Simples e thread-safe por padrão, mas pode desperdiçar recursos
 * se a instância nunca for utilizada.
 */
public class EagerSingleton {
    
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    private EagerSingleton() {
        System.out.println("EagerSingleton: Instância criada");
    }
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
    
    public void executarOperacao() {
        System.out.println("EagerSingleton: Executando operação");
    }
}
