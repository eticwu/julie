package org.eticwu.julie.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.eticwu.julie.handler.AcceptCompletionHandler;
import org.eticwu.julie.session.SessionManager;

public class Server implements Runnable {

    private static final Logger logger = Logger.getLogger(Server.class);

    private AsynchronousServerSocketChannel serverChannel;

    private AcceptCompletionHandler acceptCompletionHandler;

    public Server(ServerConfig serverConfig) throws IOException {
	AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(
		Executors.newCachedThreadPool(), 10);
	this.serverChannel = AsynchronousServerSocketChannel.open(group);
	configServer(serverConfig);
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
	if (serverConfig.getTcpRecBufSize() > 0) {
	    serverChannel.setOption(StandardSocketOptions.SO_RCVBUF,
		    serverConfig.getTcpRecBufSize());
	}
	if (serverConfig.getTcpSndBufSize() > 0) {
	    // serverChannel.setOption(StandardSocketOptions.SO_SNDBUF,
	    // serverConfig.getTcpSndBufSize());
	}
	SessionManager sessionManager = new SessionManager(serverConfig);
	this.acceptCompletionHandler = new AcceptCompletionHandler(sessionManager);
    }

    @Override
    public void run() {
	while (true) {
	    Future<AsynchronousSocketChannel> future = serverChannel.accept();
	    try {
		future.get();
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }
}
