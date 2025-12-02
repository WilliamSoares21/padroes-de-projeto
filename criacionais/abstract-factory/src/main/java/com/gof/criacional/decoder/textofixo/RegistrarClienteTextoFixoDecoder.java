package com.gof.criacional.decoder.textofixo;

import com.gof.criacional.decoder.RegistrarClienteDecoder;

public class RegistrarClienteTextoFixoDecoder extends RegistrarClienteDecoder {
  @Override
  public void decode(String dados) {
    System.out.println("📝 [TEXTO FIXO] Decodificando cliente: " + dados);
    System.out.println("   → Extraindo posições fixas...");
  }
}
