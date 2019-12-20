package com.wy.chain_pattern.handler.impl;

import com.wy.chain_pattern.handler.AbstractHandler;
import com.wy.chain_pattern.request.AbstractRequest;

/**
 * @author weiyin
 * @date 2019-03-20 13:28
 */
public class Handler3 extends AbstractHandler {

    public Handler3() {
        super(3);
    }

    @Override
    protected void echo(AbstractRequest request) {
        System.out.println("handler3处理消息：" + request.getRequestData());
    }
}
