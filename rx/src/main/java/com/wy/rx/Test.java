package com.wy.rx;

import com.alibaba.fastjson.JSON;
import io.reactivex.rxjava3.core.Observable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author weiyin
 * @date 2019-12-03 17:08
 */
public class Test {

    public static void main(String[] args) throws Exception {
        timeWindowTest();
    }

    public static void timeWindowTest() throws Exception {
        Random random = new Random();
        Observable<Integer> source = Observable.interval(50, TimeUnit.MILLISECONDS).map(i -> random.nextInt(3));
        source.window(1, TimeUnit.SECONDS).subscribe(window -> {
            int[] metrics = new int[3];
            window.subscribe(i -> metrics[i]++,
                    e -> System.out.println(e),
                    () -> System.out.println("窗口Metrics:" + JSON.toJSONString(metrics)));
        });
        TimeUnit.SECONDS.sleep(3);
    }
}
