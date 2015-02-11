package org.eticwu.julie.session;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

import org.apache.log4j.Logger;
import org.eticwu.julie.completion.ReadCompletionHandler;
import org.eticwu.julie.completion.WriteCompletionHandler;
import org.eticwu.julie.handler.IHandler;
import org.eticwu.julie.server.ServerConfig;

public class Session<T> implements ISession<T> {

    private static final Logger logger = Logger.getLogger(Session.class);

    private AsynchronousSocketChannel channel;

    private Integer id;

    private IHandler handler;

    private ReadCompletionHandler readHandler;

    private WriteCompletionHandler writeHandler;

    private Pipeline pipeline;

    private int bufferSize;

    private ByteBuffer buffer;

    Session(Integer id, AsynchronousSocketChannel channel, ServerConfig config) {
	this.id = id;
	this.channel = channel;
	this.readHandler = new ReadCompletionHandler();
	this.writeHandler = new WriteCompletionHandler();
	this.pipeline = new Pipeline();
	this.pipeline.addLast(config.getFilters());
	this.bufferSize = config.getBufferSize();
	this.handler = config.getHandler();
	this.buffer = ByteBuffer.allocate(this.bufferSize);
    }

    @Override
    public void sessionCreated() {
	// this.pipeline.publish(Event.SESSION_CREATED, this);
	this.channel.read(buffer, this, readHandler);
    }

    @Override
    public void sessionWrite(T message) {
	// this.pipeline.publish(Event.MESSAGE_SENT, this, message);
    }

    @Override
    public T sessionReceived() {
	this.buffer.flip();
	this.pipeline.fireMessageReceived(this.pipeline.getHead(), this, this.buffer);
	this.buffer.clear();
	this.channel.read(buffer, this, readHandler);
	return null;
    }

    @Override
    public void sessionReleased() {
    }

    @Override
    public void sessionReadIdle() {

    }

    @Override
    public void sessionWriteIdle() {

    }

    @Override
    public ByteBuffer getBuffer() {
	return this.buffer;
    }

    @Override
    public Pipeline getPipeline() {
	return this.pipeline;
    }

}
