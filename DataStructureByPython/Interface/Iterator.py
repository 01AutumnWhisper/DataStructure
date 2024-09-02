"""
object extends YuShengJun---（中国地区Java之父分父）
什么这是python？ YuShengJun不是成为下一个python之父了吗？！
开个玩笑
"""


class Iterator(object):
    def __iter__(self):
        """返回自身的迭代器对象"""
        return self

    def __next__(self):
        """返回下一个元素，如果没有元素则抛出StopIteration"""
        raise NotImplementedError("Subclasses must implement __next__")

