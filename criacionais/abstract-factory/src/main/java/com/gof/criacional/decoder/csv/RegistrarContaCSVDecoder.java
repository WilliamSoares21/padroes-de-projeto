package com.gof.criacional.decoder.csv;

import com.gof.criacional.decoder.RegistrarContaDecoder;

public class RegistrarContaCSVDecoder extends RegistrarContaDecoder {
  @Override
  public void decode(String dados) {
    System.out.println("💳 [CSV] Decodificando conta: " + dados);
    String[] campos = dados.split(",");
    System.out.println("   → Campos processados: " + campos.length);
  }
}
