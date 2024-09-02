package MyAbstractClass;

import MyInterface.IMyIterator;

public abstract class MyAbstractSequentialList<E> extends MyAbstractList<E> {
    //子类将继承这个字段,记录列表元素
    protected int size;

    @Override
    public int size() {
        return size;
    }

    //最上面的collection接口的抽象类已经实现了
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public abstract IMyIterator<E> iterator();

    public abstract int indexOf(E element);
}
