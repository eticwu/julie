package org.eticwu.julie.session;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.eticwu.julie.event.IService;
import org.eticwu.julie.filter.IFilter;
import org.eticwu.julie.handler.ReadCompletionHandler;
import org.eticwu.julie.handler.WriteCompletionHandler;

public class Session<T> implements ISession<T> {

    private static final Logger       logger = Logger.getLogger(Session.class);

    private AsynchronousSocketChannel channel;

    private Integer                   id;

    private List<IFilter>             filters;

    private IService                  service;

    private ReadCompletionHandler     readHandler;

    private WriteCompletionHandler    writeHandler;

    private ByteBuffer                buffer;

    Session(Integer id, AsynchronousSocketChannel channel) {
        this.id = id;
        this.channel = channel;
        this.readHandler = new ReadCompletionHandler();
        this.writeHandler = new WriteCompletionHandler();
        this.buffer = ByteBuffer.allocate(1024);
    }

    @Override
    public void sessionCreated() {
        if (CollectionUtils.isNotEmpty(filters)) {
            for (IFilter filter : filters) {
                filter.onCreated();
            }
        }
        if (service != null) {
            service.onCreated();
        }
        this.channel.read(buffer, this, readHandler);
    }

    @Override
    public void sessionWrite(T message) {
        if (message != null) {
            if (CollectionUtils.isNotEmpty(filters)) {
                for (IFilter filter : filters) {
                    filter.onWrite();
                }
            }
        }
    }

    @Override
    public T sessionReceived() {
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
    public void setFilters(List<IFilter> filters) {
        this.filters = filters;
    }

    @Override
    public void setService(IService service) {
        this.service = service;
    }

    @Override
    public void sessionReadIdle() {
        if (CollectionUtils.isNotEmpty(filters)) {
            for (IFilter filter : filters) {
                filter.onReadIdle();
            }
        }
        if (service != null) {
            service.onReadIdle();
        }
    }

    @Override
    public void sessionWriteIdle() {
        if (CollectionUtils.isNotEmpty(filters)) {
            for (IFilter filter : filters) {
                filter.onWriteIdle();
            }
        }
        if (service != null) {
            service.onWriteIdle();
        }
    }

    private void pendingRead() {
        this.channel.read(null, this, readHandler);
    }

}
