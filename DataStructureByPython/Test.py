from Class.PriorityQueue import PriorityQueue
from Class.Stack import Stack
from Exception.EmptyException import EmptyException


def test_priority_queue():
    # 创建一个最小堆优先级队列
    pq = PriorityQueue(_mode='min')

    # 添加元素
    pq.add(10)
    pq.add(5)
    pq.add(7)
    pq.add(9)
    pq.add(11)

    # 打印队列的堆结构
    print("PriorityQueue structure:")
    print(pq)

    # 测试队列大小
    assert pq.size() == 5, "Size should be 5"

    # 测试队列的最小值
    assert pq.peek() == 5, "Peek should return the minimum element, which is 5"

    # 删除元素
    assert pq.remove() == True, "Remove should succeed"
    print("PriorityQueue after removal:")
    print(pq)

    # 测试队列大小
    assert pq.size() == 4, "Size should be 4"

    # 测试队列的最小值
    assert pq.peek() == 7, "Peek should return the new minimum element, which is 7"

    # 测试迭代器
    print("Iterating over PriorityQueue:")
    for item in pq:
        print(item)

    # 清空队列
    pq.clear()
    assert pq.size() == 0, "Size should be 0 after clearing"

    # 测试从空队列中删除元素
    try:
        pq.remove()
    except IndexError as e:
        print("Caught expected exception:", e)

    # 测试从空队列中查看最小值
    try:
        pq.peek()
    except IndexError as e:
        print("Caught expected exception:", e)

def test_stack():
    # 创建一个栈实例
    stack = Stack()

    # 测试栈是否为空
    assert stack.empty() == True, "Stack should be empty initially"

    # 推入元素
    stack.push(10)
    stack.push(20)
    stack.push(30)

    # 测试栈的大小
    assert stack.size() == 3, "Stack size should be 3 after pushing 3 elements"

    # 测试栈顶元素
    assert stack.peek() == 30, "Peek should return the top element, which is 30"

    # 弹出元素
    assert stack.pop() == 30, "Pop should return the top element, which is 30"
    assert stack.size() == 2, "Stack size should be 2 after popping one element"

    # 测试栈顶元素
    assert stack.peek() == 20, "Peek should return the new top element, which is 20"

    # 弹出所有元素
    assert stack.pop() == 20, "Pop should return the top element, which is 20"
    assert stack.pop() == 10, "Pop should return the last element, which is 10"

    # 测试栈是否为空
    assert stack.empty() == True, "Stack should be empty after popping all elements"

    # 弹出操作应该引发异常
    try:
        stack.pop()
    except EmptyException as e:
        print("Caught expected exception:", e)

    # 查看栈顶操作应该引发异常
    try:
        stack.peek()
    except EmptyException as e:
        print("Caught expected exception:", e)

# 运行测试
test_stack()


def main():
    pq = PriorityQueue()
    pq.enqueue(10)
    pq.offer(5)
    pq.offer(7)
    pq.offer(9)
    pq.offer(11)
    pq.offer(13)
    for i in pq:
        print(i)
