package org.eticwu.julie.server;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.eticwu.julie.event.IService;
import org.eticwu.julie.filter.IFilter;

public class ServerConfig {

    private int port = 8090;

    private int writeIdleTime = 60;

    private int readIdleTime = 60;

    private int backlog = 100;

    private int tcpRecBufSize = 1024; // KB

    private int tcpSndBufSize = 1024; // KB

    private List<IFilter> filters = new LinkedList<IFilter>();

    private int timeout = 5;

    private int bufferSize = 1024;

    private ExecutorService bizExecutors;

    private IService service;

    public void setPort(int port) {
	this.port = port;
    }

    public void setBacklog(int backlog) {
	this.backlog = backlog;
    }

    public void setTcpRecBufSize(int tcpRecBufSize) {
	this.tcpRecBufSize = tcpRecBufSize;
    }

    public void setTcpSndBufSize(int tcpSndBufSize) {
	this.tcpSndBufSize = tcpSndBufSize;
    }

    public void addFilter(IFilter filter) {
	this.filters.add(filter);
    }

    public void setTimeout(int timeout) {
	this.timeout = timeout;
    }

    public void setBufferSize(int bufferSize) {
	this.bufferSize = bufferSize;
    }

    public int getPort() {
	return port;
    }

    public int getBacklog() {
	return backlog;
    }

    public int getTcpRecBufSize() {
	return tcpRecBufSize;
    }

    public int getTcpSndBufSize() {
	return tcpSndBufSize;
    }

    public List<IFilter> getFilters() {
	return filters;
    }

    public int getTimeout() {
	return timeout;
    }

    public int getBufferSize() {
	return bufferSize;
    }

    public int getWriteIdleTime() {
	return writeIdleTime;
    }

    public void setWriteIdleTime(int writeIdleTime) {
	this.writeIdleTime = writeIdleTime;
    }

    public int getReadIdleTime() {
	return readIdleTime;
    }

    public void setReadIdleTime(int readIdleTime) {
	this.readIdleTime = readIdleTime;
    }

    public ExecutorService getBizExecutors() {
	return bizExecutors;
    }

    public void setBizExecutors(ExecutorService bizExecutors) {
	this.bizExecutors = bizExecutors;
    }

    public IService getService() {
	return service;
    }

    public void setService(IService service) {
	this.service = service;
    }

}
