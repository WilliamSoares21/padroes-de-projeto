package com.gof.criacional.decoder.xml;

import com.gof.criacional.decoder.DecoderTemplate;
import com.gof.criacional.factory.DecoderFactory;
import com.gof.criacional.factory.XMLDecoderFactory;

public class XMLDecoderTemplate extends DecoderTemplate {
    @Override
    public DecoderFactory getFactory() {
        return XMLDecoderFactory.getInstance();
    }
}
