package LeetCode;

public class leetcode {


//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
//    }

    //------------------------------------------------------
    // 节省内存。但是慢。
    // 可以存储差值再通过哈希表快速查数等。节省时间耗费内存。
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        if (length == 2)
            return new int[]{0, 1};
        for (int f = 0; f < length - 1; f++) {
            for (int s = f; s < length; s++)
                if (nums[f] + nums[s] == target)
                    return new int[]{f, s};
        }
        throw new IllegalArgumentException();
    }

    //----------------------------------------------------------------

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);


        ListNode d = new ListNode(5);
        ListNode e = new ListNode(6);
        ListNode f = new ListNode(4);

        a.next = b;
        b.next = c;

        d.next = e;
        e.next = f;


        addTwoNumbers(a, d);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode toReturn = new ListNode(0);
        addWithCarryBit(toReturn, l1, l2, false);
        return toReturn;
    }

    public static void addWithCarryBit(ListNode future, ListNode l1, ListNode l2, boolean carry) {
        if (l1 == null && l2 == null && carry){
            future.val = 1;
            return;
        }

        if (l1 == null) {
            if (!carry) {
                future.val = l2.val;
                future.next = l2.next;
            } else {
                int sum = 1 + l2.val;
                if (sum >= 10) {
                    future.val = sum - 10;
                    future.next = new ListNode(0);
                    addWithCarryBit(future.next, null, l2.next, true);
                } else {
                    future.val = sum;
                    future.next = l2.next;
                }
            }
            return;
        }

        if (l2 == null) {
            if (!carry) {
                future.val = l1.val;
                future.next = l1.next;
            } else {
                int sum = 1 + l1.val;
                if (sum >= 10) {
                    future.val = sum - 10;
                    future.next = new ListNode(0);
                    addWithCarryBit(future.next, l1.next, null, true);
                } else {
                    future.val = sum;
                    future.next = l1.next;
                }
            }
            return;
        }

        int sum = l1.val + l2.val + (carry ? 1 : 0);
        int newVar;
        boolean ca = false;
        if (sum >= 10) {
            newVar = sum - 10;
            ca = true;
        } else {
            newVar = sum;
        }
        future.val = newVar;
        if (l1.next == null && l2.next == null) {
            if (!ca) {

            } else {
                future.next = new ListNode(1);
            }
            return;
        }

        future.next = new ListNode(0);
        addWithCarryBit(future.next, l1.next, l2.next, ca);
    }
}
