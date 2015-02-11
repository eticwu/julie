package org.eticwu.julie.handler.codec;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;

import org.eticwu.julie.session.ISession;

public class StringDecoder extends ObjectDecoder<ByteBuffer> {

    private String charset = "utf-8";

    public StringDecoder() {

    }

    public StringDecoder(String charset) {
	this.charset = charset;
    }

    @Override
    protected void decode(ISession session, ByteBuffer obj, List<Object> out) {
	if (obj.hasRemaining()) {
	    byte[] bytes = new byte[obj.remaining()];
	    obj.get(bytes);
	    obj.clear();
	    try {
		out.add(new String(bytes, charset));
	    } catch (UnsupportedEncodingException e) {
		// TODO
	    }
	}

    }
}
