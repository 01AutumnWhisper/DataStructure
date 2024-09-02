from __future__ import annotations  # 在文件的开头添加这行:延长所有类型的注释。
from Interface.Queue import Queue
from Interface.Collection import Collection
from Interface.Iterator import Iterator

from Exception.EmptyException import EmptyException
"""
最大堆和最小堆
优先级队列

堆默认是常使用的二叉堆，而二叉堆仅仅只是优先队列的一种是实现方式罢了。
"""


class PriorityQueue(Queue):
    def __init__(self, _mode: str = 'min', _capacity: int = 11, _extend_factor: float = 2.0):
        self._capacity: int = _capacity if _capacity >= 11 else 11
        self._arr: list[int] = [0] * self._capacity
        self._size: int = 0
        self._extend_factor: float = _extend_factor if _extend_factor >= 1.5 else 2.0
        self._mode: str = _mode if _mode in ['min', 'max'] else 'min'  # 默认最小堆

    """树状打印结构好看一点"""

    @staticmethod
    def __print_level(arr: list[int], size: int, index: int, level: int = 0) -> str:
        """递归地打印堆结构"""
        if index >= size:
            return ""

        result = ""
        # 先打印右子树
        right_child = PriorityQueue.__right(index)
        if right_child < size:
            result += PriorityQueue.__print_level(arr, size, right_child, level + 1)

        # 打印当前节点
        result += "    " * level + str(arr[index]) + "\n"

        # 再打印左子树
        left_child = PriorityQueue.__left(index)
        if left_child < size:
            result += PriorityQueue.__print_level(arr, size, left_child, level + 1)

        return result

    def __str__(self):
        return PriorityQueue.__print_level(self._arr, self._size, 0)

    @staticmethod
    def to_priorityqueue(collection: Collection) -> PriorityQueue:
        """所有实现Collection接口的类均可以转化成优先级队列"""
        pq = PriorityQueue()
        for item in collection:
            pq.add(item)
        return pq

    # 静态私有方法
    @staticmethod
    def __left(i: int) -> int:
        """获取左子节点的索引"""
        return 2 * i + 1

    @staticmethod
    def __right(i: int) -> int:
        """获取右子节点的索引"""
        return 2 * i + 2

    @staticmethod
    def __parent(i: int) -> int:
        """获取父节点的索引"""
        return (i - 1) // 2  # 向下整除

    @staticmethod
    def __swap(arr: list[int], i: int, j: int):
        arr[i], arr[j] = arr[j], arr[i]

    @staticmethod
    def __sift_up(arr: list[int], mode: str, i) -> None:
        p = PriorityQueue.__parent(i)
        if mode == 'min':
            while p >= 0:
                if arr[p] > arr[i]:
                    PriorityQueue.__swap(arr, p, i)
                    i = p
                    p = PriorityQueue.__parent(i)
                else:
                    break
        else:  # self._mode == 'max'
            while p >= 0:
                if arr[p] < arr[i]:
                    PriorityQueue.__swap(arr, p, i)
                    i = p
                    p = PriorityQueue.__parent(i)
                else:
                    break

    @staticmethod
    def __sift_down(arr: list[int], mode: str, p, n):
        child = PriorityQueue.__left(p)
        while child < n:
            if mode == 'min':
                if child + 1 < n and arr[child] > arr[child+1]:
                    child += 1
                if arr[child] < arr[p]:
                    PriorityQueue.__swap(arr, child, p)
                    p = child
                    child = PriorityQueue.__left(p)
                else:
                    break
            else:  # self._mode == 'max'
                if child + 1 < n and arr[child] < arr[child+1]:
                    child += 1
                if arr[child] > arr[p]:
                    PriorityQueue.__swap(arr, child, p)
                    p = child
                    child = PriorityQueue.__left(p)
                else:
                    break

    # @Override
    def size(self) -> int:
        return self._size

    # @Override
    def is_empty(self) -> bool:
        return self._size == 0

    # @Override
    def contains(self, item) -> bool:
        return item in self._arr[:self._size]

    def is_full(self) ->bool:
        return self._size == self._capacity

    def __resize(self):
        self._capacity = int(self._capacity * self._extend_factor)
        arr = [0] * self._capacity
        for i in range(self._size):
            arr[i] = self._arr[i]
        self._arr = arr
    # @Override
    def add(self, item) -> bool:
        if self.is_full():
            self.__resize()
        self._arr[self._size] = item
        PriorityQueue.__sift_up(self._arr, self._mode, self._size)
        self._size += 1

        return True

    # @Override
    def remove(self) -> bool:
        """删除堆顶的元素"""
        try:
            if self.is_empty():
                raise EmptyException("PriorityQueue is Empty")
        except EmptyException as e:
            print(f"{e}")
            return False
        PriorityQueue.__swap(self._arr, 0, self._size-1)
        self._arr[self._size-1] = 0
        self._size -= 1
        PriorityQueue.__sift_down(self._arr, self._mode, 0, self._size)
        return True

    # @Override
    def clear(self):
        self._arr = [0] * self._capacity
        self._size = 0

    # @Override
    def enqueue(self, value: int) -> bool:
        self.add(value)
        return True

    # @Override
    def dequeue(self) -> int:
        tmp = self._arr[0]
        self.remove()
        return tmp

    # @Override
    def peek(self) -> int:
        try:
            if self.is_empty():
                raise EmptyException("PriorityQueue is Empty")
        except EmptyException as e:
            print(f"{e}")
        return self._arr[0]

    # @Override
    def offer(self, value: int) -> bool:
        return self.add(value)

    # @Override
    def poll(self) -> int:
        tmp = self._arr[0]
        self.remove()
        return tmp

    # @Override
    def __eq__(self, other):
        if not isinstance(other, PriorityQueue):
            return False
        elif self._size != other.size():
            return False
        else:
            for i in range(self._size):
                if self._arr[i] != other._arr[i]:
                    return False
            return True

    def __iter__(self) -> PriorityQueue.Iterator:
        """返回优先队列的迭代器"""
        return self.Iterator(self)

    class Iterator(Iterator):
        def __init__(self, pq: PriorityQueue):
            self._pq = pq
            self._index = 0

        def __next__(self):
            if self._index < self._pq._size:
                result = self._pq._arr[self._index]
                self._index += 1
                return result
            else:
                raise StopIteration()
