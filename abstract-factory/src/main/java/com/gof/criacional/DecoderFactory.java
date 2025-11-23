package com.gof.criacional;

public abstract class DecoderFactory {
  public abstract RegistrarClienteDecoder createRegistrarClienteDecoder();

  public abstract RegistrarContaDecoder createRegistrarContaDecoder();

  public static DecoderFactory fabricaParaOrigem(String origem) {
    return switch (origem) {
      case "xml" -> new XMLDecoderFactory();
      case "csv" -> new CSVDecoderFactory();
      case "fixo" -> new TextoFixoDecoderFactory();
      default -> throw new IllegalArgumentException("Origem desconhecida: " + origem);
    };
  }
}
