package org.etic.julie.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class ClientTest {

    @Test
    public void clientStart() throws UnknownHostException, IOException, InterruptedException {
	Socket socket = new Socket("127.0.0.1", 8090);
	OutputStream out = socket.getOutputStream();

	// for (int i = 1; i < 1000; i++) {
	out.write("10101010101\n1000010".getBytes());
	out.write("22222222\n".getBytes());
	// System.out.println(i);
	Thread.sleep(1000);
	// }
	socket.close();
    }
}
