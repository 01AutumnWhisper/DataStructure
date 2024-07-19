package MyList;

import java.util.Arrays;

/**
 * @author 秋落风声
 * @description:Java版本的顺序表，以此来熟悉数组列表的使用。
 */
public class MyArrayList {
    public int[] MyArray;
    public int size;

    private static final int DEFAULT_CAPACITY=10;

    private static final int[] DEFAULT_EMPTY_CAPACITY={};

    public MyArrayList(){   MyArray = DEFAULT_EMPTY_CAPACITY ; }
    public MyArrayList(int initCapacity)
    {
        if(initCapacity<0)
            initCapacity = DEFAULT_CAPACITY;
        MyArray = new int[initCapacity];
    }

    private boolean isFull(){return this.size==MyArray.length;}
    private void grow()
    {
        MyArray = Arrays.copyOf(MyArray,2*MyArray.length);
    }
    public boolean add(int val)
    {
        if(isFull())
            grow();
        MyArray[size++]=val;
    }
    public boolean add(int pos,int val)
    {
           try{
               check();
               MyArray[pos]=val;
               return true;
           }catch(PosIllegal e)
           {
               e.printStackTrace();
               retur false;
           }

    }
}
/*
public class MyArrayList implements IList {
    public int[] array;
    public int usedSize;

    //静态常量字段。
    public static final int DEFAULT_CAPACITY = 10;
    public MyArrayList(int initCapacity){
        if(initCapacity<0){
            initCapacity = DEFAULT_CAPACITY;
        }
            this.array = new int[initCapacity];
            this.usedSize = 0;

    }
    public MyArrayList(){
        this.array = new int[0];
    }
    private int[] grow(){
        int capacity  = array.length == 0?DEFAULT_CAPACITY:2*this.array.length;
        int[] newArray = new int[capacity];
        for (int i = 0; i < array.length; i++) {
            newArray[i]=this.array[i];
        }
        return newArray;
    }
    @Override
    public boolean isFull(){
        return array.length==this.usedSize;
    }

    @Override
    public void add(int pos, int data) {
        try{
            check(pos);
            array[pos]=data;
        }catch(PosIllegal e){
            System.out.println("index of pos illegal!");
            e.printStackTrace();
        }
    }
    @Override
    public void add(int data)
    {
        if(isFull()){
            grow();
        }
        this.array[this.usedSize++]=data;
    }
    public boolean isEmpty()
    {
        return this.usedSize==0;
    }
    public void remove(){
       if(isEmpty())
           throw new EmptyException();
       else
       {
           this.usedSize--;
       }
    }
    public void remove(int toRemove){
        int pos = indexOf(toRemove);
        for (int i = pos; i <usedSize-1; i++) {
            array[i]=array[i+1];
        }
    }
    public boolean contains(int toFind){
        //顺序遍历
        for (int i = 0; i < usedSize; i++) {
            if(array[i]==toFind)
                return true;
        }
        return false;
    }
    private void check(int pos){
        if(pos<0&&pos>usedSize)
            throw new PosIllegal("index of pos illegal!");
    }
    public int get(int pos){
        try{
            check(pos);
            return array[pos];
        }catch(PosIllegal e)
        {
            System.out.println("index of pos illegal!");
            e.printStackTrace();
        }
        return -1;//返回不合理的下标。
    }

    public void set(int pos,int value){
        try{
            check(pos);
            array[pos]=value;
        }catch(PosIllegal e)
        {
            System.out.println("index of pos illegal!");
            e.printStackTrace();
        }
    }

    public int size(){
        return this.usedSize;
    }
    public int indexOf(int toFind){
        //顺序遍历
        for (int i = 0; i < usedSize; i++) {
            if(array[i]==toFind)
                return i;
        }
        return -1;
    }

    public void clear()
    {
        for (int i = 0; i < usedSize; i++) {
            array[i]=0;
        }
    }
    public void display()
    {
        for (int i = 0; i < usedSize; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
*/
