package com.wy.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Dwen
 * @date 2020-09-22 09:40
 */
public class CuratorTest {

    private CuratorFramework client;

    @Before
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)  // 会话超时时间
                .connectionTimeoutMs(5000) // 连接超时时间
                .retryPolicy(retryPolicy)
                .namespace("base") // 包含隔离名称
                .build();
        client.start();
    }

    /**
     * 创建节点
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        client.create()
                .creatingParentContainersIfNeeded() // 递归创建所需父节点
                .withMode(CreateMode.PERSISTENT) // 创建类型为持久节点
                .forPath("/nodeA", "init".getBytes()); // 目录及内容
    }

    /**
     * 删除节点
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        client.delete()
                .guaranteed()  // 强制保证删除
                .deletingChildrenIfNeeded() // 递归删除子节点
                .withVersion(0) // 指定删除的版本号
                .forPath("/nodeA");
    }

    /**
     * 查询节点内容
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        byte[] bytes = client.getData().forPath("/nodeA");
        System.out.println(new String(bytes));
    }


    /**
     * 查询节点状态
     * @throws Exception
     */
    @Test
    public void test04() throws Exception {
        Stat stat = new Stat();
        client.getData()
                .storingStatIn(stat)
                .forPath("/nodeA");
        System.out.println(stat);
    }

    /**
     * 修改节点
     * @throws Exception
     */
    @Test
    public void test05() throws Exception {
        client.setData()
                .withVersion(0) // 指定版本修改
                .forPath("/nodeA", "data".getBytes());
    }

    /**
     * 异步回调
     * @throws Exception
     */
    @Test
    public void test06() throws Exception {
        Executor executor = Executors.newFixedThreadPool(2);
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground((curatorFramework, curatorEvent) -> {
                    System.out.println(String.format("eventType:%s,resultCode:%s",curatorEvent.getType(),curatorEvent.getResultCode()));
                },executor)
                .forPath("/nodeA");
    }
}
