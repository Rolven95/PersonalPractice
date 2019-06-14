package LeetCode;

/*
 * Remove all elements from a linked list of integers that have value val.
 * Example:
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 * */
public class RemoveLinkedListElements {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 执行用时 : 3 ms , 在所有Java提交中击败了 32.18% 的用户
     * 内存消耗 : 43.6 MB , 在所有Java提交中击败了 66.36% 的用户
     */
    public ListNode removeElements1(ListNode head, int val) {
        if (head == null)
            return null;

        head.next = removeElements1(head.next, val);

        return head.val == val ? head.next : head;
    }

    /**
     * 执行用时 : 3 ms , 在所有Java提交中击败了 32.18% 的用户
     * 内存消耗 : 43.8 MB , 在所有Java提交中击败了 62.52% 的用户
     */
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null)
            return null;

        if (head.val == val) {
            if (head.next == null)
                return null;

            head.val = head.next.val;
            if (head.next.next != null) {
                head.next = head.next.next;
            } else {
                head.next = null;
            }
        }

        if (head.val == val) {
            head = removeElements2(head, val);
        } else {
            head.next = removeElements2(head.next, val);
        }

        return head;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(6);
        ListNode b = new ListNode(6);
        ListNode c = new ListNode(6);
        ListNode d = new ListNode(6);
        ListNode e = new ListNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        RemoveLinkedListElements removeLinkedListElements = new RemoveLinkedListElements();

        removeLinkedListElements.removeElements2(a, 6);
    }
}