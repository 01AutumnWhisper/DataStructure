/**\
 * @author 90774
 */
//// 2、无头双向链表实现
//public class LinkedList {
//    static class Node{
//        int val;
//        Node prev;
//        Node next;
//        public Node(int data)
//        {
//            val = data;
//        }
//    }
//    private Node head;
//    private int usedSize;
//    public LinkedList(){
//        head = new Node(0);
//        usedSize = 0;
//    }
//    /**
//     *
//     * @param data
//     */
//    public void addFirst(int data)
//    {
//        Node node = new Node(data);
//        node.next = head.next;
//        node.prev = head;
//        head.next.prev = node;
//        head.next = node;
//    }
//
//    /**
//     *
//     * @param data
//     */
//    public void addLast(int data)
//    {
//
//    }
//    /**
//     *
//     */
//    public boolean addIndex(int index,int data)
//    {
//
//    }
//    //查找是否包含关键字key是否在单链表当中
//    public boolean contains(int key);
//    //删除第一次出现关键字为key的节点
//    public void remove(int key);
//    //删除所有值为key的节点
//    public void removeAllKey(int key);
//    //得到单链表的长度
//    public int size();
//    public void display();
//    public void clear();
//}