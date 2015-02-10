package org.eticwu.julie.session;

import java.util.LinkedList;
import java.util.List;

import org.eticwu.julie.event.Event;
import org.eticwu.julie.handler.IHandler;

public class Pipeline {

    private List<IHandler> handlers = new LinkedList<>();

    private IHandler head;

    private IHandler tail;

    public void addLast(IHandler handler) {
	if (head == null) {
	    head = handler;
	    tail = handler;
	} else {
	    tail.setNextHandler(handler);
	    tail = handler;
	}
    }

    public void addLast(List<IHandler> handlers) {
	for (IHandler handler : handlers) {
	    addLast(handler);
	}
    }

    public void publish(Event event, ISession session, Object message) {
	if (head != null) {
	    head.action(event, session, message);
	}

    }
}
