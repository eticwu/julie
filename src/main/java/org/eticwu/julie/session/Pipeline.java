package org.eticwu.julie.session;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.eticwu.julie.event.IEvent;
import org.eticwu.julie.handler.IHandler;

public class Pipeline {

    private List<IHandler> handlers = new LinkedList<>();

    public void addLast(IHandler handler) {
	this.handlers.add(handler);
    }

    public void publish(IEvent event) {
	if (CollectionUtils.isNotEmpty(handlers)) {
	    for (IHandler handler : handlers) {

	    }
	}
    }
}
