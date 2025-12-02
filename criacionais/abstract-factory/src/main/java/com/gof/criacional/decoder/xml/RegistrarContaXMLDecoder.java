package com.gof.criacional.decoder.xml;

import com.gof.criacional.decoder.RegistrarContaDecoder;

public class RegistrarContaXMLDecoder extends RegistrarContaDecoder {
  @Override
  public void decode(String dados) {
    System.out.println("💳 [XML] Decodificando conta: " + dados);
    System.out.println("   → Processando tags XML de conta...");
  }
}
