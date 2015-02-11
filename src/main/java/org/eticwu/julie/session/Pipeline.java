package org.eticwu.julie.session;

import java.util.LinkedList;
import java.util.List;

import org.eticwu.julie.handler.Filter;
import org.eticwu.julie.handler.IHandler;

public class Pipeline {

    private List<IHandler> handlers = new LinkedList<>();

    private Filter head;

    private Filter tail;

    public void addLast(Filter handler) {
	if (head == null) {
	    head = handler;
	    tail = handler;
	} else {
	    tail.setNextFilter(handler);
	    tail = handler;
	}
    }

    public void addLast(List<Filter> filters) {
	for (Filter filter : filters) {
	    addLast(filter);
	}
    }

    public void fireMessageReceived(IHandler handler, ISession session, Object message) {
	if (handler == null) {
	    return;
	}
	handler.messageReceived(session, message);
    }

    public IHandler getHead() {
	return this.head;
    }
}
