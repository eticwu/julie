package org.eticwu.julie.handler.codec;

import java.nio.ByteBuffer;

import org.eticwu.julie.event.Event;
import org.eticwu.julie.handler.AbstractHandler;
import org.eticwu.julie.handler.IHandler;
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
    public void messageReceived(Event event, ISession session, Object message) {
	if (!(message instanceof ByteBuffer)) {
	    return;
	}
	Object object = decode((ByteBuffer) message);
	IHandler next = getNextHandler();
	if (next != null) {
	    next.action(event, session, object);
	}
    }

    @Override
    public void messageSent(ISession session) {

    }

    public Object decode(ByteBuffer buffer) {
	return null;
    }

}
