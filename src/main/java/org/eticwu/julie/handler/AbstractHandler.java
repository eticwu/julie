package org.eticwu.julie.handler;

import org.eticwu.julie.event.Event;
import org.eticwu.julie.session.ISession;

public abstract class AbstractHandler implements IHandler {

    @Override
    public boolean action(Event event, ISession session) {
	switch(event) {
	case SESSION_CREATED:
	    sessionCreated(session);
	    break;
	case MESSAGE_RECEIVED:
	    messageReceived(session);
	    break;
	case EXCEPTION_CAUGHT:
	    exceptionCaught(session);
	     break;
	case MESSAGE_SENT:
	    messageSent(session);
	    break;
	}
	return false;
    }

    @Override
    public abstract void sessionCreated(ISession session);
    
    @Override
    public abstract void sessionClosed(ISession session);

    @Override
    public abstract void exceptionCaught(ISession session);

    @Override
    public abstract void messageReceived(ISession session);

    @Override
    public abstract void messageSent(ISession session);

}
