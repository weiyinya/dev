package com.wy;

import com.wy.watcher.WatcherZooState;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App2
{
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        WatcherZooState watcher = new WatcherZooState();
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2100,127.0.0.1:2200,127.0.0.1:2300", 60 * 1000, watcher);
        zooKeeper.create("/usr/local", "aaaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        while (true) {

        }
    }
}
