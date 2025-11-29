package com.gof.criacional;

/**
 * Bill Pugh Singleton (Initialization-on-demand holder idiom)
 * 
 * Considerada a melhor abordagem para implementar Singleton em Java.
 * Combina lazy initialization com thread-safety sem usar sincronização.
 * A classe interna estática é carregada apenas quando referenciada.
 */
public class BillPughSingleton {
    
    private BillPughSingleton() {
        System.out.println("BillPughSingleton: Instância criada");
    }
    
    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public void executarOperacao() {
        System.out.println("BillPughSingleton: Executando operação");
    }
}
