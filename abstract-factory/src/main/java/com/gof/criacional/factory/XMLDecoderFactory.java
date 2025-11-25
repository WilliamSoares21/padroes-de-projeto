package com.gof.criacional.factory;

import com.gof.criacional.decoder.RegistrarClienteDecoder;
import com.gof.criacional.decoder.RegistrarContaDecoder;
import com.gof.criacional.decoder.xml.RegistrarClienteXMLDecoder;
import com.gof.criacional.decoder.xml.RegistrarContaXMLDecoder;

public class XMLDecoderFactory extends DecoderFactory {
  private static XMLDecoderFactory instance;

  private XMLDecoderFactory() {
    // Construtor privado para Singleton
  }

  public static synchronized XMLDecoderFactory getInstance() {
    if (instance == null) {
      instance = new XMLDecoderFactory();
    }
    return instance;
  }

  @Override
  public RegistrarClienteDecoder createRegistrarClienteDecoder() {
    return new RegistrarClienteXMLDecoder();
  }

  @Override
  public RegistrarContaDecoder createRegistrarContaDecoder() {
    return new RegistrarContaXMLDecoder();
  }

}
