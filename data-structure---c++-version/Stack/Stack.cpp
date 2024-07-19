#define _CRT_SECURE_NO_WARNINGS 1

#include<iostream>//标准输入输出流是吧
#include<stdlib.h>
//快速写个栈玩玩。
//C++结构体，类与对象。

typedef int STDataType;
struct MyStack {
	STDataType* array;
	size_t top;//栈顶指针
	size_t capacity;//栈的容量

	//初始化，销毁，入栈，出栈
	void Init() {
		array = nullptr;
		top = 0;
		capacity = 0;
	}

	void push(STDataType x) {
		if (capacity == top) {
			size_t newCapacity = array == nullptr ? 4 : 2 * capacity;
			STDataType* tmp = (STDataType*)malloc(newCapacity * sizeof(STDataType));
			if (tmp == nullptr) {
				std::cout << "Memory allocation failed" << std::endl;
				return;
			}
			else {
				array = tmp;
				capacity = newCapacity;
			}
		}
		if(array!=nullptr)
		   array[top++] = x;
	}

	void pop() {
		if (capacity == 0) {
			std::cout << " Stack is Empty!" << std::endl;
		}
		top--;
	}

	void destroy() {
		if (array == nullptr)
		{
			free(array);
			array = nullptr;
			top = 0;
			capacity = 0;
		}
	}
	STDataType getTop() {
		return array[top - 1];
	}
};


int main(void) {
	MyStack s;
	s.Init();
	s.push(0);
	s.push(1);
	std::cout << s.getTop() << std::endl;
	return 0;
}