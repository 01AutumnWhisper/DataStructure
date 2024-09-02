package MyAbstractClass;

import MyInterface.IMyCollection;

public abstract class MyAbstractCollection<E> implements IMyCollection<E> {
    @Override
    public abstract int size();

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回集合的迭代器。
     *
     * @return 集合的迭代器
     */
    // @Override
    // public abstract Iterator<E> iterator();

    /**
     * 添加一个元素到集合中。
     *
     * @param element 要添加的元素
     * @return 如果集合因为这个操作发生了变化，则返回 true
     */
    @Override
    public boolean add(E element) {
        throw new UnsupportedOperationException("add operation not supported");
    }

    /**
     * 从集合中删除一个元素。
     *
     * @param element 要删除的元素
     * @return 如果集合中存在并且成功删除了元素，则返回 true
     */
    // @Override
    // public boolean remove(Object element) {
    //     Iterator<E> it = iterator();
    //     while (it.hasNext()) {
    //         if (element.equals(it.next())) {
    //             it.remove();
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // /**
    //  * 清空集合中的所有元素。
    //  */
    // @Override
    // public void clear() {
    //     Iterator<E> it = iterator();
    //     while (it.hasNext()) {
    //         it.next();
    //         it.remove();
    //     }
    // }
}

