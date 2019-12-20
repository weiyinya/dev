package com.wy.observer_pattern.observable;

/**
 * @author weiyin
 * @date 2019-12-16 12:38
 */
public interface Action {

    void run();

    void end();

    void edit(String msg);
}
