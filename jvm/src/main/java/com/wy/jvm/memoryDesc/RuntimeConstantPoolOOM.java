package com.wy.jvm.memoryDesc;

import java.io.File;
import java.io.PrintStream;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M （jar8废弃该参数，废除永久代）
 * -XX:MetaspaceSize=5M -XX:MaxMetaspaceSize=5M (采用元空间)
 * @Author wy
 * @Date 2018/11/4
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {

        /**
         * 使用如下方法并没有出现元空间的oom异常，反而出现了堆空间的内存溢出
         */
/*
        List<String> list = new ArrayList<>();
        String ss = "jiayouba";
        while (true){
            String aa = ss + ss;
            ss = aa;
            list.add(ss.intern());
        }
*/
/*
        try {
             //准备url
             URL url = new File("D:/58workplace/11study/src/main/java/jdk8").toURI().toURL();
             URL[] urls = {url};
             //获取有关类型加载的JMX接口
             ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
             //用于缓存类加载器
             List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
             while (true) {
                 //加载类型并缓存类加载器实例
                 ClassLoader classLoader = new URLClassLoader(urls);
                 classLoaders.add(classLoader);
                 classLoader.loadClass("com.wy.jvm.memoryDesc.HeapOOM");
                 //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
                 System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
                 System.out.println("active: " + loadingBean.getLoadedClassCount());
                 System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
*/

        //结果：true
        //jdk1.7之后，调用intern方法后，如果常量池中不存在该值时，只是会记录该实例第一次出现的引用。
        String ss = new StringBuilder("wei").append("yin").toString();
        System.out.println(ss.intern() == ss);
    }
}