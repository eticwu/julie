package org.etic.julie.server;

import java.io.IOException;

import junit.framework.TestCase;

import org.eticwu.julie.server.Server;
import org.eticwu.julie.server.ServerBuilder;
import org.junit.Test;

public class ServerTest extends TestCase{

    @Test
    public void testStart() throws IOException, InterruptedException{
	Server server = new ServerBuilder().buildServer();
	new Thread(server).start();
	Thread.sleep(1111);
    }
}
