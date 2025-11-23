package com.gof.criacional;

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
