package com.gof.criacional.factory;

import com.gof.criacional.decoder.RegistrarClienteDecoder;
import com.gof.criacional.decoder.RegistrarContaDecoder;
import com.gof.criacional.decoder.textofixo.RegistrarClienteTextoFixoDecoder;
import com.gof.criacional.decoder.textofixo.RegistrarContaTextoFixoDecoder;

public class TextoFixoDecoderFactory extends DecoderFactory {

  @Override
  public RegistrarClienteDecoder createRegistrarClienteDecoder() {
    return new RegistrarClienteTextoFixoDecoder();
  }

  @Override
  public RegistrarContaDecoder createRegistrarContaDecoder() {
    return new RegistrarContaTextoFixoDecoder();
  }

}
