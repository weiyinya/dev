package com.wy.command_pattern.executor;

import com.wy.command_pattern.command.AbstractCommand;

/**
 * 命令传达者（‘项目经理’）
 * @author weiyin
 * @date 2019-03-19 22:07
 */
public class Executor {

    /**
     * 发布命令
     */
    public void invoke(AbstractCommand command){
        command.execute();
    }
}
