package com.gof.criacional.factory;

import com.gof.criacional.decoder.RegistrarClienteDecoder;
import com.gof.criacional.decoder.RegistrarContaDecoder;
import com.gof.criacional.decoder.textofixo.RegistrarClienteTextoFixoDecoder;
import com.gof.criacional.decoder.textofixo.RegistrarContaTextoFixoDecoder;

public class TextoFixoDecoderFactory extends DecoderFactory {
  private static TextoFixoDecoderFactory instance;

  private TextoFixoDecoderFactory() {
    // Construtor privado para Singleton
  }

  public static synchronized TextoFixoDecoderFactory getInstance() {
    if (instance == null) {
      instance = new TextoFixoDecoderFactory();
    }
    return instance;
  }

  @Override
  public RegistrarClienteDecoder createRegistrarClienteDecoder() {
    return new RegistrarClienteTextoFixoDecoder();
  }

  @Override
  public RegistrarContaDecoder createRegistrarContaDecoder() {
    return new RegistrarContaTextoFixoDecoder();
  }

}
