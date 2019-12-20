package com.wy.cacheOut;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * least recently used
 * 缓存淘汰算法之：最近最少使用
 * @author weiyin
 * @date 2019-11-07 15:04
 */
public class LRU {

    @Data
    public static class CacheNode {

        private String key;

        private Object value;

        private CacheNode nextNode;

    }

    @Data
    public static class CacheManager {

        private CacheNode head;

        private CacheNode tail;

        //缓存索引
        private Map<String, CacheNode> index = new HashMap<>();

        private int size;

        private int used;
    }

    public static void main(String[] args) {

    }
}
