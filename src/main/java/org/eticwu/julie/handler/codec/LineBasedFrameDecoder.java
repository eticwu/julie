package org.eticwu.julie.handler.codec;

import java.nio.ByteBuffer;

public class LineBasedFrameDecoder extends DecoderHandlerAdapter {
    
    private int maxLength;
    
    public LineBasedFrameDecoder(int maxLength){
	this.maxLength = maxLength;
    }

    @Override
    public Object decode(ByteBuffer buffer) {
	if(buffer != null && buffer.hasRemaining()){
	    int pos = searchPos(buffer);
	    if(pos >= maxLength){
		return null;
	    }
	    byte[] bytes = new byte[maxLength];
	  //TODO pos + 1 or pos
	    buffer.get(bytes, 0, pos);
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
