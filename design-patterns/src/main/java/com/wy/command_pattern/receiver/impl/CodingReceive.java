package com.wy.command_pattern.receiver.impl;

import com.wy.command_pattern.receiver.AbstractReceiver;

/**
 * 后台工程师
 * @author weiyin
 * @date 2019-03-19 21:57
 */
public class CodingReceive extends AbstractReceiver {
    @Override
    public void add() {
        System.out.println("添加一个需求");
    }

    @Override
    public void delete() {
        System.out.println("删除一个需求");
    }

    @Override
    public void update() {
        System.out.println("更新一个需求");
    }

    @Override
    public void plan() {
        System.out.println("给出了需求变动的排期");
    }
}
