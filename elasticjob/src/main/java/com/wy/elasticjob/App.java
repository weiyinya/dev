package com.wy.elasticjob;

import com.wy.elasticjob.job.MyJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 * elasticjob与quartz的结合：
 *  @see org.apache.shardingsphere.elasticjob.lite.internal.schedule.LiteJob
 *
 */
@SpringBootApplication
public class App 
{
    private static final String dockerAddr = "172.17.0.2";
    private static final String remoteAddr = "49.233.16.56";
    private static final String localAddr = "localhost";

    private static final String port = "2181";

    public static void main( String[] args )
    {
        new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration()).schedule();
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration(remoteAddr + ":" + port, "my-job"));
        regCenter.init();
        return regCenter;
    }

    private static JobConfiguration createJobConfiguration() {
        // 创建作业配置
        JobConfiguration jobConfig = JobConfiguration.newBuilder("MyJob", 2).cron("0/5 * * * * ?").overwrite(true).build();
        return jobConfig;
    }
}
