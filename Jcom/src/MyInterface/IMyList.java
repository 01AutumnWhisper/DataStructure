package MyInterface;

public interface IMyList<E> extends IMyCollection<E> {
    //positional access and Search Operations
    E get(int index);

    E set(int index, E element);//覆盖index元素，并返回被覆盖值

    void add(int index, E element);//指定位置插入元素

    E remove(int index);//删除指定位置的元素，并返回被删除值

    int indexOf(E element);//从第一个位置查找元素，返回第一个匹配的index

    int lastIndexOf(E element);//从最后一个位置倒数查找元素，返回第一个匹配的index
}
