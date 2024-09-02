package MyClass.ArrayList;

import MyInterface.IMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class MyHashMap<K,V> implements IMap<K,V> {
    public static class Node<K,V> implements Entry<K,V>
    {
        final K key;
        V val;
        Node<K,V> next;
        public Node(K key,V val)
        {
            this.key = key;
            this.val = val;
        }
        //实现Entry接口
        public K getKey() { return key;}
        public V getValue() { return val;}
        public V setValue(V value) {
            V oldVal = val;
            val = value;
            return oldVal;
        }
        public String toString()
        {
            return key+"="+val;
        }
        public int hashCode() {
            int kHashCode = key == null?0:key.hashCode();
            int vHashCode = val == null?0:val.hashCode();
            return kHashCode ^ vHashCode;
        }
        public boolean equals(Object o) {
            if(o==this)
                return true;
            return o instanceof Entry<?,?> e
                    && key.equals(e.getKey())
                    && val.equals(e.getValue());

        }
    }
    public static final int DEFAULT_CAPACITY = 1<<4 + 1;//默认数组容量17
    public static final double DEFAULT_LOAD_FACTOR = 0.75;//负载因子
    private Node<K,V>[] array = (Node<K,V>[])new Node[DEFAULT_CAPACITY];
    private int usedSize;
    private boolean isPrime(int x){
        //x必定大于DEFAULT_CAPACITY;
        if(x%2==0||x%3==0)
            return false;
        for (int i = 5; i < Math.floor(Math.sqrt(x)); i+=6) {
            if(x%2==0||x%3==0)
                return false;
        }
        return true;
    }
    private int nextPrime(int x)
    {
        while(!isPrime(x))
        {
            //向上调整直到为整数为止.
            x+=1;
        }
        return x;
    }
    public Node<K,V> getNode(K key)
    {
        for (int i = 0; i < array.length; i++) {
            Node<K,V> cur = array[i];
            while(cur!=null)
            {
                if(key.equals(cur.key))
                {
                    return cur;
                }
                cur = cur.next;
            }
        }
        return null;
    }
    public int size() {return this.usedSize;}

    public boolean isEmpty(){return size()==0;}

    private void grow()
    {
        int newCapacity = nextPrime(array.length*2+1);
        Node<K,V>[] newArray = (Node<K,V>[])new Object[newCapacity];
        for (int i = 0; i < array.length; i++) {

        }
    }
    public V put(K key,V val)
    {
        if(usedSize*1.0/array.length>DEFAULT_LOAD_FACTOR)
        {
            grow();
        }
        Node<K,V> node = new Node<>(key,val);
        int index = node.hashCode()% array.length;

        Node<K,V> cur = array[index];
        while(cur!=null)
        {
            //这个if是为了避免重复覆盖
            if(key.equals(cur.key)) {
                cur.val = val;
                return val;
            }
            cur = cur.next;
        }
        node.next = array[index];
        array[index] = node;
        usedSize++;
        return val;
    }

    public V get(Object key)
    {
        return getNode((K)key).val;
    }
    public V remove(Object key)
    {
        Node<K,V> ret = getNode((K)key);
        if(ret == null) return null;
        int index = ret.hashCode()% array.length;
        Node<K,V> prev = null;
        Node<K,V> cur = array[index];
        while(cur.next!=null)
        {
            if(cur==ret)
                break;
            prev = cur;
            cur = cur.next;
        }
        if(cur==array[index])
            array[index] = cur.next;
        else
        {
            prev.next = cur.next;
        }
        usedSize--;
        return ret.getValue();
    }
    public boolean containsKey(Object key)
    {
        return getNode((K)key)==null;
    }
    public boolean containsValue(Object value)
    {
        for (int i = 0; i < array.length; i++) {
            Node<K,V> cur = array[i];
            while(cur!=null)
            {
                if(value.equals(cur.val))
                {
                    return true;
                }
                cur = cur.next;
            }
        }
        return false;
    }
    public void clear()
    {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }
    public Set<K> keySet()
    {
        Set<K> set = new TreeSet<>();
        for (int i = 0; i < array.length; i++) {
            Node<K,V> cur = array[i];
            while(cur!=null)
            {
                set.add(cur.key);
                cur = cur.next;
            }
        }
        return set;
    }
    public Collection<V> values(){
        Collection<V> coll = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            Node<K,V> cur = array[i];
            while(cur!=null)
            {
                coll.add(cur.val);
                cur = cur.next;
            }
        }
        return coll;
    }
    public Set<IMap.Entry<K, V>> entrySet()
    {
        Set<IMap.Entry<K,V>> entry = new TreeSet<>();
        for (int i = 0; i < array.length; i++) {
            Node<K,V> cur = array[i];
            while(cur!=null)
            {
                entry.add(cur);
                cur = cur.next;
            }
        }
        return entry;
    }

}
