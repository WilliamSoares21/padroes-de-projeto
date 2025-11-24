package com.gof.criacional.factory;

import com.gof.criacional.decoder.RegistrarClienteDecoder;
import com.gof.criacional.decoder.RegistrarContaDecoder;
import com.gof.criacional.decoder.xml.RegistrarClienteXMLDecoder;
import com.gof.criacional.decoder.xml.RegistrarContaXMLDecoder;

public class XMLDecoderFactory extends DecoderFactory {

  @Override
  public RegistrarClienteDecoder createRegistrarClienteDecoder() {
    return new RegistrarClienteXMLDecoder();
  }

  @Override
  public RegistrarContaDecoder createRegistrarContaDecoder() {
    return new RegistrarContaXMLDecoder();
  }

}
