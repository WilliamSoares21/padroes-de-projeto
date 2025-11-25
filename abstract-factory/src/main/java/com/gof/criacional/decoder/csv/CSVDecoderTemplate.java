package com.gof.criacional.decoder.csv;

import com.gof.criacional.decoder.DecoderTemplate;
import com.gof.criacional.factory.CSVDecoderFactory;
import com.gof.criacional.factory.DecoderFactory;

public class CSVDecoderTemplate extends DecoderTemplate {
    @Override
    public DecoderFactory getFactory() {
        return CSVDecoderFactory.getInstance();
    }
}
