package org.eticwu.julie.handler.codec;

import java.util.ArrayList;
import java.util.List;

import org.eticwu.julie.handler.Filter;
import org.eticwu.julie.session.ISession;

public abstract class ObjectDecoder<T> extends Filter {

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
    public void messageReceived(ISession session, Object message) {
	List<Object> out = new ArrayList<>();
	@SuppressWarnings("unchecked")
	T obj = (T) message;
	decode(session, obj, out);
	int size = out.size();
	if (this.getNextFilter() != null) {
	    for (int i = 0; i < size; i++) {
		session.getPipeline()
			.fireMessageReceived(this.getNextFilter(), session, out.get(i));
	    }
	}
    }

    protected abstract void decode(ISession session, T obj, List<Object> out);

    @Override
    public void messageSent(ISession session) {
    }

}
