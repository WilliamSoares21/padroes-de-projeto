package com.gof.criacional;

public class RegistrarContaCSVDecoder extends RegistrarContaDecoder {
  @Override
  public void decode(String dados) {
    System.out.println("💳 [CSV] Decodificando conta: " + dados);
    String[] campos = dados.split(",");
    System.out.println("   → Campos processados: " + campos.length);
  }
}
