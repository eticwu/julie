package org.eticwu.julie.event;

public interface IService<R, T> {
	
	public void doInit();
	
	public R doService(T message);
	
	public void doException(Throwable e, T message);
	
}
