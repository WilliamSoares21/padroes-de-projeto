package com.gof.criacional.service;

import com.gof.criacional.decoder.RegistrarClienteDecoder;
import com.gof.criacional.decoder.RegistrarContaDecoder;
import com.gof.criacional.factory.DecoderFactory;

public class ServicoIntegracao {
  private DecoderFactory factory;

  public ServicoIntegracao(DecoderFactory factory) {
    this.factory = factory;
  }

  public void processar() {
    // Cria os decoders usando a factory
    RegistrarClienteDecoder clienteDecoder = factory.createRegistrarClienteDecoder();
    RegistrarContaDecoder contaDecoder = factory.createRegistrarContaDecoder();

    // Processa os dados
    clienteDecoder.decode("dados do cliente...");
    contaDecoder.decode("dados da conta...");
  }
}
