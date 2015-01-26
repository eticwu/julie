package org.eticwu.julie.server;

import java.util.LinkedList;
import java.util.List;

import org.eticwu.julie.filter.IFilter;

public class ServerConfig {

    private int           port          = 8090;

    private int           idleTime      = 60;

    private int           backlog       = 100;

    private int           tcpRecBufSize = 1024;                     //KB

    private int           tcpSndBufSize = 1024;                     //KB

    private List<IFilter> filters       = new LinkedList<IFilter>();

    private int           timeout       = 5;

    private int           bufferSize    = 1024;

    public void setPort(int port) {
        this.port = port;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
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

    public int getIdleTime() {
        return idleTime;
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

}
