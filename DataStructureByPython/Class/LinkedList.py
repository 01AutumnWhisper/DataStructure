from __future__ import annotations  # 在文件的开头添加这行:延长所有类型的注释。
from Interface.List import List
from Interface.Deque import Deque
from Exception.EmptyException import EmptyException


class LinkedList(List, Deque):
    class Node:
        def __init__(self, data):
            self.data = data
            self.next = self
            self.prev = self

    def __init__(self):
        self.__head = self.Node(0)
        self.__tail = self.__head
        self.__size = 0

    def size(self) -> int:
        return self.__size

    def is_empty(self) -> bool:
        return self.__size == 0

    def contains(self, item) -> bool:
        cur = self.__head.next
        while cur is not self.__head:
            if cur.data == item:
                return True
            cur = cur.next
        return False

    def add(self, item) -> bool:
        """默认尾部插入"""
        node = self.Node(item)
        self.__tail.next = node
        node.prev = self.__tail
        self.__tail = node
        self.__tail.next = self.__head  # 修改为正确的循环链接
        self.__size += 1  # 增加 size 计数
        return True

    def remove(self) -> bool:
        try:
            if self.is_empty():
                raise EmptyException('LinkedList is empty')
        except EmptyException as e:
            print(f'{e}')
            return False
        tmp = self.__tail.prev
        tmp.next = self.__head
        self.__head.prev = tmp
        self.__size -= 1

    def clear(self):
        self.__head = self.Node(0)
        self.__tail = self.__head
        self.__size = 0

    def __str__(self) -> str:
        if self.is_empty():
            return 'Empty List'
        else:
            s = 'LinkedList:'
            cur = self.__head.next
            while cur is not self.__head:
                s += str(cur.data) + "->"
                cur = cur.next
            return s

    def __eq__(self, other):
        if not isinstance(other, LinkedList):
            return False
        elif self.__size != other.__size:
            return False
        else:
            cur1 = self.__head.next
            cur2 = other.__head.next
            while cur1 is not self.__head:
                if cur1.data != cur2.data:
                    return False
                cur1 = cur1.next
                cur2 = cur2.next
            return True

    def __iter__(self) -> LinkedList.Iterator:
        return self.Iterator(self.__head, self.__tail)

    class Iterator:
        def __init__(self, head, tail):
            self.__head = head.next
            self.__tail = tail

        def __iter__(self):
            if self.__head is self.__tail.next:
                result = self.__head.data
                self.__head = self.__head.next
                return result
            else:
                raise StopIteration()

    # 链表最优排序算法是归并排序
    def __split(self, head):
        if not head or not head.next:
            return head, None
        slow = head
        fast = head
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
        middle = slow.next
        slow.next = None
        return head, middle

    def __merge(self, left, right):
        dummy = self.Node(0)
        tail = dummy
        while left and right:
            if left.data <= right.data:
                tail.next = left
                left.prev = tail
                left = left.next
            else:
                tail.next = right
                right.prev = tail
                right = right.next
            tail = tail.next
        tail.next = left if left else right
        if tail.next:
            tail.next.prev = tail
        return dummy.next

    def __merge_sort(self, head):
        if not head or not head.next:
            return head
        left, right = self.__split(head)
        left = self.__merge_sort(left)
        right = self.__merge_sort(right)
        return self.__merge(left, right)

    def sort(self):
        self.__head.next = self.__merge_sort(self.__head.next)
        if self.__head.next:
            self.__head.next.prev = self.__head
        cur = self.__head.next
        while cur and cur.next:
            cur = cur.next
        self.__tail = cur

    def index_of(self, value) -> int:
        cur = self.__head.next
        index = 0
        while cur:
            if cur.data == value:
                return index
            cur = cur.next
            index += 1
        return -1  # Not found

    def last_index_of(self, value) -> int:
        last_index = -1
        cur = self.__head.next
        index = 0
        while cur:  # 等效于while cur is not None
            if cur.data == value:
                last_index = index
            cur = cur.next
            index += 1
        return last_index

    def get_at(self, index: int) -> int:
        if index < 0 or index >= self.__size:
            raise IndexError("Index out of bounds")
        cur = self.__head.next
        for _ in range(index):
            cur = cur.next
        return cur.data

    def set_at(self, index: int, value: int) -> None:
        if index < 0 or index >= self.__size:
            raise IndexError("Index out of bounds")
        cur = self.__head.next
        for _ in range(index):
            cur = cur.next
        cur.data = value

    # Queue接口
    def enqueue(self, value):
        self.add(value)

    def dequeue(self):
        if self.is_empty():
            raise EmptyException('LinkedList is empty')
        else:
            cur = self.__head.next
            self.__head.next = cur.next
            cur.prev = None
            if cur.next is not None:
                cur.next.prev = self.__head
            self.__size -= 1
            return cur.data

    def peek(self):
        if self.is_empty():
            raise EmptyException('LinkedList is empty')
        else:
            return self.__head.next.data

    def offer(self, value):
        self.add(value)

    def poll(self):
        return self.dequeue()

    def add_first(self, e):
        node = self.Node(e)
        cur = self.__head.next
        self.__head.next = node
        cur.prev = node
        node.prev = self.__head
        node.next = cur
        self.__size += 1

    def add_last(self, e):
        self.enqueue(e)

    def remove_first(self):
        self.dequeue()

    def remove_last(self):
        self.remove()

    def get_first(self) -> int:
        return self.peek()

    def get_last(self) -> int:
        return self.__tail.data

    def poll_first(self):
        return self.dequeue()

    def poll_last(self):
        cur = self.__tail.prev
        cur.next = self.__head
        self.__head.prev = cur
        ret = self.__tail.data
        self.__tail = cur
        return ret

    def offer_first(self, e):
        self.add_first(e)

    def offer_last(self, e):
        self.add(e)

    def peek_first(self):
        return self.get_first()

    def peek_last(self):
        return self.get_last()

    # 栈的接口
    def push(self, e):
        return self.add_first(e)

    def pop(self):
        return self.poll_first()
