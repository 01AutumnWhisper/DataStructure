package MyInterface;

public interface IMyCollection<E> extends IMyIterable<E> {
    int size();//获取集合中的元素个数。

    boolean isEmpty();//判断集合是否为空

    boolean contains(E element);//判断元素（对象）是否在集合中

    boolean add(E element);//增加集合中的元素

    boolean remove(E element); // 删除集合中的元素

        //聚合运算,lamda表达式.这里只涉及迭代器如何使用

//     boolean containsAll(MyCollection<?> c); //判断集合是否包含另一个集合中的所有元素

//     boolean addAll(MyCollection<? extends E> c);

//     boolean removeAll(MyCollection<?> c);

//     boolean retainAll(MyCollection<?> c);

    void clear();//清空集合中的元素

// //操作数组----将集合--->数组
//     Object[] toArray(); // 返回一个数组

//     <T> T[] toArray(T[] a);
}

