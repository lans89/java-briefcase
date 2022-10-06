package com.localtest.cxfsample.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.message.Message;

@Slf4j
@InInterceptors
public class AppInboundInterceptor extends LoggingInInterceptor {
    @Override
    public void handleMessage(Message message) throws Fault {
        processPayLoad(message);
        super.handleMessage(message);
    }

    private void processPayLoad(Message message) {
        log.info("*** PROCESSING PAYLOAD AT IN-INTERCEPTOR **");
    }
}
