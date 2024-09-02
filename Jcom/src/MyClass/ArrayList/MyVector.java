package MyClass.ArrayList;

import MyAbstractClass.MyAbstractList;
import MyInterface.IMyIterator;

import java.util.Arrays;
import java.util.Iterator;

public class MyVector<E> extends MyAbstractList<E> {

    private Object[] element;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyVector() {
        this.element = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    private boolean isFull(){return size== element.length;}
    public boolean add(E element) {
        if(isFull())
            grow();
        this.element[size++] = element;
        return true;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);
        if(index<0)
            return false;
        for(int i = index;i<size-1;i++) {
            this.element[i]=this.element[i+1];
        }
        size--;
        return true;
    }

    public void add(int index, E element) {
        checkIndex(index);
        int i = 0;
        for(i = size;i>=index;i--) {
            this.element[i]=this.element[i-1];
        }
        this.element[i]=element;
        size++;
    }

    public E get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E tmp = elementAt(index);
        this.element[index]=element;
        return tmp;
    }

    public E remove(int index) {
        checkIndex(index);
        E oldValue = elementAt(index);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(element, index + 1, element, index, numMoved);
        element[--size] = null;
        return oldValue;
    }

    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++)
                if (this.element[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (element.equals(this.element[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = size-1; i >= 0; i--) {
            if(this.element[i]==element)
                return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }
    public void clear() {
        for (int i = 0; i < size; i++)
            element[i] = null;
        size = 0;
    }


    private void grow() {
        Arrays.copyOf(element,size<5?DEFAULT_CAPACITY:2*size);
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    @SuppressWarnings("unchecked")
    private E elementAt(int index) {
        return (E) element[index];
    }

    @Override
    public IMyIterator<E> iterator() { return null;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(element[i] + " ");
        }
        System.out.println();
    }
}

