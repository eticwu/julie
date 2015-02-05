package org.eticwu.julie.session;

import java.nio.ByteBuffer;
import java.util.List;

import org.eticwu.julie.event.IService;
import org.eticwu.julie.handler.IHandler;

public interface ISession<T> {

    public void sessionCreated();

    public void sessionWrite(T message);

    public T sessionReceived();

    public void sessionReleased();

    public void sessionReadIdle();

    public void sessionWriteIdle();

    public void setFilters(List<IHandler> filterss);

    public void setService(IService service);
    
    public ByteBuffer getByteBuffer();

}
