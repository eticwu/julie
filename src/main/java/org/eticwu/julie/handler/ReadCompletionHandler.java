package org.eticwu.julie.handler;

import java.nio.channels.CompletionHandler;

import org.eticwu.julie.session.ISession;

public class ReadCompletionHandler implements CompletionHandler<Integer, ISession> {

    @Override
    public void completed(Integer result, ISession session) {
	// TODO parse
	session.sessionReceived();
    }

    @Override
    public void failed(Throwable exc, ISession session) {

    }

}
