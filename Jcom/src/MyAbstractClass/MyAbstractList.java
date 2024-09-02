package MyAbstractClass;

import MyInterface.IMyList;

public abstract class MyAbstractList<E> extends MyAbstractCollection<E> implements IMyList<E> {
    /**
     * 返回列表的大小。
     * @return 列表的大小
     */
    @Override
    public abstract int size();

    /**
     * 返回指定索引位置的元素。
     * @param index 索引位置
     * @return 索引位置的元素
     */
    @Override
    public abstract E get(int index);

    /**
     * 设置指定索引位置的元素。
     * @param index 索引位置
     * @param element 新的元素
     * @return 之前在指定位置的元素
     */
    @Override
    public abstract E set(int index, E element);

    /**
     * 添加一个元素到列表的末尾。
     * @param e 要添加的元素
     * @return 如果列表因为这个操作发生了变化，则返回 true
     */
    @Override
    public boolean add(E element) {
        add(size(), element);
        return true;
    }

    /**
     * 在指定的位置插入一个元素。
     * @param index 索引位置
     * @param element 要插入的元素
     */
    @Override
    public abstract void add(int index, E element);

    /**
     * 从列表中删除指定索引位置的元素。
     * @param index 索引位置
     * @return 被删除的元素
     */
    @Override
    public abstract E remove(int index);

    /**
     * 清空列表中的所有元素。
     */
    // @Override
    // public void clear() {
    //     Iterator<E> it = iterator();
    //     while (it.hasNext()) {
    //         it.next();
    //         it.remove();
    //     }
    // }

    /**
     * 返回一个列表迭代器。
     * @return 列表迭代器
    //  */
    // @Override
    // public ListIterator<E> listIterator() {
    //     return listIterator(0);
    // }

    /**
     * 返回一个列表迭代器，从指定索引位置开始。
     * @param index 起始索引
     * @return 列表迭代器
     */
    // @Override
    // public ListIterator<E> listIterator(int index) {
    //     return new ListIterator<E>() {
    //         private final Iterator<E> iterator = subList(index, size()).iterator();
    //         private int currentIndex = index;

    //         @Override
    //         public boolean hasNext() {
    //             return iterator.hasNext();
    //         }

    //         @Override
    //         public E next() {
    //             currentIndex++;
    //             return iterator.next();
    //         }

    //         @Override
    //         public boolean hasPrevious() {
    //             return currentIndex > 0;
    //         }

    //         @Override
    //         public E previous() {
    //             currentIndex--;
    //             return iterator.previous();
    //         }

    //         @Override
    //         public int nextIndex() {
    //             return currentIndex;
    //         }

    //         @Override
    //         public int previousIndex() {
    //             return currentIndex - 1;
    //         }

    //         @Override
    //         public void remove() {
    //             iterator.remove();
    //         }

    //         @Override
    //         public void set(E e) {
    //             throw new UnsupportedOperationException();
    //         }

    //         @Override
    //         public void add(E e) {
    //             throw new UnsupportedOperationException();
    //         }
    //     };
    //}
}
