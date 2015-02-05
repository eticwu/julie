package org.eticwu.julie.handler;

import org.eticwu.julie.event.Event;
import org.eticwu.julie.session.ISession;

public interface IHandler {

    boolean action(Event event, ISession session);

    void sessionCreated(ISession session);
    
    void sessionClosed(ISession session);
 
    void exceptionCaught(ISession session);
 
    void messageReceived(ISession session);
 
    void messageSent(ISession session);
}
