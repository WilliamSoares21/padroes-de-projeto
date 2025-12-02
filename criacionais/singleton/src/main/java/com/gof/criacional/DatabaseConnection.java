package com.gof.criacional;

import java.util.HashMap;
import java.util.Map;

/**
 * Exemplo prático: Gerenciador de Conexão com Banco de Dados
 * 
 * Demonstra um caso de uso real onde Singleton é apropriado.
 * Garante uma única instância de gerenciamento de conexões.
 */
public class DatabaseConnection {
    
    private static volatile DatabaseConnection instance;
    private final Map<String, String> configuracoes;
    private boolean conectado;
    
    private DatabaseConnection() {
        this.configuracoes = new HashMap<>();
        this.conectado = false;
        System.out.println("DatabaseConnection: Inicializando gerenciador de conexões");
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    public void configurar(String chave, String valor) {
        configuracoes.put(chave, valor);
        System.out.println("Configuração adicionada: " + chave + " = " + valor);
    }
    
    public void conectar() {
        if (!conectado) {
            System.out.println("Conectando ao banco de dados...");
            System.out.println("Host: " + configuracoes.getOrDefault("host", "localhost"));
            System.out.println("Porta: " + configuracoes.getOrDefault("porta", "5432"));
            System.out.println("Database: " + configuracoes.getOrDefault("database", "app_db"));
            conectado = true;
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.out.println("Já existe uma conexão ativa");
        }
    }
    
    public void desconectar() {
        if (conectado) {
            System.out.println("Desconectando do banco de dados...");
            conectado = false;
            System.out.println("Desconectado com sucesso!");
        }
    }
    
    public boolean isConectado() {
        return conectado;
    }
    
    public void executarQuery(String query) {
        if (conectado) {
            System.out.println("Executando query: " + query);
        } else {
            System.out.println("Erro: Não há conexão ativa");
        }
    }
}
