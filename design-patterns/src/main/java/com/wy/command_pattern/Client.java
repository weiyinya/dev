package com.wy.command_pattern;

import com.wy.command_pattern.command.impl.CodingUpdateCommand;
import com.wy.command_pattern.command.impl.PageAddCommand;
import com.wy.command_pattern.executor.Executor;

/**
 * 情景模拟
 * 优点：命令发布者与命令执行者解耦。
 * @author weiyin
 * @date 2019-03-19 22:10
 */
public class Client {

    public static void main(String[] args) {

        //项目经理
        Executor executor = new Executor();

        //有一天，客户找到项目经理，要求添加页面
        //于是项目经理发布了一条添加页面的命令
        executor.invoke(new PageAddCommand());

        //过了一段时间，客户要求该需求
        //项目经理发出了需求更新的命令
        executor.invoke(new CodingUpdateCommand());
    }
}
