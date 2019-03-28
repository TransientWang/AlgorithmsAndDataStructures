package test.design;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author wangfy
 * @Description 146.LRU缓存机制(review)
 * @date 2019/1/4
 **/
class LRUCache {
    private HashMap<Integer, Integer> hashMap;
    private LinkedList<Integer> list;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        Integer res = hashMap.getOrDefault(key, -1);
        if (res != -1) {
            list.remove((Object) key);
            list.addFirst(key);
        }
        return res;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            list.remove((Object) key);
            list.addFirst(key);
        } else if (capacity == 0) {
            hashMap.remove(list.pollLast());
            list.addFirst(key);
        } else {
            list.addFirst(key);
            capacity--;
        }
        hashMap.put(key, value);
    }
}

