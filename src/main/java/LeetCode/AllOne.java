package LeetCode;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.util.*;

/**
 * Implement a data structure supporting the following operations:
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1.
 * Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure.
 * Otherwise decrements an existing key by 1. If the key does not exist,
 * this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value.
 * If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value.
 * If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllOne {
//    public static void main(String[] args) {
//        AllOne allOne = new AllOne();
//        allOne.inc("a");
//        allOne.inc("a");
//        String xa2 = allOne.getMaxKey();
//        String ia2 = allOne.getMinKey();
//        allOne.inc("b");
//        String ib1 = allOne.getMaxKey();
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.dec("a");
//        String xb2 = allOne.getMaxKey();
//        String ia1 = allOne.getMinKey();
//    }

    private Comparator<DataModel> DataModelComparator = (o1, o2) -> {
        if (o1.key.equals(o2.key))
            return 0;
        if (o1.getValue() < o2.getValue())
            return 1;
        if (o1.getValue() > o2.getValue())
            return -1;
        return -1;
    };

    private class DataModel {
        int value;
        String key;

        DataModel(String k, int v) {
            value = v;
            key = k;
        }

        public int getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }
    }

    private TreeSet<DataModel> treeSet = new TreeSet<>(DataModelComparator);

    private HashMap<String, Integer> relation = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (relation.containsKey(key)) {
            DataModel old = new DataModel(key, relation.get(key));
            relation.remove(key);
            treeSet.remove(old);
            int newValue = old.value + 1;

            relation.put(key, newValue);
            DataModel newModel = new DataModel(key, newValue);
            treeSet.add(newModel);
        } else {
            relation.put(key, 1);
            treeSet.add(new DataModel(key, 1));
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (relation.containsKey(key)) {
            DataModel old = new DataModel(key, relation.get(key));
            relation.remove(key);
            treeSet.remove(old);
            int newValue = old.value - 1;
            if (newValue > 0) {
                relation.put(key, newValue);
                treeSet.add(new DataModel(key, newValue));
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if(treeSet.isEmpty())
            return "";
        DataModel first = treeSet.first();
        return first.key;
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if(treeSet.isEmpty())
            return "";
        DataModel first = treeSet.last();
        return first.key;
    }
    /**
     * Your AllOne object will be instantiated and called as such:
     * AllOne obj = new AllOne();
     * obj.inc(key);
     * obj.dec(key);
     * String param_3 = obj.getMaxKey();
     * String param_4 = obj.getMinKey();
     */
}
