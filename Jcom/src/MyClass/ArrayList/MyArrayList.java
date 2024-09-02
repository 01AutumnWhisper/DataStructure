package MyClass.ArrayList;

import MyAbstractClass.MyAbstractList;
import MyInterface.IMyIterator;
import MyInterface.IMyListIterator;

import javax.management.RuntimeErrorException;
import java.util.NoSuchElementException;

public class MyArrayList<E> extends MyAbstractList<E> {
    private Object[] element;
    private int size = 0;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.element = (E[])new Object[capacity];
    }

    @Override
    public int size() {
        return this.size;
    }
    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size; ++i) {
            if (this.element[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean add(E element) {
        if (isFull()) {
            grow();
        }
        this.element[size++] = element;
        return true;
    }

    private boolean isFull() {
        return this.size == element.length;
    }

    private void grow() {
        Object[] newElement = new Object[element.length * 2];
        for (int i = 0; i < element.length; ++i) {
            newElement[i] = this.element[i];
        }
        this.element = newElement;
    }

    @Override
    public boolean remove(E element) throws RuntimeErrorException
    {
        if (isEmpty()) {
            System.out.println("List is empty, can't remove element.");
            return false;
        }
        return true;
    }
    @Override
    public void clear()
    {
        this.size = 0;
    }

    @Override
    public E get(int index){
        try{
            check(index);
            return (E)this.element[index];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound: "+index);
        }
        return null;
    }

    private void check(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException
    {
        try {
            check(index);
            E tmp = (E) this.element[index];
            return tmp;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound: " + index);
        }
        return null;
    }

    @Override
    public void add(int index, E element)
    {
        try{
            check1(index);
            if(isFull())
            {
                grow();
            }
            for(int i = this.size; i > index; --i)
            {
                this.element[i] = this.element[i-1];
            }
            this.element[index] = element;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound: " + index);
        }
    }

    private void check1(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E remove(int index) {
        try {
            check(index);
            if (isEmpty()) {
                System.out.println("List is empty, can't remove element.");
                return null;
            }
            E tmp = (E) this.element[index];
            for (int i = index; i < this.size - 1; i++) {
                this.element[i] = this.element[i + 1];
            }
            this.size--;
            return tmp;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound: " + index);
        }
        return null;
    }

    @Override
    public int indexOf(E element)
    {
        for (int i = 0; i < this.size; ++i) {
            if (this.element[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(E element) {
        for (int i = this.size - 1; i >= 0; --i) {
            if (this.element[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void print()
    {
        System.out.println(this.toString());
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        if (isEmpty()) {
        } else {
            for (int i = 0; i < this.size - 1; ++i) {
                sb.append(this.element[i] + ", ");
            }
        }
        sb.append(element[this.size - 1]);
        sb.append("]");
        return sb.toString();
    }

    //迭代器
    public IMyIterator<E> iterator()
    {
        return new Itr();
    }

    private class Itr implements IMyListIterator<E> {
        private int cursor;
        private int lastRet = -1;//这个下标来确保remove删除上一次调用next方法的元素.
        public Itr() {
        }
        @Override
        public boolean hasNext()
        {
            return this.cursor < size;
        }
        @Override
        public E next()
        {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            E e = (E)element[i];
            cursor = i + 1;
            lastRet = i;
            return e;
        }

        @Override
        //消除上次调用next()的元素
        public void remove()
        {
            if(lastRet<0)
            {
                throw new IllegalStateException();
            }
            int i = cursor - 1;
            while(i<size){
                element[i] = element[i + 1];
                i++;
            }
            size--;
            cursor--;
            lastRet--;
        }
    }

}
