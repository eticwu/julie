package org.eticwu.julie.filter;

public interface IFilter {

    public void onCreated();

    public void onReceived();

    public void onWrite();

    public void onReadIdle();

    public void onWriteIdle();
}
