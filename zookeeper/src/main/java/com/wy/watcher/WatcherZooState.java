package com.wy.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author Dwen
 * @date 2020-08-27 20:21
 */
public class WatcherZooState implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        System.out.println("zoo state changed：" + event);
    }

}
