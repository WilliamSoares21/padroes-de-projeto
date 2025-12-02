package com.gof.criacional;

import com.gof.criacional.decoder.DecoderTemplate;
import com.gof.criacional.decoder.csv.CSVDecoderTemplate;
import com.gof.criacional.decoder.textofixo.TextoFixoDecoderTemplate;
import com.gof.criacional.decoder.xml.XMLDecoderTemplate;
import com.gof.criacional.factory.DecoderFactory;
import com.gof.criacional.service.ServicoIntegracao;

public class Main {
  public static void main(String[] args) {
    System.out.println("=== Sistema de Decodificação ===\n");

    // Demonstração 1: Usando Abstract Factory com Singleton
    System.out.println("=== DEMO 1: Abstract Factory + Singleton ===");
    processarComFactory("csv");
    processarComFactory("xml");
    processarComFactory("fixo");

    // Demonstração 2: Usando Template Method
    System.out.println("\n=== DEMO 2: Template Method Pattern ===");
    processarComTemplateMethod();

    // Demonstração 3: Verificando Singleton
    System.out.println("\n=== DEMO 3: Verificando Singleton ===");
    verificarSingleton();

    // Demonstração 4: Tratamento de erro
    System.out.println("\n=== DEMO 4: Tratamento de Erro ===");
    try {
      processarComFactory("json");
    } catch (IllegalArgumentException e) {
      System.out.println("❌ Erro capturado: " + e.getMessage());
    }
  }

  private static void processarComFactory(String origem) {
    System.out.println("\n→ Processando com origem: " + origem.toUpperCase());
    DecoderFactory factory = DecoderFactory.fabricaParaOrigem(origem);
    ServicoIntegracao servico = new ServicoIntegracao(factory);
    servico.processar();
  }

  private static void processarComTemplateMethod() {
    // Template Method define o algoritmo, subclasses fornecem as factories
    System.out.println("\n→ Usando XML Template:");
    DecoderTemplate xmlTemplate = new XMLDecoderTemplate();
    xmlTemplate.registrarCliente("João Silva, 12345678900");
    xmlTemplate.registrarConta("Conta Corrente 001");

    System.out.println("\n→ Usando CSV Template:");
    DecoderTemplate csvTemplate = new CSVDecoderTemplate();
    csvTemplate.registrarCliente("Maria Santos,98765432100");
    csvTemplate.registrarConta("Conta Poupança 002");

    System.out.println("\n→ Usando Texto Fixo Template:");
    DecoderTemplate fixoTemplate = new TextoFixoDecoderTemplate();
    fixoTemplate.registrarCliente("Pedro Costa       45612378900");
    fixoTemplate.registrarConta("Conta Salário 003             ");
  }

  private static void verificarSingleton() {
    DecoderFactory factory1 = DecoderFactory.fabricaParaOrigem("csv");
    DecoderFactory factory2 = DecoderFactory.fabricaParaOrigem("csv");
    
    System.out.println("Factory CSV 1: " + factory1);
    System.out.println("Factory CSV 2: " + factory2);
    System.out.println("São a mesma instância? " + (factory1 == factory2 ? "✅ SIM (Singleton funcionando)" : "❌ NÃO"));
    
    DecoderFactory xmlFactory1 = DecoderFactory.fabricaParaOrigem("xml");
    DecoderFactory xmlFactory2 = DecoderFactory.fabricaParaOrigem("xml");
    
    System.out.println("\nFactory XML 1: " + xmlFactory1);
    System.out.println("Factory XML 2: " + xmlFactory2);
    System.out.println("São a mesma instância? " + (xmlFactory1 == xmlFactory2 ? "✅ SIM (Singleton funcionando)" : "❌ NÃO"));
  }
}
