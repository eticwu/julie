package org.eticwu.julie.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.eticwu.julie.session.ISession;
import org.eticwu.julie.session.SessionManager;

public class Server {

    private static final Logger logger = Logger.getLogger(Server.class);

    private AsynchronousServerSocketChannel serverChannel;

    private ServerConfig serverConfig;

    private SessionManager sessionManager;

    public Server(ServerConfig serverConfig) throws IOException {
	AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(
		Executors.newCachedThreadPool(), 10);
	this.serverChannel = AsynchronousServerSocketChannel.open(group);
	this.serverConfig = serverConfig;
	configServer(serverConfig);
	this.sessionManager = new SessionManager(serverConfig);
    }

    private void configServer(ServerConfig serverConfig) throws IOException {
	if (serverConfig.getPort() > 0 && serverConfig.getBacklog() > 0) {
	    serverChannel.bind(new InetSocketAddress(serverConfig.getPort()),
		    serverConfig.getBacklog());
	} else if (serverConfig.getPort() > 0) {
	    serverChannel.bind(new InetSocketAddress(serverConfig.getPort()));
	}
	if (logger.isInfoEnabled()) {
	    logger.info("server bind port: " + serverConfig.getPort());
	}
    }

    public void start() throws InterruptedException, ExecutionException {
	while (true) {
	    Future<AsynchronousSocketChannel> future = serverChannel.accept();
	    AsynchronousSocketChannel channel = future.get();
	    ISession session = sessionManager.createSession(channel);
	}
    }
}
