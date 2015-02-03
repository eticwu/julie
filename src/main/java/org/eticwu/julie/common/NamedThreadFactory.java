package org.eticwu.julie.common;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

    private String name;

    public NamedThreadFactory(String name) {
	this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
	return new Thread(r, name);
    }

}
