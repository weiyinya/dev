package com.wy.chain_pattern.handler;

import com.wy.chain_pattern.handler.impl.Handler1;
import com.wy.chain_pattern.handler.impl.Handler2;
import com.wy.chain_pattern.handler.impl.Handler3;
import com.wy.chain_pattern.request.AbstractRequest;

/**
 *
 * @author weiyin
 * @date 2019-12-13 11:09
 */
public class HandlerManager {
    private AbstractHandler firstHandler;
    private AbstractHandler lastHandler;

    private HandlerManager(AbstractHandler firstHandler) {
        this.firstHandler = firstHandler;
        this.lastHandler = this.firstHandler;
    }

    private HandlerManager nextHandler(AbstractHandler nextHandler){
        lastHandler.setNextHandler(nextHandler);
        lastHandler = nextHandler;
        return this;
    }

    public void handler(AbstractRequest request){
        firstHandler.handlerRequest(request);
    }

    public static HandlerManager defaultManger(){
        HandlerManager handlerManager =
                new HandlerManager(new Handler1())
                .nextHandler(new Handler2())
                .nextHandler(new Handler3());

        return handlerManager;
    }
}
