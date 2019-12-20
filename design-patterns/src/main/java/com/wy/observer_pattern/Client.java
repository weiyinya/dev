package com.wy.observer_pattern;

import com.wy.observer_pattern.observable.ActionImpl;
import com.wy.observer_pattern.observer.Observer1;
import com.wy.observer_pattern.observer.Observer2;

/**
 * @author weiyin
 * @date 2019-12-16 12:44
 */
public class Client {

    public static void main(String[] args) {
        ActionImpl action = new ActionImpl();
        action.addObserver(new Observer1());
        action.addObserver(new Observer2());


        action.run();
        action.edit("aaa");
        action.end();
    }

}
