package org.eticwu.julie.completion;

import java.nio.channels.CompletionHandler;

import org.eticwu.julie.session.ISession;

public class ReadCompletionHandler implements CompletionHandler<Integer, ISession> {

    @Override
    public void completed(Integer result, ISession session) {
	if (result < 0) {
	    session.sessionReleased();
	} else {
	    session.sessionReceived();
	}
    }

    @Override
    public void failed(Throwable exc, ISession session) {

    }

}
