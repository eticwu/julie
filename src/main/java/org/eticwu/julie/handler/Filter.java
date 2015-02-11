package org.eticwu.julie.handler;

import org.eticwu.julie.session.ISession;

public abstract class Filter implements IHandler {

    private Filter next;

    @Override
    public abstract void sessionCreated(ISession session);

    @Override
    public abstract void sessionClosed(ISession session);

    @Override
    public abstract void exceptionCaught(ISession session);

    @Override
    public abstract void messageReceived(ISession session, Object message);

    @Override
    public abstract void messageSent(ISession session);

    public Filter getNextFilter() {
	return this.next;
    }

    public Filter setNextFilter(Filter filter) {
	return this.next = filter;
    }
}
