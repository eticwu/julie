package org.eticwu.julie.session;

import java.util.List;

import org.eticwu.julie.event.IService;
import org.eticwu.julie.filter.IFilter;

public interface ISession<T> {

    public void sessionCreated();

    public void sessionWrite(T message);

    public T sessionReceived();

    public void sessionReleased();

    public void sessionReadIdle();

    public void sessionWriteIdle();

    public void setFilters(List<IFilter> filterss);

    public void setService(IService service);

}
