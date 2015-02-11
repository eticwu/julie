package org.eticwu.julie.handler;

import org.eticwu.julie.session.ISession;

public interface IHandler {

    void sessionCreated(ISession session);

    void sessionClosed(ISession session);

    void exceptionCaught(ISession session);

    void messageReceived(ISession session, Object message);

    void messageSent(ISession session);

}
