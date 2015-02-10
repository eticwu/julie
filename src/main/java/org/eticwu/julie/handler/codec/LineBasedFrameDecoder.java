package org.eticwu.julie.handler.codec;

import java.nio.ByteBuffer;

public class LineBasedFrameDecoder extends DecoderHandlerAdapter {

    private int maxLength;

    private boolean includeDelem = true;

    public LineBasedFrameDecoder(int maxLength, boolean includeDelem) {
	this.maxLength = maxLength;
	this.includeDelem = includeDelem;
    }

    @Override
    public Object decode(ByteBuffer buffer) {
	if (buffer != null && buffer.hasRemaining()) {
	    int pos = searchPos(buffer);
	    if (pos >= maxLength) {
		return null;
	    }
	    int resultLen = 0;
	    if (includeDelem) {
		resultLen = pos + ((buffer.get(pos) == '\r') ? 2 : 1);
	    } else {
		resultLen = pos;
	    }
	    byte[] bytes = new byte[resultLen];
	    buffer.get(bytes, 0, resultLen);
	    return bytes;
	}
	return null;
    }

    private int searchPos(ByteBuffer buffer) {
	int length = buffer.remaining();
	int i = 0;
	for (; i < length; i++) {
	    byte b = buffer.get(i);
	    if (b == '\n') {
		break;
	    } else if (b == '\r' && i < length - 1 && buffer.get(i + 1) == '\n') {
		break;
	    }
	}
	return i;
    }

}
