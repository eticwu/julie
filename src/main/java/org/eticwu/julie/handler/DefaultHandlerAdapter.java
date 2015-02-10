package org.eticwu.julie.handler;

import org.eticwu.julie.event.Event;
import org.eticwu.julie.session.ISession;

public class DefaultHandlerAdapter extends AbstractHandler {

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
    }

    @Override
    public void messageSent(ISession session) {
    }

}
