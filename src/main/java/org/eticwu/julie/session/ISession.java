package org.eticwu.julie.session;

public interface ISession<T> {
	
	public void write(T message);
	
	public T read();
	
	public void release();

}
