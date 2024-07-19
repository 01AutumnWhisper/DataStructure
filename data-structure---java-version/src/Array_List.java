import java.util.Arrays;

public class Array_List implements IList {
    private static final int DEFAULT_CAPACITY=10;
    private int[] array;
    private int usedSize;
    private int capaCity;

    Array_List(){
        this.array = new int[DEFAULT_CAPACITY];
        this.usedSize = 0;
        this.capaCity=DEFAULT_CAPACITY;

    }
    @Override
    public void add(int data)
    {
        if(isFull()){
            grow();
        }
        this.array[this.usedSize++]=data;
    }
    @Override
    public void add(int pos,int data)
    {
       /* try {
            if (isFull()) {
                grow();
            }
            for (int i = this.usedSize - 1; i >= pos; i++) {
                this.array[i + 1] = this.array[i];
            }
            this.array[pos] = data;
        }catch()*/
    }

    private void checkPos(int pos) throws PosIllegal
    {
        if(pos<0||pos>this.usedSize)
        {
            new PosIllegal("插入位置不合法！");
        }
    }
    @Override
    public boolean contains(int toFind) {
       if(indexOf(toFind)==-1)
           return false;
       else
           return true;
    }

    // 获取顺序表长度
    @Override
    public int size(){
        return this.usedSize;
    }
    public boolean isFull(){ return this.usedSize==this.capaCity; }
    public boolean isEmpty(){ return this.usedSize==0;}
    private void grow()
    {
        this.array = Arrays.copyOf(array,2*this.capaCity);
    }
    // 查找某个元素对应的位置
    public int indexOf(int toFind)
    {
        for (int i = 0; i < array.length; i++) {
            if(array[i]==toFind)
                return i;
        }
        return -1;
    }
    // 获取 pos 位置的元素
    public int get(int pos)
    {
        return array[pos];
    }
    // 给 pos 位置的元素设为 value
    public void set(int pos, int value)
    {
        this.array[pos]=value;
    }
    //删除第一次出现的关键字key
    public void remove(int toRemove)
    {
        int index = this.indexOf(toRemove);
        if(index!=-1)
        {
            for(int j = index;j<array.length;j++)
            {
                this.array[j]=this.array[j+1];
            }
        }
    }
    public void clear()
    {

    }

    public void display(){
        System.out.print("[");
        for (int i = 0; i < this.usedSize-1; i++) {
            System.out.print(this.array[i]+", ");
        }
        System.out.println(this.array[this.usedSize-1]+"]");
    }
}
