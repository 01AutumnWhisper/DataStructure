package MyClass.ArrayList;

import MyAbstractClass.MyAbstractSequentialList;
import MyInterface.IMyDeque;
import MyInterface.IMyIterator;

import java.util.NoSuchElementException;

public class MyLinkedList<E> extends MyAbstractSequentialList<E> implements IMyDeque<E> {
    @Override
    public IMyIterator<E> iterator() {
        return null;
    }

    static class ListNode<E>{
        E element;
        ListNode<E> prev;
        ListNode<E> next;
        public ListNode(E element){
            this.element = element;
            prev=null;
            next=null;
        }
    }

    /**
     * 注意:My MyAbstractSequentialList有一个字段size(protected)专门用来记录长度的.
     */
    private ListNode<E> head;
    private ListNode<E> tail;

    /**
     *    遍历链表--根据下标返回节点
     *    封装这个接口是为了重复的遍历写烦了.
     *    根据下标与长度关系,选择从头遍历或者从尾遍历.
     * @param index
     * @return
     */
    public ListNode<E> node(int index)
    {
        if(!isElements(index))
            return null;
        else{
            ListNode<E> cur = null;
            if(index*2<super.size)
            {
                cur = head;
                for (int i = 0; i < index; i++) {
                    cur = cur.next;
                }
            }
            else
            {
                cur = tail;
                for (int i = 0; i < index; i++) {
                    cur = cur.prev;
                }
            }
            return cur;
        }
    }

    /**
     * 3个私有方法都是为了检验下标信息的
     * @param index 检查下标的有效性
     * @return
     */
    //这个一般删除查找时调用
    private boolean isElements(int index)
    {
        return index>=0&&index<super.size;
    }
    //这个在进行尾插时可以通过.会抛IndexOutOfBoundsException异常
    private void checkElementsIndex(int index)
    {
        if(index<0||index>super.size) {
            throw new IndexOutOfBoundsException(outOfBoundsIndex(index));
        }
    }
    private String outOfBoundsIndex(int index)
    {
        return "Index Out Of Bounds: "+index+", size: "+size;
    }

    /**
     * 构造器
     */
    public MyLinkedList()
    {
        super.size = 0;//抽象类的size字段(protected)
        head = tail =null;
    }

    /**
     * MyCollection接口祖传下来的,默认尾部插入.
     * @param element
     * @return
     */
    @Override
    public boolean add(E element)
    {
        ListNode<E> node = new ListNode<>(element);
        if(isEmpty())
        {
            head = tail = node;
        }
        else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        super.size++;
        return true;
    }

    /**
     * 后续add相关方法多通过这个实现.
     * @param index 索引位置
     * @param element 要插入的元素
     */
    @Override
    public void add(int index, E element) {
        checkElementsIndex(index);
        ListNode<E> node = new ListNode<>(element);
        if(index==0)
        {
            if(isEmpty())
                head = tail = node;
            else {
                node.next = head;
                head.prev = node;
                node.prev = null;
                head = node;
            }
        }
        else if(index==super.size)
        {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        else {
            ListNode<E> cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            ListNode<E> prev = cur.prev;
            prev.next = node;
            node.prev = prev;
            //
            node.next = cur;
            cur.prev = node;
        }
        super.size++;
    }

    @Override
    public E remove(int index) {
        if(isEmpty())
            System.out.println("IsEmpty()!");
        if(!isElements(index))
            return null;
        else {
            if (index == 0) {
                E ret = head.element;
                ListNode<E> tmp = head.next;
                head.next = null;
                head = tmp;
                super.size--;
                return ret;
            } else if (index == super.size - 1) {
                E ret = tail.element;
                ListNode<E> tmp = tail.prev;
                tail.prev = null;
                tail = tmp;
                tail.next = null;
                super.size--;
                return ret;
            } else {
                ListNode<E> cur = head;
                for (int i = 0; i < index; i++) {
                    cur = cur.next;
                }
                ListNode<E> prev = cur.prev;
                ListNode<E> next = cur.next;
                prev.next = next;
                next.prev = prev;
                cur.prev = null;
                cur.next = null;
                super.size--;
                return cur.element;
            }
        }
    }
    //双端队列
    @Override
    public void addFirst(E element)
    {
        add(0,element);
    }
    @Override
    public void addLast(E element)
    {
       add(super.size,element);
    }
    @Override
    public E getFirst()
    {
        return isEmpty()?null:head.element;
    }
    @Override
    public E getLast()
    {
        return isEmpty()?null:tail.element;
    }
    @Override
    public E peekFirst()
    {
       return getFirst();
    }
    @Override
    public E peekLast()
    {
        return getLast();
    }
    @Override
    public E pollFirst(){
        E tmp = peekFirst();
        //执行头删的操作.
        ListNode<E> prev = head;
        head = head.next;
        prev.next = null;
        head.prev=null;
        return tmp;
    }
    @Override
    public E pollLast(){
        E tmp = peekLast();
        //执行尾删的操作
        ListNode<E> next = tail;
        tail = tail.prev;
        next.prev=null;
        tail.next=null;
        return tmp;
    }
    @Override
    public E removeFirst(){
        if(isEmpty())
            throw new NoSuchElementException();
        else
            return remove(0);
    }
    @Override
    public E removeLast()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        else
            return remove(super.size-1);
    }
    //

