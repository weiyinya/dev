package com.wy.command_pattern.command.impl;

import com.wy.command_pattern.command.AbstractCommand;
import com.wy.command_pattern.receiver.impl.UIReceive;

/**
 * 添加页面的命令
 * @author weiyin
 * @date 2019-03-19 21:58
 */
public class PageAddCommand extends AbstractCommand {

    public PageAddCommand() {
        super(new UIReceive());
    }

    @Override
    public void execute() {
        super.receiver.plan();
        super.receiver.add();
    }
}
