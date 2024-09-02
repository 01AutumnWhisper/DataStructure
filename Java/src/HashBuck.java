public class HashBuck {
    public static final double DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_BUCKETS = 19;
    private Node[] array = new Node[DEFAULT_BUCKETS];
    private int usedSize;
    public static class Node{
        public int key;
        public int val;
        public Node next;

        public Node(int key,int val){
            this.val = val;
            this.key = key;
        }
    }
    public void push(int key,int val)
    {
        //哈希寻址
        int index = key % array.length;
        //遍历链表
        Node cur = array[index];
        while(cur!=null)
        {
            if(cur.key==key)
            {
                cur = cur.next;
            }
            else
            {
                break;
            }
        }
        Node node = new Node(key,val);
        //头插法插入
        node.next = array[index];
        array[index] = node;
// 尾插       cur = array[index];
//        if(cur==null)
//        {
//            array[index] = node;
//        }
//        else {
//            while (cur.next != null)
//                cur = cur.next;
//            cur.next = node;
//        }
        if(doLoadFactor() >= DEFAULT_LOAD_FACTOR)
        {
            //扩容
        }
    }
    private double doLoadFactor()
    {
        return usedSize * 1.0 / array.length;
    }

    private void resize()
    {
        Node[] newArray = new Node[2 * array.length];
        for (Node e:array) {
            int index = e.key % (2*array.length);
            Node cur = array[index];
        }
    }
}
