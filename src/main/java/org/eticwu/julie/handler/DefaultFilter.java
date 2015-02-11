package org.eticwu.julie.handler;

import org.eticwu.julie.session.ISession;

public class DefaultFilter extends Filter {

    @Override
    public void sessionCreated(ISession session) {
	// TODO　sessionCreated
    }

    @Override
    public void sessionClosed(ISession session) {
	// TODO　sessionClosed
    }

    @Override
    public void exceptionCaught(ISession session) {
	// TODO　exceptionCaught
    }

    @Override
    public void messageReceived(ISession session, Object message) {
	session.getPipeline().fireMessageReceived(getNextFilter(), session, message);
    }

    @Override
    public void messageSent(ISession session) {
	// TODO　fireMessageSent
    }

}
