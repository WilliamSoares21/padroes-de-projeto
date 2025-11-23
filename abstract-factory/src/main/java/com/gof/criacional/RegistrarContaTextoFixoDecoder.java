package com.gof.criacional;

public class RegistrarContaTextoFixoDecoder extends RegistrarContaDecoder {
  @Override
  public void decode(String dados) {
    System.out.println("💳 [TEXTO FIXO] Decodificando conta: " + dados);
    System.out.println("   → Extraindo posições fixas de conta...");
  }
}
