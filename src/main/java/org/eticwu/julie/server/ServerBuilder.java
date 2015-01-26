package org.eticwu.julie.server;

import java.io.IOException;

import org.eticwu.julie.filter.IFilter;

public class ServerBuilder {

    private ServerConfig serverConfig = new ServerConfig();

    public ServerBuilder setPort(int port) {
        serverConfig.setPort(port);
        return this;
    }

    public ServerBuilder setIdleTime(int idleTime) {
        serverConfig.setIdleTime(idleTime);
        return this;
    }

    public ServerBuilder setBacklog(int backlog) {
        serverConfig.setBacklog(backlog);
        return this;
    }

    public ServerBuilder setTcpRecBufSize(int tcpRecBufSize) {
        serverConfig.setTcpRecBufSize(tcpRecBufSize);
        return this;
    }

    public ServerBuilder setTcpSndBufSize(int tcpSndBufSize) {
        serverConfig.setTcpSndBufSize(tcpSndBufSize);
        return this;
    }

    public ServerBuilder addFilters(IFilter filter) {
        serverConfig.addFilter(filter);
        return this;
    }

    public ServerBuilder setTimeout(int timeout) {
        serverConfig.setTimeout(timeout);
        return this;
    }

    public ServerBuilder setBufferSize(int bufferSize) {
        serverConfig.setBufferSize(bufferSize);
        return this;
    }

    public Server buildServer() throws IOException {
        return new Server(serverConfig);
    }

}
