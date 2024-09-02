/**
 * @author 90774
 */
public class SingleLinkedList {
    static class Node{
        int val;
        Node next;
        public Node(int data){
            this.val = data;
            this.next = null;
        }
    }
    private Node head;
    private int usedSize;

    /**
     * 头插法
     * @param data
     */
    public void addFirst(int data)
    {
        Node node = new Node(data);
        if(head==null)
        {
            head = node;
        }
        else
        {
            Node cur = head.next;
            head=node;
            node.next = cur;
        }
        usedSize++;
    }

    /**
     * 尾插法
     * @param data
     */
    public void addLast(int data)
    {
        Node node = new Node(data);
        if(head==null)
        {
            head = node;
        }
        else
        {
            Node cur = head;
            while(cur.next!=null)
            {
                cur = cur.next;
            }
            cur.next = node;
        }
        usedSize++;
    }
    /**
     *     任意位置插入,第一个数据节点为0号下标
     */
    public boolean addIndex(int index,int data)
    {
        if(index<0&&index>usedSize)
        {
            return false;
        }

        if(index==0)
        {
            addFirst(data);
        }
        else if(index==usedSize)
        {
            addLast(data);
        }
        else {
            int i = 0;
            Node cur = head,prev=null;
            while (i < index) {
                prev = cur;
                cur = cur.next;
                i++;
            }
            Node node = new Node(data);
            prev.next = node;
            node.next = cur;
        }
        return true;
    }
    /**
     *      查找是否包含关键字key是否在单链表当中
     */
    public boolean contains(int key)
    {
        Node cur = head;
        while(cur!=null)
        {
            if(cur.val==key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除第一次出现关键字为key的节点
     * @param key
     */
    public void remove(int key)
    {
        if(head==null) {
            return;
        }
        else if(head.val==key){
            head = head.next;
        }
        Node cur = head,prev = null;
        while(cur!=null)
        {
            prev = cur;
            cur = cur.next;
        }
    }

    /**
     * 删除所有值为key的节点
     *
     * @param key
     */
    public void removeAllKey(int key)
    {
        while(head!=null&&head.val==key)
        {
            head = head.next;
        }
        if(head==null) {
            return;
        }
        else
        {
            Node cur = head,prev = null;
            while(cur!=null)
            {
                prev = cur;
                cur = cur.next;
                if(cur.val==key)
                {
                    prev.next = cur.next;
                }
            }

        }
    }

    /**
     * 得到单链表的长度
     *
     * @return
     */
    public int size()
    {
        return usedSize;
    }
    public void display()
    {
        Node cur = head;
        while(cur!=null)
        {
            System.out.println(cur.val+"->");
        }
        System.out.println("null");
    }
    public void clear()
    {
        head = null;
    }
}