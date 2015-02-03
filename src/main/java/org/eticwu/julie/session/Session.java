package org.eticwu.julie.session;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.eticwu.julie.event.IService;
import org.eticwu.julie.filter.IFilter;
import org.eticwu.julie.handler.ReadCompletionHandler;
import org.eticwu.julie.handler.WriteCompletionHandler;

public class Session<T> implements ISession<T> {

    private AsynchronousSocketChannel channel;

    private Integer id;

    private List<IFilter> filters;

    private IService service;

    private ReadCompletionHandler readHandler;

    private WriteCompletionHandler writeHandler;

    private ByteBuffer byteBuf;

    Session(Integer id, AsynchronousSocketChannel channel) {
	this.id = id;
	this.channel = channel;
	this.readHandler = new ReadCompletionHandler();
	this.writeHandler = new WriteCompletionHandler();
    }

    @Override
    public void sessionCreated() {
	if (CollectionUtils.isNotEmpty(filters)) {
	    for (IFilter filter : filters) {
		filter.onCreated();
	    }
	}
	if (service != null) {
	    service.onCreated();
	}
    }

    @Override
    public void sessionWrite(T message) {
	if (message != null) {
	    if (CollectionUtils.isNotEmpty(filters)) {
		for (IFilter filter : filters) {
		    filter.onWrite();
		}
	    }
	}
    }

    @Override
    public T sessionReceived() {
	if (CollectionUtils.isNotEmpty(filters)) {
	    for (IFilter filter : filters) {
		filter.onReceived();
	    }
	}
	T result = null;
	if (service != null) {
	    result = service.onService(message);
	}
	return result;
    }

    @Override
    public void sessionReleased() {
    }

    @Override
    public void setFilters(List<IFilter> filters) {
	this.filters = filters;
    }

    @Override
    public void setService(IService service) {
	this.service = service;
    }

    @Override
    public void sessionReadIdle() {
	if (CollectionUtils.isNotEmpty(filters)) {
	    for (IFilter filter : filters) {
		filter.onReadIdle();
	    }
	}
	if (service != null) {
	    service.onReadIdle();
	}
    }

    @Override
    public void sessionWriteIdle() {
	if (CollectionUtils.isNotEmpty(filters)) {
	    for (IFilter filter : filters) {
		filter.onWriteIdle();
	    }
	}
	if (service != null) {
	    service.onWriteIdle();
	}
    }

    private void pendingRead() {
	this.channel.read(null, this, readHandler);
    }

}
