package test.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangfy
 * @Description 146.LRU缓存机制
 * @date 2019/1/4
 **/
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int LRUCapacity;


    LRUCache(int LRUCapacity) {
        super(16, 0.75f, true);
        this.LRUCapacity = LRUCapacity;
    }

    @Override
    public Integer get(Object key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        if (size() > this.LRUCapacity) return true;
        else return false;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
    }

}
