package JavaExtends.MyQueue;

public class MyQueue {
    static class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

    }
    
    private int size;
    private ListNode head;
    private ListNode tail;

    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public void offer(int val) {
        ListNode node = new ListNode(val);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }
    
    public int poll() {
        if (isEmpty())
            return -1;
        ListNode node = head;
        head = head.next;
        node.next = null;
        size--;
        return node.val;
    }
    
    public int peek() {
        if (isEmpty())
            return -1;
        return head.val;
    }

    public int size() {
        return this.size;
    }
}
