package com.wy.chain_pattern;

import com.wy.chain_pattern.handler.HandlerManager;
import com.wy.chain_pattern.handler.impl.Handler1;
import com.wy.chain_pattern.handler.impl.Handler2;
import com.wy.chain_pattern.handler.impl.Handler3;
import com.wy.chain_pattern.request.AbstractRequest;

/**
 * 情景模拟
 * @author weiyin
 * @date 2019-03-20 13:33
 */
public class Client {

    public static void main(String[] args) {
        //请求
        AbstractRequest request = new AbstractRequest(2, "111");

        //处理请求
        HandlerManager handlerManager = HandlerManager.defaultManger();
        handlerManager.handler(request);
    }
}
