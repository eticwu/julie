package org.eticwu.julie.session;

import java.nio.ByteBuffer;

public interface ISession<T> {

    public void sessionCreated();

    public void sessionWrite(T message);

    public T sessionReceived();

    public void sessionReleased();

    public void sessionReadIdle();

    public void sessionWriteIdle();

    public ByteBuffer getBuffer();

}
