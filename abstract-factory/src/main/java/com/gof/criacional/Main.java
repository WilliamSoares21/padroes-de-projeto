package com.gof.criacional;

public class Main {
  public static void main(String[] args) {
    // Exemplo 1: Usando CSV Factory
    System.out.println("=== Processando com CSV ===");
    processarIntegracao("csv");
    
    // Exemplo 2: Usando XML Factory
    System.out.println("\n=== Processando com XML ===");
    processarIntegracao("xml");
    
    // Exemplo 3: Usando Texto Fixo Factory
    System.out.println("\n=== Processando com Texto Fixo ===");
    processarIntegracao("fixo");
    
    // Exemplo 4: Demonstrando tratamento de erro
    System.out.println("\n=== Testando origem inválida ===");
    try {
      processarIntegracao("json");
    } catch (IllegalArgumentException e) {
      System.out.println("Erro capturado: " + e.getMessage());
    }
  }
  
  private static void processarIntegracao(String origem) {
    DecoderFactory factory = DecoderFactory.fabricaParaOrigem(origem);
    ServicoIntegracao servico = new ServicoIntegracao(factory);
    servico.processar();
  }
}
