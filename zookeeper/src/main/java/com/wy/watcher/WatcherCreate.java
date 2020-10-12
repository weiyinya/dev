package com.wy.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author Dwen
 * @date 2020-09-01 13:36
 */
public class WatcherCreate implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        System.out.println("节点出现啦：" + event);
    }
}
