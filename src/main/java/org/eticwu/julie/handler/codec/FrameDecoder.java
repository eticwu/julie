package org.eticwu.julie.handler.codec;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.eticwu.julie.handler.Filter;
import org.eticwu.julie.session.ISession;

public abstract class FrameDecoder extends Filter {

    private ByteBuffer cumulation = ByteBuffer.allocate(1024);

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
	if (message instanceof ByteBuffer) {
	    List<Object> out = new ArrayList<Object>();
	    ByteBuffer data = (ByteBuffer) message;
	    cumulation.put(data);
	    cumulation.flip();
	    data.clear();
	    callDecode(session, cumulation, out);
	    if (cumulation != null && !cumulation.hasRemaining()) {
		cumulation.clear();
	    }
	    if (this.getNextFilter() != null) {
		for (Object obj : out) {
		    session.getPipeline().fireMessageReceived(this.getNextFilter(), session, obj);
		}
	    }
	    out.clear();
	} else {
	    if (this.getNextFilter() != null) {
		session.getPipeline().fireMessageReceived(this.getNextFilter(), session, message);
	    }
	}
    }

    @Override
    public void messageSent(ISession session) {

    }

    protected void callDecode(ISession session, ByteBuffer in, List<Object> out) {
	while (in.hasRemaining()) {
	    int outSize = out.size();
	    int oldInputLength = in.remaining();
	    decode(session, in, out);
	    if (outSize == out.size()) {
		if (oldInputLength == in.remaining()) {
		    break;
		} else {
		    continue;
		}
	    }
	}
    }

    protected abstract void decode(ISession session, ByteBuffer in, List<Object> out);

}
