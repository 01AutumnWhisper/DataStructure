from abc import abstractmethod
from Interface.Iterable import Iterable
from Interface.Iterator import Iterator


class Collection(Iterable):
    @abstractmethod
    def size(self) -> int:
        pass

    def is_empty(self) -> bool:
        return self.size() == 0

    @abstractmethod
    def contains(self, item) -> bool:
        pass

    @abstractmethod
    def add(self, item) -> bool:
        pass

    @abstractmethod
    def remove(self) -> bool:
        pass

    @abstractmethod
    def clear(self):
        pass

    @abstractmethod
    def __str__(self):
        pass

    @abstractmethod
    def __eq__(self, other):
        pass

    """子类必须重写该方法，否则不允许采用迭代器遍历"""
    def __iter__(self) -> Iterator:
        raise NotImplementedError("Subclasses should implement this!")


