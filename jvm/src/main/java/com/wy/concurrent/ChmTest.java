package com.wy.concurrent;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * ConcurrentHashMap是绝对线程安全的吗?
 * @author Dwen
 * @date 2020-01-11 16:15
 */
public class ChmTest {
    public static void main(String[] args) throws IOException {
        Selector.open();
    }
}
