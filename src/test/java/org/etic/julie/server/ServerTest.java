package org.etic.julie.server;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import junit.framework.TestCase;

import org.eticwu.julie.handler.Filter;
import org.eticwu.julie.handler.codec.LineBasedFrameDecoder;
import org.eticwu.julie.handler.codec.StringDecoder;
import org.eticwu.julie.server.Server;
import org.eticwu.julie.server.ServerBuilder;
import org.eticwu.julie.session.ISession;
import org.junit.Test;

public class ServerTest extends TestCase {

    @Test
    public void testStart() throws IOException, InterruptedException, ExecutionException {
	Server server = new ServerBuilder().addFilter(new LineBasedFrameDecoder(128, false))
		.addFilter(new StringDecoder()).addFilter(new PrintHandler()).buildServer();
	server.start();
    }

    class PrintHandler extends Filter {

	@Override
	public void sessionCreated(ISession session) {
	}

	@Override
	public void sessionClosed(ISession session) {
	}

	@Override
	public void exceptionCaught(ISession session) {
	}

	@Override
	public void messageReceived(ISession session, Object message) {
	    System.out.println("echo: " + message.toString());
	}

	@Override
	public void messageSent(ISession session) {
	}

    }

}
