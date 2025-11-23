package com.gof.criacional;

public class RegistrarClienteXMLDecoder extends RegistrarClienteDecoder {
  @Override
  public void decode(String dados) {
    System.out.println("🔖 [XML] Decodificando cliente: " + dados);
    System.out.println("   → Processando tags XML...");
  }
}