    /**
     *
     * @param element
     * @return 返回结果真假取决于element是否存在
     */
    @Override
    public boolean remove(E element)
    {
        ListNode<E> cur = head;
        while(cur!=null)
        {
            if(cur.element==element)
            {
                if(cur.prev==null)
                {
                    cur.prev=null;
                    head.next=null;
                    head=cur;
                }
                else if(cur.next==null)
                {
                    cur.next=null;
                    tail.prev=null;
                    tail=cur;
                }
                else{
                    ListNode<E> prev = cur.prev;
                    ListNode<E> next = cur.next;
                    prev.next = next;
                    next.prev = prev;
                    cur.prev=null;
                    cur.next=null;
                }
                super.size--;
            }
            cur=cur.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = tail = null;
        super.size = 0;
    }

    /**
     * 调用了indexOf(),根据函数返回结果.
     * @param element
     * @return
     *
     */
    @Override
    public boolean contains(E element){return indexOf(element) >= 0;}

    /**
     * 对于队列它是用来获取队头元素的,对于双端队列,它通常调用getFirst元素实现.
     * @return
     */
    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public int indexOf(E element)
    {
        ListNode<E> cur = head;
        int index = 0;
        while(cur!=null)
        {
            if(cur.element==element)
                return index;
            cur = cur.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        return 0;
    }

    @Override
    public boolean offerFirst(E element) {
        ListNode<E> node = new ListNode<>(element);
        node.next=head;
        head.prev = node;
        head = node;
        return true;
    }
    @Override
    public boolean offerLast(E element)
    {
        ListNode<E> node = new ListNode<>(element);
        node.prev = tail;
        tail.next = node;
        tail = node;
        return true;
    }
    //当作栈---这里只是提供栈的接口.
    @Override
    public void push(E element)
    {
        addLast(element);
    }
    @Override
    public E pop()
    {
        E tmp = getLast();
        removeLast();
        return tmp;
    }
    @Override
    public E peek()
    {
        return getLast();
    }

    //当作队列---
    @Override
    public boolean offer(E element)
    {
        addLast(element);
        return true;
    }
    @Override
    public E remove()
    {
        E tmp = removeFirst();
        return tmp;
    }
    @Override
    public E poll()
    {
        return getFirst();
    }

    @Override
    public String toString()
    {
        if(isEmpty())
            return "[]";
        StringBuilder s = new StringBuilder("[null<-");
        ListNode<E> cur = head;
        if(cur.next==null)
        {
            s.append(cur.element);
            s.append("->null]");
            return s.toString();
        }
        while(cur.next!=null)
        {
            s.append(cur.element);
            s.append("<=>");
            cur = cur.next;
        }
        s.append(cur.element);
        s.append("->null]");
        return s.toString();
    }
    //直接父类的size方法适用子类,不用重写
    @Override
    public int size(){
        return super.size;
    }

    @Override
    public E get(int index) {
        checkElementsIndex(index);
        ListNode<E> ret = node(index);
        return ret.element;
    }

    @Override
    public E set(int index, E element) {
        checkElementsIndex(index);
        ListNode<E> node = node(index);
        E ret = node.element;
        node.element = element;
        return ret;
    }

    public int print()
    {
        System.out.println(this.toString());
        return 0;
    }

}
