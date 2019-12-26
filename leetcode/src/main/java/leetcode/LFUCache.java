package leetcode; /**
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
public class LFUCache {
    private HashMap<Integer, Integer> dataMap = new HashMap<>();
    private HashMap<Integer, Integer> freq = new HashMap<>();
    private LinkedList<Integer> record = new LinkedList<>();

    private int dataSize = 0;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer toReturn = dataMap.get(key);
        if (toReturn == null) {
            return -1;
        } else {
            freq.put(key, freq.get(key) + 1);
            refreshLFRecord(key);
            return toReturn;
        }
    }

    public void put(int key, int value) {
        if (!dataMap.containsKey(key)) {
            if (isCacheFull()) {
                removeLFData();
                if (isCacheFull())
                    return;
            }
            dataSize++;
        }

        dataMap.put(key, value);
        freq.put(key, 1);
        refreshLFRecord(key);
    }

    private boolean isCacheFull() {
        if (dataSize >= capacity)
            return true;
        else
            return false;
    }

    private void removeLFData() {
        int minFreq = 0;
        HashMap<Integer, Integer> sameFreq = new HashMap<>(); //所有拥有最小频率的节点
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int thisFreq = entry.getValue();
            if (minFreq == 0) {
                minFreq = thisFreq;
                sameFreq.put(entry.getKey(), -1);
            } else if (thisFreq > minFreq) {

            } else if (thisFreq < minFreq) {
                sameFreq.clear();
                minFreq = thisFreq;
                sameFreq.put(entry.getKey(), -1);
            } else if (thisFreq == minFreq) {
                sameFreq.put(entry.getKey(), -1);
            }
        }
        int toRemove = -1;
        if (sameFreq.size() == 1) {
            for (Map.Entry<Integer, Integer> entry : sameFreq.entrySet()) {
                toRemove = entry.getKey();
                freq.remove(toRemove);
                dataMap.remove(toRemove);
                record.remove(findIndexInRecord(toRemove));
            }
        } else {
            int oldest = -1;
            int i = 0;
            for (Integer integer : record) {
                if (sameFreq.containsKey(integer)) {
                    if (i > oldest) {
                        toRemove = integer;
                    }
                }
                i++;
            }

            if (toRemove < 0)
                return;
            freq.remove(toRemove);
            dataMap.remove(toRemove);
            record.remove(findIndexInRecord(toRemove));
        }

        dataSize--;
    }

    private int findIndexInRecord(int key) {
        int target = -1;
        int i = 0;
        for (Integer integer : record) {
            if (integer == key) {
                target = i;
                break;
            }
            i++;
        }
        return target;
    }

    private void refreshLFRecord(int key) {
        int target = findIndexInRecord(key);
        if (target != -1) {
            record.remove(target);
        }

        record.addFirst(key);
    }

    public static void main(String[] args) {
//        LFUCache cache = new LFUCache(2 /* capacity (缓存容量) */);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.get(1);       // 返回 1
//        cache.put(3, 3);    // 去除 key 2
//        cache.get(2);       // 返回 -1 (未找到key 2)
//        cache.get(3);       // 返回 3
//        cache.put(4, 4);    // 去除 key 1
//        cache.get(1);       // 返回 -1 (未找到 key 1)
//        cache.get(3);       // 返回 3
//        cache.get(4);       // 返回 4


////        LFUCache cache = new LFUCache(0 /* capacity (缓存容量) */);
////        cache.put(0, 0);
////        System.out.println(cache.get(0));
//
//        LFUCache cache = new LFUCache(3 /* capacity (缓存容量) */);
//        cache.put(2, 2);
//        cache.put(1, 1);
//        cache.get(2);       // 返回 4
//        cache.get(1);       // 返回 4
//        cache.get(2);       // 返回 4
//        cache.put(3, 3);
//        cache.put(4, 4);
//        cache.get(3);       // 返回 4
//        cache.get(2);       // 返回 4
//        cache.get(1);       // 返回 4
//        cache.get(4);       // 返回 4
//
//        LFUCache cache = new LFUCache(3 /* capacity (缓存容量) */);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.put(3, 3);
//
//        cache.get(2);       // 返回 4
//        cache.get(1);       // 返回 4
//        cache.get(3);       // 返回 4
//
//
//        cache.put(4, 1);

