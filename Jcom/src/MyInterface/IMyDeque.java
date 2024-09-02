package MyInterface;

/**
 * IMyDeque 接口
 *
 * 该接口表示一个双端队列，扩展了 IMyQueue 接口。
 * 它提供了双端队列和栈的操作。
 *
 * @param <E> 队列中持有的元素类型
 */
public interface IMyDeque<E> extends IMyQueue<E> {

    /**
     * 尝试将指定元素添加到此双端队列中。
     * IMyCollection祖传抽象方法,默认尾部添加
     * @param element 要添加的元素
     * @return 如果元素成功添加到此双端队列中，则返回 true；否则返回 false
     */
    boolean add(E element);

    /**
     * 将指定元素添加到双端队列的开头。
     *
     * @param element 要添加的元素
     */
    void addFirst(E element);

    /**
     * 将指定元素添加到双端队列的末尾。
     *
     * @param element 要添加的元素
     */
    void addLast(E element);

    /**
     * 返回此双端队列中的元素数。
     *
     * @return 此双端队列中的元素数
     */
    @Override
    int size();

    /**
     * 如果此双端队列包含指定的元素，则返回 true。
     *
     * @param element 要检查是否包含的元素
     * @return 如果此双端队列包含指定的元素，则返回 true
     */
    @Override
    boolean contains(E element);

    /**
     * 检索但不移除此双端队列的头元素。
     *
     * @return 此双端队列的头元素
     * @throws NoSuchElementException 如果此双端队列为空
     */
    @Override
    E element();

    // 双端队列操作

    /**
     * 获取并返回双端队列的第一个元素。
     *
     * @return 双端队列的第一个元素
     * @throws NoSuchElementException 如果此双端队列为空
     */
    E getFirst();

    /**
     * 获取并返回双端队列的最后一个元素。
     *
     * @return 双端队列的最后一个元素
     * @throws NoSuchElementException 如果此双端队列为空
     */
    E getLast();

    /**
     * 获取但不移除双端队列的第一个元素，如果此双端队列为空，则返回 null。
     *
     * @return 双端队列的第一个元素，如果此双端队列为空，则返回 null
     */
    E peekFirst();

    /**
     * 获取但不移除双端队列的最后一个元素，如果此双端队列为空，则返回 null。
     *
     * @return 双端队列的最后一个元素，如果此双端队列为空，则返回 null
     */
    E peekLast();

    /**
     * 获取并移除双端队列的第一个元素，如果此双端队列为空，则返回 null。
     *
     * @return 双端队列的第一个元素，如果此双端队列为空，则返回 null
     */
    E pollFirst();

    /**
     * 获取并移除双端队列的最后一个元素，如果此双端队列为空，则返回 null。
     *
     * @return 双端队列的最后一个元素，如果此双端队列为空，则返回 null
     */
    E pollLast();

    /**
     * 获取并移除双端队列的第一个元素。
     *
     * @return 双端队列的第一个元素
     * @throws NoSuchElementException 如果此双端队列为空
     */
    E removeFirst();

    /**
     * 获取并移除双端队列的最后一个元素。
     *
     * @return 双端队列的最后一个元素
     * @throws NoSuchElementException 如果此双端队列为空
     */
    E removeLast();

    /**
     * 将指定元素插入双端队列的开头。
     *
     * @param e 要添加的元素
     * @return 如果元素成功添加到此双端队列中，则返回 true；否则返回 false
     */
    boolean offerFirst(E e);

    /**
     * 将指定元素插入双端队列的末尾。
     *
     * @param e 要添加的元素
     * @return 如果元素成功添加到此双端队列中，则返回 true；否则返回 false
     */
    boolean offerLast(E e);

    // 栈操作 (这些方法提供了栈的接口)

    /**
     * 将元素推入栈顶。
     *
     * @param e 要推入栈顶的元素
     */
    void push(E e);

    /**
     * 获取并移除栈顶的元素。
     *
     * @return 栈顶的元素
     * @throws NoSuchElementException 如果此栈为空
     */
    E pop();

    /**
     * 获取但不移除栈顶的元素。
     *
     * @return 栈顶的元素
     * @throws NoSuchElementException 如果此栈为空
     */
    E peek();

    // 队列操作

    /**
     * 尝试将指定元素插入此队列中。
     *
     * @param element 要添加的元素
     * @return 如果元素成功添加到此队列中，则返回 true；否则返回 false
     */
    boolean offer(E element);

    /**
     * 获取并移除队列的头元素。
     *
     * @return 队列的头元素
     * @throws NoSuchElementException 如果此队列为空
     */
    E remove();

    /**
     * 获取并移除队列的头元素，如果此队列为空，则返回 null。
     *
     * @return 队列的头元素，如果此队列为空，则返回 null
     */
    E poll();

    // 其他操作

    /**
     * 移除指定元素的第一个匹配项。
     *
     * @param element 要移除的元素
     * @return 如果成功移除，则返回 true；否则返回 false
     */
    boolean remove(E element);
}