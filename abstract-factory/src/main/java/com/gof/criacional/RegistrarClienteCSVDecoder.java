package com.gof.criacional;

public class RegistrarClienteCSVDecoder extends RegistrarClienteDecoder {
  @Override
  public void decode(String dados) {
    System.out.println("📄 [CSV] Decodificando cliente: " + dados);
    String[] campos = dados.split(",");
    System.out.println("   → Campos processados: " + campos.length);
  }
}
