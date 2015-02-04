package org.eticwu.julie.completion;

import java.nio.channels.CompletionHandler;

import org.eticwu.julie.session.ISession;

public class WriteCompletionHandler implements CompletionHandler<Integer, ISession> {

	@Override
	public void completed(Integer result, ISession attachment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failed(Throwable exc, ISession attachment) {
		// TODO Auto-generated method stub
		
	}

}
