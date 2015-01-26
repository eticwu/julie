package org.eticwu.julie.handler;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.eticwu.julie.session.SessionManager;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {

    private SessionManager sessionManager;

    public AcceptCompletionHandler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void completed(AsynchronousSocketChannel result, Object attachment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        // TODO Auto-generated method stub

    }

}
