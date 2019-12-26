package leetcode;

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
    public static void main(String[] args) {


/*
["AllOne","inc","inc","inc","dec","inc","inc","getMaxKey","dec","dec","dec","getMaxKey"]
[[],["hello"],["world"],["hello"],["world"],["hello"],["leet"],[],["hello"],["hello"],["hello"],[]]
*/


        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("world");
        allOne.inc("hello");

        allOne.dec("world");
        allOne.inc("hello");
        allOne.inc("leet");
        String ib1 = allOne.getMaxKey(); //
        String ia2ss = allOne.getMinKey(); //

        allOne.dec("hello");
        allOne.dec("hello");
        allOne.dec("hello");
        String ib1d = allOne.getMaxKey(); //
        String ia2dss = allOne.getMinKey(); //


//        allOne.inc("a"); //1
//        allOne.inc("a"); //2
//        String xa2 = allOne.getMaxKey(); //
//        String ia2 = allOne.getMinKey(); //
//        allOne.inc("b"); //1
//        String ib1 = allOne.getMaxKey(); //
//        String ia2ss = allOne.getMinKey(); //
//        allOne.inc("b"); //2
//        allOne.inc("b"); // //3
//        allOne.inc("b"); // //4
//        String xa2asd = allOne.getMaxKey(); //
//        String ia2asdasd = allOne.getMinKey();
//        allOne.inc("a"); //3
//        allOne.inc("a"); //4
//        allOne.inc("a"); //5
//        allOne.inc("a"); //6
//        String xb2 = allOne.getMaxKey();
//        String ia1 = allOne.getMinKey();
//        allOne.inc("c"); //1
//        allOne.inc("c"); //2
//        String xb21 = allOne.getMaxKey();
//        String ia11 = allOne.getMinKey();
//        allOne.inc("c"); //3
//        allOne.inc("c"); //4
//        allOne.inc("c"); //5
//        allOne.inc("c"); //6
//        allOne.inc("c"); //7
//        allOne.inc("c"); //8
//        String xb21s = allOne.getMaxKey();
//        String ia11a = allOne.getMinKey();
//
//
//        allOne.dec("d"); //
//        allOne.dec("d"); //
//        allOne.dec("d"); //
//        allOne.inc("d"); //1
//        String xb2dd1s = allOne.getMaxKey();
//        String ia11dda = allOne.getMinKey();
//        allOne.dec("d"); //
//        allOne.dec("d"); //
//        String xb2dssd1s = allOne.getMaxKey();
//        String ia11dssda = allOne.getMinKey();
//        allOne.dec("c"); //7
//        allOne.dec("c"); //6
//        String xb2dssd1scc = allOne.getMaxKey();
//        String ia11dssdcca = allOne.getMinKey();
//        allOne.dec("c"); //5
//        String xb2dssd1ddscc = allOne.getMaxKey();
//        String ia11dssdddcca = allOne.getMinKey();
//        allOne.dec("c"); //4
//        allOne.dec("c"); //3
//        String xb2dssd1ddsccd = allOne.getMaxKey();
//        String ia11dssdddccad = allOne.getMinKey();
//        allOne.dec("c"); //2
//        allOne.dec("c"); //1
//        String xb2dssd1ddsccdd = allOne.getMaxKey();
//        String ia11dssdddccdad = allOne.getMinKey();
//        allOne.dec("a"); //5
//        allOne.dec("a"); //4
//        allOne.dec("a"); //3
//        String xb2dssd1dsdsccdd = allOne.getMaxKey();
//        String ia11dssddadccdad = allOne.getMinKey();
//        allOne.dec("a"); //5
//        allOne.dec("a"); //4
//        allOne.dec("a"); //3
    }

    public class DataModel implements Comparable<DataModel> {
        int value;
        String key;

        DataModel smaller;
        DataModel bigger;

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

        public boolean noBiggerThan(DataModel dataModel) {
            return this.value <= dataModel.value;
        }

        public boolean noSmallerThan(DataModel dataModel) {
            return this.value >= dataModel.value;
        }

        @Override
        public int compareTo(DataModel o) {
            if (this.key.equals(o.key))
                return 0;
            if (this.value <= o.value)
                return -1;
            else
                return 1;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof DataModel))
                return false;
            return ((DataModel) object).key.equals(this.key);
        }

        @Override
        public int hashCode() {
            return this.key.hashCode();
        }
    }

    private DataModel smallest;
    private DataModel biggest;

    private HashMap<String, DataModel> data = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        DataModel dataModel = data.get(key);
        if (dataModel == null) { // new key
            DataModel newElement = new DataModel(key, 1); // create new instance

            // start sorting
            if (smallest == null) { // empty list
                data.put(key, newElement);
                smallest = newElement;
                biggest = newElement;
            } else { // has element, override smallest; old smallest compare to biggest
                smallest.smaller = newElement;
                newElement.bigger = smallest;

                // in case only one element.
                if (biggest.equals(smallest)) {
                    biggest.smaller = newElement;
                    smallest.bigger = biggest;
                    data.put(biggest.key, biggest);
                }
                data.put(smallest.key, smallest);
                smallest = newElement;
                data.put(key, newElement);
            }
        } else { // old key
            dataModel.value = dataModel.value + 1;

            boolean isBiggest = dataModel.equals(biggest);
            boolean isSmallest = dataModel.equals(smallest);

            if (isBiggest && isSmallest) {
                data.put(key, dataModel);
                return;
            } else if (isBiggest) {
                data.put(key, dataModel);
                return;
            }

            if (dataModel.compareTo(dataModel.bigger) > 0) {
                if (isSmallest) {
                    dataModel.bigger.smaller = null;
                    smallest = dataModel.bigger;
                } else {
                    dataModel.bigger.smaller = dataModel.smaller;
                    dataModel.smaller.bigger = dataModel.bigger;
                }

                // reinsert this element
                DataModel i = dataModel.bigger;
                while (true) {
                    if (i.noSmallerThan(dataModel)) { // found the new index

                        if (i.equals(smallest)) {
                            smallest.smaller = dataModel;
                            dataModel.smaller = null;
                            dataModel.bigger = smallest;
                            smallest = dataModel;
                            data.put(key, dataModel);
                            return;
                        } else {
                            dataModel.smaller = i.smaller;
                            dataModel.bigger = i;
                            i.smaller.bigger = dataModel;
                            i.smaller = dataModel;
                            data.put(key, dataModel);
                            return;
                        }
                    }

                    if (i.equals(biggest)) {
                        biggest.bigger = dataModel;
                        dataModel.smaller = biggest;
                        dataModel.bigger = null;
                        biggest = dataModel;
                        data.put(key, dataModel);
                        return;
                    }
                    i = i.bigger;
                }
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        DataModel toDec = data.get(key);
        if (toDec == null)
            return;


        if (toDec.value == 1) {
            if (toDec.equals(smallest) && toDec.equals(biggest)) {
                data.clear();
                smallest = null;
                biggest = null;
                return;
            }
            if (toDec.equals(smallest)) {
                toDec.bigger.smaller = null;
                smallest = toDec.bigger;
                data.remove(key);
                return;
            }

            if (toDec.equals(biggest)) {
                toDec.smaller.bigger = null;
                biggest = toDec.smaller;
                data.remove(key);
                return;
            }

            toDec.bigger.smaller = toDec.smaller;
            toDec.smaller.bigger = toDec.bigger;
            data.remove(key);
            return;
        }

        toDec.value = toDec.value - 1;
        if (toDec.equals(smallest)) {
            data.put(key, toDec);
            return;
        }

        if (toDec.noSmallerThan(toDec.smaller)) {
            data.put(key, toDec);
            return;
        }

        if (toDec.equals(biggest)) {
            toDec.smaller.bigger = null;
            biggest = toDec.smaller;
        } else {
            toDec.smaller.bigger = toDec.bigger;
            toDec.bigger.smaller = toDec.smaller;
        }

        DataModel i = toDec.smaller;
        while (true) {
            if (i.noBiggerThan(toDec)) {
                i.bigger.smaller = toDec;
                i.bigger = toDec;

                toDec.bigger = i.bigger.smaller;
                toDec.smaller = i;
                data.put(key, toDec);

                return;
            }

            if (i.equals(smallest)) {
                smallest.smaller = toDec;
                toDec.bigger = smallest;
                toDec.smaller = null;
                smallest = toDec;
                data.put(key, toDec);
                return;
            }

            i = i.smaller;
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (biggest != null)
            return biggest.key;
        return "";
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (smallest != null)
            return smallest.key;
        return "";
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
