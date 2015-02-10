package org.eticwu.julie.handler;

import org.eticwu.julie.event.Event;
import org.eticwu.julie.session.ISession;

public interface IHandler {

    boolean action(Event event, ISession session, Object message);

    void sessionCreated(ISession session);

    void sessionClosed(ISession session);

    void exceptionCaught(ISession session);

    void messageReceived(Event event, ISession session, Object message);

    void messageSent(ISession session);

    IHandler getNextHandler();

    IHandler setNextHandler(IHandler handler);
}
