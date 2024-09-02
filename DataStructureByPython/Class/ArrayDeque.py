from Interface.Deque import Deque
from Exception.EmptyException import EmptyException
from Exception.FullException import  FullException
import sys

"""
双端队列模式： mode = 'deque'---- 可以充当栈和队列使用，但不能调用栈和队列特有的方法，如果不指定模式，则默认为双端队列
栈模式： mode = 'stack' ---- 较双端队列解放了调用栈方法---push,peek,pop等
队列模式： mode = 'queue' ---- 较双端队列，解放了调用普通队列的经典操作---offer,enqueue,dequeue,poll,peek.
栈特有的经典方法，队列特有的经典方法，双端队列均不能调用。 可以调用自身offer_first,offer_last,poll_first,poll_last等，既当栈又当队列使用。
"""


class ArrayDeque(Deque):
    def __init__(self, _mode: str = "deque", _capacity: int = 11, _extend_factor: float = 2.0):
        self._capacity: int = _capacity if _capacity >= 11 else 11
        self._arr: list[int] = [0] * self._capacity
        self._size: int = 0
        self._front: int = 0
        self._rear: int = 0
        self._extend_factor: float = _extend_factor if _extend_factor >= 1.5 else 2.0
        self._mode: str = _mode if _mode in ['deque', 'queue', 'stack'] else 'deque'
        # "deque"：双端队列模式； "queue": 队列模式： "stack": 栈模式

    # @Override
    def size(self) -> int:
        return self._size

    # @Override
    def is_empty(self) -> bool:
        return self.size() == 0

    def is_full(self) -> bool:
        return (self._rear + 1) % self._capacity == self._front

    # @Override
    def add(self, item: int) -> bool:
        try:
            if self.is_full():
                raise FullException("ArrayDeque is Full")
        except FullException as e:
            print(f"FullException: {e}. Perhaps you should call the resize function?")
            return False
        self._arr[self._rear] = item
        self._rear = (self._rear + 1) % self._capacity
        self._size += 1
        return True

    def resize(self) -> None:
        """扩容方法"""
        new_capacity = int(self._capacity * self._extend_factor)
        new_arr = [0] * new_capacity
        for i in range(self._size):
            new_arr[i] = self._arr[(self._front + i) % self._capacity]
        self._arr = new_arr
        self._capacity = new_capacity
        self._front = 0
        self._rear = self._size

    # @Override
    def remove(self):
        try:
            if self.is_empty():
                raise EmptyException("ArrayDeque is Empty")
        except EmptyException as e:
            print(f"ArrayDeque is {e}.")

    # @Override
    def contains(self, item) -> bool:
        for i in range(self._size):
            if self._arr[(self._front + i) % self._size] == item:
                return True
        else:
            return False

    # @Override
    def __str__(self):
        s = "ArrayDeque:"
        if self._size == 0:
            return s + " []"  # 队列为空时，直接返回空数组
        for i in range(self._size):
            s += f"[{self._arr[(self._front + i) % self._capacity]}]"
            if i < self._size - 1:
                s += "->"
        return s

    # @Override
    def __eq__(self, other) -> bool:
        if other is None:
            return False
        elif not isinstance(other, ArrayDeque):
            return False
        elif self._size != other.size():
            return False
        else:
            for i in range(self._size):
                if self._arr[(self._front + i) % self._capacity] != other._arr[(other._front + i) % other._capacity]:
                    return False
            return True

    def set_mode(self, mode: str) -> None:
        """动态的修改模式"""
        if mode not in ['queue', 'stack', 'Deque']:
            raise ValueError("Invalid mode. Use 'queue' or 'stack','Deque'")
        self._mode = mode

    # 实现Deque接口
    # @Override
    def add_first(self, e) -> None:
        try:
            if self.is_full():
                raise FullException("ArrayDeque is Full")
        except FullException as e:
            print(f"FullException: {e}. Perhaps you should call the resize function?")
            return
        # Python 中，负数索引会自动处理为数组的末尾。
        self._front = (self._front - 1 + self._capacity) % self._capacity
        self._arr[self._front] = e
        self._size += 1

    def add_last(self, e) -> None:
        try:
            if self.is_full():
                raise FullException("ArrayDeque is Full")
        except FullException as e:
            print(f"FullException: {e}. Perhaps you should call the resize function?")
            return
        # 尾插：先放值，后修正
        self._arr[self._rear] = e
        self._rear = (self._rear + 1) % self._capacity
        self._size += 1

    # @Override
    def remove_first(self) -> int:
        try:
            if self.is_empty():
                raise EmptyException("ArrayDeque is Empty")
        except EmptyException as e:
            print(f"ArrayDeque is {e}.")
            return sys.maxsize
        item = self._arr[self._front]
        self._front = (self._front + 1) % self._capacity
        self._size -= 1
        return item

    # @Override
    def remove_last(self) -> int:
        try:
            if self.is_empty():
                raise EmptyException("ArrayDeque is Empty")
        except EmptyException as e:
            print(f"ArrayDeque is {e}.")
            return sys.maxsize
        self._rear = (self._rear - 1 + self._capacity) % self._capacity
        item = self._arr[self._rear]
        self._size -= 1
        return item

    # @Override
    def get_first(self) -> int:
        try:
            if self.is_empty():
                raise EmptyException("ArrayDeque is Empty")
        except EmptyException as e:
            print(f"ArrayDeque is {e}.")
        return self._arr[self._front]

    # @Override
    def get_last(self) -> int:
        try:
            if self.is_empty():
                raise EmptyException("ArrayDeque is Empty")
        except EmptyException as e:
            print(f"ArrayDeque is {e}.")
        return self._arr[(self._rear - 1 + self._capacity) % self._capacity]

    # @Override
    def poll_first(self):
        return self.remove_first()

    # @Override
    def poll_last(self):
        return self.remove_last()

    # @Override
    def offer_first(self, e) -> None:
        self.add_first(e)

    # @Override
    def offer_last(self, e) -> None:
        self.add_last(e)

    # @Override
    def peek_first(self) -> int:
        return self.get_first()

    # @Override
    def peek_last(self) -> int:
        return self.get_last()

    # 一般队列
    # @Override
    def enqueue(self, value):
        if self._mode == "queue":
            self.add_last(value)
        else:
            raise ValueError("Invalid mode. Use 'queue'.")

    # @Override
    def dequeue(self) -> int:
        if self._mode == "queue":
            return self.remove_first()
        else:
            raise ValueError("Invalid mode. Use 'queue'.")

    # @Override
    def peek(self) -> int:
        if self._mode == "queue":
            return self.get_first()
        elif self._mode == "stack":
            return self.get_last()
        else:
            raise ValueError("Invalid mode. Use 'queue' or 'stack'.")  # 双端队列禁止调用此法。

    # @Override
    def offer(self, value):
        if self._mode == "queue":
            self.add_last(value)
        else:
            raise ValueError("Invalid mode. Use 'queue'.")

    # @Override
    def poll(self):
        if self._mode == "queue":
            return self.remove_first()
        else:
            raise ValueError("Invalid mode. Use 'queue'.")

    # 栈的接口
    # @Override
    def push(self, e) -> int:
        if self._mode == "stack":
            self.add_last(e)
            return e
        else:
            raise ValueError("Invalid mode. Use 'stack'.")

    # @Override
    def pop(self) -> int:
        if self._mode == "stack":
            return self.remove_last()
        else:
            raise ValueError("Invalid mode. Use 'stack'.")

    # @Override
    def clear(self):
        self._arr = [0] * self._capacity
        self._front = 0
        self._rear = 0
        self._size = 0
