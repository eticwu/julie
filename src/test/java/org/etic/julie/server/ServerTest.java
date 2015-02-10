package org.etic.julie.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import junit.framework.TestCase;

import org.eticwu.julie.event.Event;
import org.eticwu.julie.handler.DefaultHandlerAdapter;
import org.eticwu.julie.handler.codec.LineBasedFrameDecoder;
import org.eticwu.julie.server.Server;
import org.eticwu.julie.server.ServerBuilder;
import org.eticwu.julie.session.ISession;
import org.junit.Test;

public class ServerTest extends TestCase {

    @Test
    public void testStart() throws IOException, InterruptedException, ExecutionException {
	Server server = new ServerBuilder().addFilters(new LineBasedFrameDecoder(128, false))
		.addFilters(new PrintHandler()).buildServer();
	server.start();
    }

    class PrintHandler extends DefaultHandlerAdapter {

	@Override
	public void sessionCreated(ISession session) {
	    super.sessionCreated(session);
	}

	@Override
	public void sessionClosed(ISession session) {
	    super.sessionClosed(session);
	}

	@Override
	public void exceptionCaught(ISession session) {
	    super.exceptionCaught(session);
	}

	@Override
	public void messageReceived(Event event, ISession session, Object message) {
	    if (message == null) {
		System.out.println("nothing");
		return;
	    }
	    if (message instanceof byte[]) {
		try {
		    System.err.println(new String((byte[]) message, "utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
	    } else {
		System.out.println("error");
	    }
	}

	@Override
	public void messageSent(ISession session) {
	    super.messageSent(session);
	}

    }

}
