package com.gof.criacional.decoder;

import com.gof.criacional.factory.DecoderFactory;

public abstract class DecoderTemplate {
    public abstract DecoderFactory getFactory();

    // Template Method - define o algoritmo de processamento
    public void registrarCliente(String textoMsg) {
        RegistrarClienteDecoder decoder = getFactory().createRegistrarClienteDecoder();
        decoder.decode(textoMsg);
    }

    public void registrarConta(String textoMsg) {
        RegistrarContaDecoder decoder = getFactory().createRegistrarContaDecoder();
        decoder.decode(textoMsg);
    }
}
