package com.gof.criacional;

/**
 * Singleton usando Enum
 * 
 * A forma mais simples e segura de implementar Singleton em Java.
 * Garante proteção contra serialização e reflection attacks.
 * Thread-safe garantido pela JVM.
 */
public enum EnumSingleton {
    
    INSTANCE;
    
    private int contador = 0;
    
    public void executarOperacao() {
        contador++;
        System.out.println("EnumSingleton: Executando operação #" + contador);
    }
    
    public int getContador() {
        return contador;
    }
}
