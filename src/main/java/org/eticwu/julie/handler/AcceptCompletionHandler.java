package org.eticwu.julie.handler;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.eticwu.julie.session.SessionManager;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    private SessionManager sessionManager;

    public AcceptCompletionHandler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel server) {
        // TODO Auto-generated method stub
	server.accept(server, this);
	System.out.println("accepted");
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel server) {
        // TODO Auto-generated method stub

    }

}
