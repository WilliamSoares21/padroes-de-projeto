package com.gof.criacional.decoder.textofixo;

import com.gof.criacional.decoder.DecoderTemplate;
import com.gof.criacional.factory.DecoderFactory;
import com.gof.criacional.factory.TextoFixoDecoderFactory;

public class TextoFixoDecoderTemplate extends DecoderTemplate {
    @Override
    public DecoderFactory getFactory() {
        return TextoFixoDecoderFactory.getInstance();
    }
}
