package com.gof.criacional;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DO PADRÃO SINGLETON ===\n");
        
        demonstrarEagerSingleton();
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        demonstrarLazySingleton();
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        demonstrarThreadSafeSingleton();
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        demonstrarBillPughSingleton();
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        demonstrarEnumSingleton();
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        demonstrarDatabaseConnection();
    }
    
    private static void demonstrarEagerSingleton() {
        System.out.println("1. EAGER SINGLETON (Inicialização Antecipada)");
        System.out.println("   Instância criada no carregamento da classe\n");
        
        EagerSingleton singleton1 = EagerSingleton.getInstance();
        EagerSingleton singleton2 = EagerSingleton.getInstance();
        
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
        singleton1.executarOperacao();
    }
    
    private static void demonstrarLazySingleton() {
        System.out.println("2. LAZY SINGLETON (Inicialização Preguiçosa)");
        System.out.println("   Instância criada apenas no primeiro uso\n");
        
        System.out.println("Antes de chamar getInstance()...");
        LazySingleton singleton1 = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();
        
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
        singleton1.executarOperacao();
    }
    
    private static void demonstrarThreadSafeSingleton() {
        System.out.println("3. THREAD-SAFE SINGLETON (Double-Checked Locking)");
        System.out.println("   Seguro para ambientes multi-thread\n");
        
        ThreadSafeSingleton singleton1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance();
        
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
        singleton1.executarOperacao();
    }
    
    private static void demonstrarBillPughSingleton() {
        System.out.println("4. BILL PUGH SINGLETON (Initialization-on-demand)");
        System.out.println("   Melhor abordagem: lazy + thread-safe sem sincronização\n");
        
        System.out.println("Antes de chamar getInstance()...");
        BillPughSingleton singleton1 = BillPughSingleton.getInstance();
        BillPughSingleton singleton2 = BillPughSingleton.getInstance();
        
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
        singleton1.executarOperacao();
    }
    
    private static void demonstrarEnumSingleton() {
        System.out.println("5. ENUM SINGLETON (Recomendado por Joshua Bloch)");
        System.out.println("   Mais simples e segura: protegido contra serialização e reflection\n");
        
        EnumSingleton.INSTANCE.executarOperacao();
        EnumSingleton.INSTANCE.executarOperacao();
        
        System.out.println("Contador total: " + EnumSingleton.INSTANCE.getContador());
    }
    
    private static void demonstrarDatabaseConnection() {
        System.out.println("5. EXEMPLO PRÁTICO: Database Connection Manager");
        System.out.println("   Caso de uso real do padrão Singleton\n");
        
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.configurar("host", "localhost");
        db1.configurar("porta", "5432");
        db1.configurar("database", "singleton_demo");
        db1.conectar();
        
        System.out.println();
        db1.executarQuery("SELECT * FROM usuarios");
        
        System.out.println("\nObtendo a mesma instância em outro ponto da aplicação:");
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("db1 == db2? " + (db1 == db2));
        System.out.println("Conexão ativa? " + db2.isConectado());
        
        db2.executarQuery("INSERT INTO usuarios (nome) VALUES ('João')");
        
        System.out.println();
        db2.desconectar();
    }
}
