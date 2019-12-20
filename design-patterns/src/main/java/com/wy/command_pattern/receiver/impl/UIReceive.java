package com.wy.command_pattern.receiver.impl;

import com.wy.command_pattern.receiver.AbstractReceiver;

/**
 * ui工程师
 * @author weiyin
 * @date 2019-03-19 21:55
 */
public class UIReceive extends AbstractReceiver {
    @Override
    public void add() {
        System.out.println("添加一个页面");
    }

    @Override
    public void delete() {
        System.out.println("删除一个页面");
    }

    @Override
    public void update() {
        System.out.println("修改一个页面");
    }

    @Override
    public void plan() {
        System.out.println("给出了页面操作的排期");
    }
}
