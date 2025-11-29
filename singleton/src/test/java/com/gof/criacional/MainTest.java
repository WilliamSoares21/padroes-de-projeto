package com.gof.criacional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Padrão Singleton")
class MainTest {
    
    @Test
    @DisplayName("EagerSingleton deve retornar sempre a mesma instância")
    void testEagerSingletonInstance() {
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        
        assertSame(instance1, instance2);
    }
    
    @Test
    @DisplayName("LazySingleton deve retornar sempre a mesma instância")
    void testLazySingletonInstance() {
        LazySingleton instance1 = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        
        assertSame(instance1, instance2);
    }
    
    @Test
    @DisplayName("ThreadSafeSingleton deve retornar sempre a mesma instância")
    void testThreadSafeSingletonInstance() {
        ThreadSafeSingleton instance1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton instance2 = ThreadSafeSingleton.getInstance();
        
        assertSame(instance1, instance2);
    }
    
    @Test
    @DisplayName("BillPughSingleton deve retornar sempre a mesma instância")
    void testBillPughSingletonInstance() {
        BillPughSingleton instance1 = BillPughSingleton.getInstance();
        BillPughSingleton instance2 = BillPughSingleton.getInstance();
        
        assertSame(instance1, instance2);
    }
    
    @Test
    @DisplayName("ThreadSafeSingleton deve ser thread-safe")
    void testThreadSafeSingletonConcurrency() throws InterruptedException {
        final int threadCount = 100;
        final ThreadSafeSingleton[] instances = new ThreadSafeSingleton[threadCount];
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.execute(() -> {
                instances[index] = ThreadSafeSingleton.getInstance();
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        ThreadSafeSingleton firstInstance = instances[0];
        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i]);
        }
    }
    
    @Test
    @DisplayName("DatabaseConnection deve manter o estado entre chamadas")
    void testDatabaseConnectionState() {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.configurar("host", "localhost");
        db1.configurar("porta", "5432");
        db1.conectar();
        
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        assertSame(db1, db2);
        assertTrue(db2.isConectado());
        
        db2.desconectar();
        assertFalse(db1.isConectado());
    }
    
    @Test
    @DisplayName("DatabaseConnection deve ser thread-safe")
    void testDatabaseConnectionConcurrency() throws InterruptedException {
        final int threadCount = 50;
        final DatabaseConnection[] instances = new DatabaseConnection[threadCount];
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.execute(() -> {
                instances[index] = DatabaseConnection.getInstance();
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        DatabaseConnection firstInstance = instances[0];
        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i]);
        }
    }
    
    @Test
    @DisplayName("EnumSingleton deve manter estado consistente")
    void testEnumSingletonState() {
        EnumSingleton.INSTANCE.executarOperacao();
        int count1 = EnumSingleton.INSTANCE.getContador();
        
        EnumSingleton.INSTANCE.executarOperacao();
        int count2 = EnumSingleton.INSTANCE.getContador();
        
        assertEquals(count1 + 1, count2);
    }
}
