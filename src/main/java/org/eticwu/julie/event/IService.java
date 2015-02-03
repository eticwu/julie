package org.eticwu.julie.event;

public interface IService<R, T> {

    public void onInit();

    public R onService(T message);

    public void doSend(T message);

    public void onException(Throwable e, T message);

    public void onReadIdle();

    public void onWriteIdle();

    public void onCreated();

    public void onReleased();

}
