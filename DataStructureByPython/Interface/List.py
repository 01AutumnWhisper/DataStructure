from abc import abstractmethod
from Interface.Collection import Collection


class List(Collection):
    @abstractmethod
    def sort(self):
        pass

    @abstractmethod
    def index_of(self, value) -> int:
        pass

    @abstractmethod
    def last_index_of(self, value) -> int:
        pass

    @abstractmethod
    def get_at(self, index: int) -> int:
        pass

    @abstractmethod
    def set_at(self, index: int, value: int) -> None:
        pass