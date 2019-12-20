package com.wy.chain_pattern.handler;

import com.wy.chain_pattern.request.AbstractRequest;
import lombok.Data;

/**
 * 抽象执行者
 * @author weiyin
 * @date 2019-03-20 11:00
 */
@Data
public abstract class AbstractHandler {

    //要处理的消息级别
    private int handleLevel;

    //下一个处理者
    private AbstractHandler nextHandler;

    public AbstractHandler(int handleLevel) {
        this.handleLevel = handleLevel;
    }

    /**
     * 模板方法，定制处理逻辑
     * @param request
     */
    public final void handlerRequest(AbstractRequest request) {
        if (this.handleLevel == request.getLevel()){ //消息属于自己应处理的级别，进行处理
            this.echo(request);
        } else { //消息不属于自己处理的级别，交给下一个handler处理
            if (this.getNextHandler() != null){
                this.getNextHandler().handlerRequest(request);
            } else { //已经没有handler了，该消息不能被处理
                System.out.println("该消息不能被识别处理");
            }
        }
    }

    /**
     * 子类处理所属消息的逻辑
     * @param request
     */
    protected abstract void echo(AbstractRequest request);
}
