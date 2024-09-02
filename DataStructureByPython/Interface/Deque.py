from abc import abstractmethod
from Interface.Queue import Queue


class Deque(Queue):
    # 双端队列接口
    @abstractmethod
    def add_first(self, e):
        pass

    @abstractmethod
    def add_last(self, e):
        pass

    @abstractmethod
    def remove_first(self):
        pass

    @abstractmethod
    def remove_last(self):
        pass

    @abstractmethod
    def get_first(self):
        pass

    @abstractmethod
    def get_last(self):
        pass

    @abstractmethod
    def poll_first(self):
        pass

    @abstractmethod
    def poll_last(self):
        pass

    @abstractmethod
    def offer_first(self, e):
        pass

    @abstractmethod
    def offer_last(self, e):
        pass

    @abstractmethod
    def peek_first(self):
        pass

    @abstractmethod
    def peek_last(self):
        pass

    # 栈的接口
    @abstractmethod
    def push(self, e):
        pass

    @abstractmethod
    def pop(self):
        pass

    @abstractmethod
    def peek(self):
        pass
