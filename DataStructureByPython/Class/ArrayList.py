from Interface.List import List
from Exception.EmptyException import EmptyException
'''
MyList可以用作栈双端队列，普通队列，顺序表，双向链表
列表操作应基于有效元素的范围，即 [0, self._size)。谨记
python版数据结构
'''


class ArrayList(List):

    def __str__(self):
        pass

    def __eq__(self, other):
        pass

    def __init__(self, _capacity: int = 10, _enlarge_factor: float = 2):
        self._capacity: int = _capacity  # 初始化默认10个int空间的容量
        self._arr: list[int] = [0] * self._capacity  # 初始化列表
        self._size: int = 0   # 初始化大小
        self._enlarge_factor: float = _enlarge_factor if _enlarge_factor >= 1.5 else 2.0  # 扩容因子

    def __getitem__(self, index) -> int:
        if 0 <= index < self._capacity:
            return self._arr[index]
        else:
            raise IndexError("Index out of range")

    def __setitem__(self, index, value) -> None:
        if 0 <= index < self._capacity:
            self._arr[index] = value
        else:
            raise IndexError("Index out of range")

    # @Override
    def size(self) -> int:
        """获取动态列表内元素的有效个数"""
        return self._size

    # @Override
    def is_empty(self) -> bool:
        """判断当前动态列表是否为空"""
        return self._size == 0

    def is_full(self) -> bool:
        """判断当前动态列表是否满容量"""
        return self._size == self._capacity

    # @Override
    def contains(self, x: int) -> bool:
        """判断x是否在动态列表内。"""
        for i in range(self._size):
            if self._arr[i] == x:
                return True
        else:
            return False

    def capacity(self) -> int:
        """获取容量"""
        return self._capacity

    def get_at(self, index: int) -> int:
        """获取index下标的值"""
        if 0 <= index < self._capacity:
            return self._arr[index]

    def set_at(self, index: int, value: int) -> None:
        """设置index下标的值"""
        if 0 <= index < self._capacity:
            self._arr[index] = value

    def __enlarge(self) -> None:
        """拷贝数组"""
        new_capacity = int(self._capacity * self._enlarge_factor)
        new_arr = [0] * new_capacity
        for i in range(self._size):
            new_arr[i] = self._arr[i]
        self._arr = new_arr
        self._capacity = new_capacity

    #  Override
    def add(self, item) -> bool:
        """默认执行尾部插入"""
        if self.is_full():
            self.__enlarge()
        self._arr[self._size] = item
        self._size += 1
        return True

    def insert(self, item: int, index: int):
        """在中间插入元素"""
        if index < 0 or index >= self._size:
            raise IndexError("Index Out of Range: index < 0 or index >= self.size")  # 越界抛异常
        if self.is_full():
            self.__enlarge()  # 扩容
        # 将索引 index 及其之后的元素都向后移动一位
        for j in range(self._size - 1, index - 1, -1):  # [self._size-1, index] ,把index的位置腾出来。
            self._arr[j + 1] = self._arr[j]
        self._arr[index] = item
        self._size += 1

    def remove(self) -> None:
        self.remove_last()

    def add_last(self, item: int) -> None:
        """尾部插入元素item"""
        if self.is_full():
            self.__enlarge()
        self.add(item)
        self._size += 1

    def add_first(self, item: int) -> None:
        """头部插入元素item"""
        if self.is_full():
            self.__enlarge()
        for j in range(self._size - 1, -1, -1):
            self._arr[j + 1] = self._arr[j]
        self._arr[0] = item
        self._size += 1

    def remove_last(self) -> None:
        try:
            if self.is_empty():
                raise EmptyException("Stack is Empty")
        except EmptyException as e:
            print(f'{e}')
        self._arr[self._size - 1] = 0
        self._size -= 1

    def remove_first(self) -> None:
        try:
            if self.is_empty():
                raise EmptyException("Stack is Empty")
        except EmptyException as e:
            print(f'{e}')
            return
        for j in range(0, self._size - 1):
            self._arr[j] = self._arr[j + 1]
        self._arr[self._size - 1] = 0
        self._size -= 1

    def index_of(self, item: int) -> int:
        try:
            if self.is_empty():
                raise EmptyException("Stack is Empty")
        except EmptyException as e:
            print(f'{e}')

        for i in range(self._size):
            if self._arr[i] == item:
                return i
        return -1

    def last_index_of(self, item: int) -> int:
        try:
            if self.is_empty():
                raise EmptyException("Stack is Empty")
        except EmptyException as e:
            print(f'{e}')
        for i in range(self._size - 1, -1, -1):
            if self._arr[i] == item:
                return i
        return -1

    def clear(self) -> None:
        self._arr = [0] * self._capacity
        self._size = 0

    def sort(self) -> None:
        """当然，你可以手写一个随机化的快速排序"""
        self._arr.sort()
