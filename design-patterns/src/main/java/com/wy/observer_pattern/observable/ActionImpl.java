package com.wy.observer_pattern.observable;

import java.util.Observable;

/**
 * @author weiyin
 * @date 2019-12-16 12:39
 */
public class ActionImpl extends Observable implements Action {
    @Override
    public void run() {
        System.out.println("开始启动！");
        super.setChanged();
        super.notifyObservers("开始启动！");
    }

    @Override
    public void end() {
        System.out.println("停止！");
        super.setChanged();
        super.notifyObservers("停止！");
    }

    @Override
    public void edit(String msg) {
        System.out.println("修改：" + msg);
        super.setChanged();
        super.notifyObservers("修改：" + msg);
    }
}
