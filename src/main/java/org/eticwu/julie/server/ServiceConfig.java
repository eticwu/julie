package org.eticwu.julie.server;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ServiceConfig {

	public final String WITHCACHETHREADPOOL = "withCacheThreadPool";

	public final String WITHFIXEDTHREADPOOL = "withFixedThreadPool";

	public final String WITHTHREADPOOL = "withThreadPool";

	public final String WITHNOTHREADPOOL = "withNoThreadPool";

	private int port = 8090;

	private int idleTime = 60;

	private int timeout = 5;

	private int bufferSize = 1024;

	private String threadGroup = WITHNOTHREADPOOL;

	private int nThreads = 10;

	private int initialSize = 10;

	private int backlog = 100;

	private ExecutorService executorService;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public int getnThreads() {
		return nThreads;
	}

	public void setnThreads(int nThreads) {
		this.nThreads = nThreads;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public Server createServer() throws IOException {
		AsynchronousChannelGroup group = getGroup();  
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(group);
		return null;
	}

	private AsynchronousChannelGroup getGroup() throws IOException {
		switch(this.threadGroup){
			case WITHCACHETHREADPOOL:
				if(!checkExecutorService()){//TODO check initialSize
					
				}
				return AsynchronousChannelGroup.withCachedThreadPool(executorService, initialSize);
			case WITHFIXEDTHREADPOOL:
				//TODO check nThreads
				return AsynchronousChannelGroup.withFixedThreadPool(nThreads, new ThreadFactory(){

					@Override
					public Thread newThread(Runnable r) {
						// TODO Auto-generated method stub
						return null;
					}
					
				});
			case WITHTHREADPOOL:
				return AsynchronousChannelGroup.withThreadPool(executorService);
			default:
				return null;
		}
	}

	private boolean checkExecutorService() {
		if (executorService == null) {
			return false;
		}
		return true;
	}

	public String getThreadGroup() {
		return threadGroup;
	}

	public void setThreadGroup(String threadGroup) {
		this.threadGroup = threadGroup;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

}
