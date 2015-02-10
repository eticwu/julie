package org.eticwu.julie.session;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import org.eticwu.julie.server.ServerConfig;

public class SessionManager {

    private AtomicInteger count;

    private Map<Integer, ISession> sessionMap;

    private Integer[] readIdleCycle;

    private Integer[] writeIdleCycle;

    private int readIdlePointer;

    private int writeIdlePointer;

    private ScheduledExecutorService idleExecutor;

    private ExecutorService executors;

    private ServerConfig config;

    public SessionManager(ServerConfig config) {
	this.config = config;
	this.count = new AtomicInteger(0);
	this.sessionMap = new ConcurrentHashMap<>();
	this.readIdleCycle = new Integer[config.getReadIdleTime()];
	this.writeIdleCycle = new Integer[config.getWriteIdleTime()];
	this.readIdlePointer = 0;
	this.writeIdlePointer = 0;
	// this.idleExecutor = Executors.newScheduledThreadPool(1, new
	// NamedThreadFactory(
	// "idle-session-detector thread"));
	// this.idleExecutor.schedule(new IdleSessionDetector(), 1,
	// TimeUnit.SECONDS);
    }

    public ISession createSession(AsynchronousSocketChannel channel) {
	Integer id = count.incrementAndGet();
	ISession session = new Session(id, channel, config);
	sessionMap.put(id, session);
	session.sessionCreated();
	return session;
    }

    class IdleSessionDetector implements Runnable {

	@Override
	public void run() {
	    readIdlePointer++;
	    int curReadPointer = readIdlePointer % readIdleCycle.length;
	    int idleReadPointer = (readIdlePointer + 1) % readIdleCycle.length;
	    // TODO find this session
	    if (executors != null) {
		executors.execute(new Runnable() {
		    @Override
		    public void run() {
			// TODO session.onReadIdle();
		    }
		});
	    } else {

	    }

	    // TODO witeIdle
	}
    }
}
