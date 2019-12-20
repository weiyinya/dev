package com.wy.hadoop_operator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;

/**
 * @author weiyin
 * @date 2019-03-28 09:08
 */
public class HadoopApi {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fs = FileSystem.newInstance(conf);

        Executors.newCachedThreadPool();

    }
}
