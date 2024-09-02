from abc import abstractmethod
from Interface.Collection import Collection


class Queue(Collection):
    @abstractmethod
    def enqueue(self, value):
        pass

    @abstractmethod
    def dequeue(self):
        pass

    @abstractmethod
    def peek(self):
        pass

    @abstractmethod
    def offer(self, value):
        pass

    @abstractmethod
    def poll(self):
        pass


