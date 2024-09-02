from Interface.Collection import Collection
from Interface.Iterator import Iterator

class Set(Collection):

    def size(self) -> int:
        pass

    def is_empty(self) -> bool:
        return self.size() == 0

    def contains(self, item) -> bool:
        pass

    def add(self, item) -> bool:
        pass

    def remove(self) -> bool:
        pass

    def clear(self):
        pass

    def __str__(self):
        pass

    def __eq__(self, other):
        pass

    """子类必须重写该方法，否则不允许采用迭代器遍历"""

    def __iter__(self) -> Iterator:
        raise NotImplementedError("Subclasses should implement this!")

