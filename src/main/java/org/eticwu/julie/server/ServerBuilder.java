package org.eticwu.julie.server;

import java.io.IOException;

import org.eticwu.julie.handler.Filter;
import org.eticwu.julie.handler.IHandler;

public class ServerBuilder {

    private ServerConfig serverConfig = new ServerConfig();

    public ServerBuilder setPort(int port) {
	serverConfig.setPort(port);
	return this;
    }

    public ServerBuilder setReadIdleTime(int idleTime) {
	serverConfig.setReadIdleTime(idleTime);
	return this;
    }

    public ServerBuilder setWriteIdleTime(int idleTime) {
	serverConfig.setWriteIdleTime(idleTime);
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

    public ServerBuilder addFilter(Filter filter) {
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

    public ServerBuilder setHandler(IHandler handler) {
	serverConfig.setHandler(handler);
	return this;
    }

    public Server buildServer() throws IOException {
	return new Server(serverConfig);
    }

}
