package MyInterface;

import java.util.NoSuchElementException;

public interface IMyQueue<E> extends IMyCollection<E> {
    /**
     * 如果可能，将指定的元素插入此队列，
     * 不违反容量限制。
     *
     * @param element 要添加的元素
     * @return 如果将元素添加到此队列中，则返回 true，否则返回 false
     */
    boolean offer(E element);

    /**
     * 检索并移除此队列的头元素。
     *
     * @return 此队列的头元素
     * @throws NoSuchElementException 如果此队列为空
     */
    E remove();

    /**
     * 检索但不移除此队列的头元素，如果此队列为空，则返回 null。
     *
     * @return 此队列的头元素，如果此队列为空，则返回 null
     */
    E peek();

    /**
     * 检索并移除此队列的头元素，如果此队列为空，则返回 null。
     *
     * @return 此队列的头元素，如果此队列为空，则返回 null
     */
    E poll();

    /**
     * 检索但不移除此队列的头元素。
     *
     * @return 此队列的头元素
     * @throws NoSuchElementException 如果此队列为空
     */
    E element();

    /**
     * 如果可能，将指定的元素添加到此队列，
     * 不违反容量限制。
     *
     * @param element 要添加的元素
     * @return 如果将元素添加到此队列中，则返回 true，否则返回 false
     */
    boolean add(E element);
}
