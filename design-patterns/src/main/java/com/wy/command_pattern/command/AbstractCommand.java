package com.wy.command_pattern.command;

import com.wy.command_pattern.receiver.AbstractReceiver;

/**
 * 抽象命令
 * @author weiyin
 * @date 2019-03-19 13:46
 */
public abstract class AbstractCommand {

    /**
     * 封装接收者
     */
    protected AbstractReceiver receiver;

    public AbstractCommand(AbstractReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 执行命令
     */
    public abstract void execute();

}
