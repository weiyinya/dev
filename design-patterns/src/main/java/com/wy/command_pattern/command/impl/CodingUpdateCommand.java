package com.wy.command_pattern.command.impl;

import com.wy.command_pattern.command.AbstractCommand;
import com.wy.command_pattern.receiver.impl.CodingReceive;

/**
 * 修改需求命令
 * @author weiyin
 * @date 2019-03-19 22:04
 */
public class CodingUpdateCommand extends AbstractCommand {

    public CodingUpdateCommand() {
        super(new CodingReceive());
    }

    @Override
    public void execute() {
        super.receiver.plan();
        super.receiver.update();
    }
}
