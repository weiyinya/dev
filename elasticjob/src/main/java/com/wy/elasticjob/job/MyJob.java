package com.wy.elasticjob.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @author Dwen
 * @date 2020-09-27 14:32
 */
@Slf4j
public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()) {
            case 0:
                log.info("--------> 0 <--------");
                break;
            case 1:
                log.info("--------> 1 <--------");
                break;
            case 2:
                log.info("--------> 2 <--------");
                break;
            default:
                log.info("default");
        }
    }
}
