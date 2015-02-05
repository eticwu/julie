package org.eticwu.julie.handler.codec;

import java.nio.ByteBuffer;

import org.eticwu.julie.handler.AbstractHandler;
import org.eticwu.julie.session.ISession;

public class DecoderHandlerAdapter extends AbstractHandler {

    @Override
    public void sessionCreated(ISession session) {

    }

    @Override
    public void sessionClosed(ISession session) {

    }

    @Override
    public void exceptionCaught(ISession session) {

    }

    @Override
    public void messageReceived(ISession session) {
	Object object = decode(session.getByteBuffer());
    }

    @Override
    public void messageSent(ISession session) {

    }
    
    public Object decode(ByteBuffer buffer) {
	return null;
    }

}
