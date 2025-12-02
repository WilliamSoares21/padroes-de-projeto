package com.gof.criacional.factory;

import com.gof.criacional.decoder.RegistrarClienteDecoder;
import com.gof.criacional.decoder.RegistrarContaDecoder;

public abstract class DecoderFactory {
  public abstract RegistrarClienteDecoder createRegistrarClienteDecoder();

  public abstract RegistrarContaDecoder createRegistrarContaDecoder();

  public static DecoderFactory fabricaParaOrigem(String origem) {
    return switch (origem) {
      case "xml" -> XMLDecoderFactory.getInstance();
      case "csv" -> CSVDecoderFactory.getInstance();
      case "fixo" -> TextoFixoDecoderFactory.getInstance();
      default -> throw new IllegalArgumentException("Origem desconhecida: " + origem);
    };
  }
}
