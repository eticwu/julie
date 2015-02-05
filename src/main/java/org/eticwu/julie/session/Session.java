package org.eticwu.julie.session;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.eticwu.julie.completion.ReadCompletionHandler;
import org.eticwu.julie.completion.WriteCompletionHandler;
import org.eticwu.julie.event.Event;
import org.eticwu.julie.event.IService;
import org.eticwu.julie.handler.IHandler;

public class Session<T> implements ISession<T> {

    private static final Logger       logger = Logger.getLogger(Session.class);

    private AsynchronousSocketChannel channel;

    private Integer                   id;

    private List<IHandler>             filters;

    private IService                  service;

    private ReadCompletionHandler     readHandler;

    private WriteCompletionHandler    writeHandler;

    private ByteBuffer                buffer;
    
    private Pipeline                  pipeline;

    Session(Integer id, AsynchronousSocketChannel channel) {
        this.id = id;
        this.channel = channel;
        this.readHandler = new ReadCompletionHandler();
        this.writeHandler = new WriteCompletionHandler();
        this.buffer = ByteBuffer.allocate(1024);
        this.pipeline = new Pipeline();
    }

    @Override
    public void sessionCreated() {
        this.pipeline.publish(Event.SESSION_CREATED, this);
        this.channel.read(buffer, this, readHandler);
    }

    @Override
    public void sessionWrite(T message) {
       this.pipeline.publish(Event.MESSAGE_SENT, this);
    }

    @Override
    public T sessionReceived() {
	this.pipeline.publish(Event.MESSAGE_RECEIVED, this);
        Integer readBytes = 0;
        try {
            readBytes = this.channel.read(buffer).get();
        } catch (InterruptedException | ExecutionException e1) {
            logger.error(e1);
        }
        if (readBytes > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            //            buffer.get(bytes);
            //            if (CollectionUtils.isNotEmpty(filters)) {
            //                for (IFilter filter : filters) {
            //                    filter.onReceived();
            //                }
            //            }
            //            if (service != null) {
            //                //      result = service.onService(message);
            //            }
            int length = buffer.remaining();
            int i = 0;
            for (; i < length; i++) {
                byte b = buffer.get(i);
                if (b == '\n') {
                    break;
                } else if (b == '\r' && i < length - 1 && buffer.get(i + 1) == '\n') {
                    break;
                }
                bytes[i] = b;
            }
            try {
                System.out.println(ArrayUtils.toString(bytes));
                System.out.println(new String(bytes, 0, i, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error(e);
            }
        }
        buffer.clear();
        this.channel.read(buffer, this, readHandler);
        return null;
    }

    @Override
    public void sessionReleased() {
    }

    @Override
    public void setFilters(List<IHandler> filters) {
        this.filters = filters;
    }

    @Override
    public void setService(IService service) {
        this.service = service;
    }

    @Override
    public void sessionReadIdle() {
        
    }

    @Override
    public void sessionWriteIdle() {
        
    }

    @Override
    public ByteBuffer getByteBuffer() {
	return buffer;
    }

}
