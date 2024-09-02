from Class.ArrayList import ArrayList


class Stack(ArrayList):
    def __init__(self):
        super().__init__()

    def push(self, item: int) -> int:
        self.add(item)
        return item

    def pop(self) -> int:
        tmp = self._arr[self._size - 1]
        self.remove()
        return tmp

    def size(self) -> int:
        return self._size

    def empty(self) -> bool:
        return self._size == 0

    def peek(self) -> int:
        try:
            if self.empty():
                raise Exception("Stack is empty")
        except Exception as e:
            print(f'{e}')
        return self._arr[self._size - 1]







