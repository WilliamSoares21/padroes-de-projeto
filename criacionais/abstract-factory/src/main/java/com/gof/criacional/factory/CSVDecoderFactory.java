package com.gof.criacional.factory;

import com.gof.criacional.decoder.RegistrarClienteDecoder;
import com.gof.criacional.decoder.RegistrarContaDecoder;
import com.gof.criacional.decoder.csv.RegistrarClienteCSVDecoder;
import com.gof.criacional.decoder.csv.RegistrarContaCSVDecoder;

public class CSVDecoderFactory extends DecoderFactory {
  private static CSVDecoderFactory instance;

  private CSVDecoderFactory() {
    // Construtor privado para Singleton
  }

  public static synchronized CSVDecoderFactory getInstance() {
    if (instance == null) {
      instance = new CSVDecoderFactory();
    }
    return instance;
  }

  @Override
  public RegistrarClienteDecoder createRegistrarClienteDecoder() {
    return new RegistrarClienteCSVDecoder();
  }

  @Override
  public RegistrarContaDecoder createRegistrarContaDecoder() {
    return new RegistrarContaCSVDecoder();
  }

}