/**
 * ["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
 * [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
 * */
        LFUCache cache2 = new LFUCache(10 /* capacity (缓存容量) */);
        cache2.put(10, 13);
        cache2.put(3, 17);
        cache2.put(6, 11);       // 返回 4
        cache2.put(10, 5);
        cache2.put(9, 10);
        cache2.get(13);
        cache2.put(2, 19);
        cache2.get(2);        // 返回 4
        cache2.get(3);        // 返回 4
        cache2.put(5, 25);
        cache2.get(8);
        cache2.put(9, 22);
        cache2.put(5, 5);
        cache2.put(1, 30);
        cache2.get(11);       // 返回 4
        cache2.put(9, 12);
        cache2.get(7);       // 返回 4
        cache2.get(5);       // 返回 4
        cache2.get(8);       // 返回 4
        cache2.get(9);       // 返回 4
        cache2.put(4, 30);
        cache2.put(9, 3);
        cache2.get(9);       // 返回 4
        cache2.get(10);
        cache2.get(10);
        cache2.put(6, 14);
        cache2.put(3, 1);
        cache2.get(3);
        cache2.put(10, 11);
        cache2.get(8);
        cache2.put(2, 14);
        cache2.get(1);
        cache2.get(5);
        cache2.get(4);
        cache2.put(11, 4);
        cache2.put(12, 24);
        cache2.put(5, 18);
        cache2.get(13);
        cache2.put(7, 23);
        cache2.get(8);
        cache2.get(12);
        cache2.put(3, 27);
        cache2.put(2, 12);
        cache2.get(5);
        cache2.put(2, 9);
        cache2.put(13, 4);
        cache2.put(8, 18);
        cache2.put(1, 7);
        cache2.get(6); //这个错了 应该输出14
        cache2.put(9, 29);
        cache2.put(8, 21);
        cache2.get(5); // 对了
        cache2.put(6, 30);
        cache2.put(1, 12);
        cache2.get(10); // 错了，应该11
        cache2.put(4, 15);
        cache2.put(7, 22);
        cache2.put(11, 26);
        cache2.put(8, 17);
        cache2.put(9, 29);
        cache2.get(5);
        cache2.put(3, 4);
        cache2.put(11, 30);
        cache2.get(12);
        cache2.put(4, 29);
        cache2.get(3);
        cache2.get(9);
        cache2.get(6);
        cache2.put(3, 4);
        cache2.get(1);
        cache2.get(10);
        cache2.put(3, 29);
        cache2.put(10, 28);
        cache2.put(1, 20);
        cache2.put(11, 13);
        cache2.get(3);
        cache2.put(3, 12);
        cache2.put(3, 8);
        cache2.put(10, 9);
        cache2.put(3, 26);
        cache2.get(8);
        cache2.get(7);
        cache2.get(5);
        cache2.put(13, 17);
        cache2.put(2, 27);
        cache2.put(11, 15);
        cache2.get(12);
        cache2.put(9, 19);
        cache2.put(2, 15);
        cache2.put(3, 16);
        cache2.get(1);
        cache2.put(12, 17);
        cache2.put(9, 1);
        cache2.put(6, 19);
        cache2.get(4);
        cache2.get(5);
        cache2.get(5);
        cache2.put(8, 1);
        cache2.put(11, 7);
        cache2.put(5, 2);
        cache2.put(9, 28);
        cache2.get(1);
        cache2.put(2, 2);
        cache2.put(7, 4);
        cache2.put(4, 22);
        cache2.put(7, 24);
        cache2.put(9, 26);
        cache2.put(13, 28);
        cache2.put(11, 26);
    }
}