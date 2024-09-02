package MyClass.ArrayList;

import MyAbstractClass.MyAbstractList;

public class MyStack<E> extends MyVector<E> {
    private int size;
    private Object[] element;
    private static final int DEFAULT_CAPACITY = 100;
    public MyStack(int initCapacity)
    {
        if(initCapacity<0)
        {
            initCapacity = DEFAULT_CAPACITY;
        }
        element = new Object[DEFAULT_CAPACITY];
    }
    public MyStack(){
        size = 0;
        element = new Object[DEFAULT_CAPACITY];
    }
    public void push(E element) {this.element[this.size++]=element;}
    public E pop(){return (E)this.element[--this.size];}
    public E peek(){return (E)this.element[this.size-1];}
}
