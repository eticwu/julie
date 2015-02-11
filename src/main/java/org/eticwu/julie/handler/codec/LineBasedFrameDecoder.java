package org.eticwu.julie.handler.codec;

import java.nio.ByteBuffer;
import java.util.List;

import org.eticwu.julie.session.ISession;

public class LineBasedFrameDecoder extends FrameDecoder {

    private int maxLength;

    private boolean includeDelem = true;

    public LineBasedFrameDecoder(int maxLength, boolean includeDelem) {
	this.maxLength = maxLength;
	this.includeDelem = includeDelem;
    }

    private int searchPos(ByteBuffer buffer) {
	for (int i = buffer.position(); i < buffer.limit(); i++) {
	    byte b = buffer.get(i);
	    if (b == '\n') {
		return i;
	    } else if (b == '\r' && i < buffer.limit() - 1 && buffer.get(i + 1) == '\n') {
		return i;
	    }
	}
	return -1;
    }

    @Override
    public void decode(ISession session, ByteBuffer in, List<Object> out) {
	Object obj = decode(session, in);
	if (obj != null) {
	    out.add(obj);
	}
    }

    public Object decode(ISession session, ByteBuffer in) {
	if (in != null && in.hasRemaining()) {
	    int pos = searchPos(in);
	    if (pos == -1) {
		// TODO
		return null;
	    }
	    int cur = in.position();
	    int resultLen = pos - cur;
	    if (resultLen > maxLength) {
		// TODO
		return null;
	    }

	    int delemLen = (in.get(pos) == '\r') ? 2 : 1;
	    if (includeDelem) {
		resultLen += delemLen;
	    }
	    byte[] bytes = new byte[resultLen];
	    in.get(bytes, 0, resultLen);
	    if (!includeDelem) {
		for (int i = 0; i < delemLen; i++) {
		    in.get();// skip delem
		}
	    }
	    return ByteBuffer.wrap(bytes);
	}
	return null;
    }
}
