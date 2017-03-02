package homework_2;


public class Solution {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(2);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(3);
        ListNode list2 = new ListNode(5);
        list2.next = new ListNode(6);
        list2.next.next = new ListNode(4);

        ListNode list3 = addTwoNumbers(list1, list2);
        System.out.println(list3.val);
        System.out.println(list3.next.val);
        System.out.println(list3.next.next.val);
        //System.out.println(list3.next.next.next.val);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = (l1.val + l2.val) / 10;
        ListNode result = new ListNode((l1.val + l2.val) % 10);
        ListNode tempNode = new ListNode(temp);
        if (temp > 0 || l1.next != null || l2.next != null) {
            result.next = tempNode;
        }

        while (l1.next != null || l2.next != null) {
            l1 = l1.next != null ? l1.next : new ListNode(0);
            l2 = l2.next != null ? l2.next : new ListNode(0);
            tempNode.val = (temp + l1.val + l2.val) % 10;
            temp = (temp + l1.val + l2.val) / 10;
            if (temp > 0 || l1.next != null || l2.next != null) {
                tempNode.next = new ListNode(temp);
                tempNode = tempNode.next;
            }
        }

        return result;
    }
}
