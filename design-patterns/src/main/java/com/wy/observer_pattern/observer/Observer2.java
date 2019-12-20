package com.wy.observer_pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author weiyin
 * @date 2019-12-16 12:42
 */
public class Observer2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.getClass().getSimpleName() + "，监听：" + arg.toString());
    }
}
