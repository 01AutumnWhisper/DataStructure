import java.util.Arrays;

/**
 * @author 90774
 */
public class Heap {
    private int[] elem;
    private int usedSize;
    private static final int DEFAULT_CAPACITY = 10;

    public Heap(int[] element) {
        elem = new int[DEFAULT_CAPACITY];
        this.usedSize = element.length;
        for (int i = 0; i < element.length; i++) {
           elem[i] = element[i];
        }
        for(int i=(element.length-1)/2;i>=0;i--)
        {
            siftDown(i,usedSize);
        }
    }
    private void siftUp(int child)
    {
        int parent = (child-1)/2;
        while(parent>=0&&elem[parent]>elem[child])
        {
            int tmp = elem[parent];
            elem[parent] = elem[child];
            elem[child] = tmp;
            child = parent;
            parent = (child-1)/2;
        }
    }
    private void siftDown(int parent,int usedSize)
    {
        int child = parent*2+1;
        while(child<usedSize)
        {
            if(child+1<usedSize&&elem[child+1]<elem[child])
            {
                child++;
            }
            if(elem[child]<elem[parent])
            {
                int tmp = elem[child];
                elem[child] = elem[parent];
                elem[parent] = tmp;
                parent = child;
                child = parent*2+1;
            }
            else
            {
                break;
            }
        }
    }

    public boolean isFull()
    {
        return elem.length == usedSize;
    }
    public void push(int val)
    {
        if(isFull()){
            elem = Arrays.copyOf(elem,2*elem.length);
        }
        elem[this.usedSize++] = val;
        siftUp(this.usedSize-1);
    }

    public boolean isEmpty(){
        return this.usedSize == 0;
    }
    public int peek()
    {
        if(isEmpty()){
            return Integer.MAX_VALUE;
        }
        else
        {
            return this.elem[0];
        }
    }
    public int poll()
    {
        if(isEmpty())
        {
            return Integer.MAX_VALUE;
        }
        else
        {
            int tmp = elem[0];
            elem[0] = elem[usedSize-1];
            usedSize--;
            siftDown(0,usedSize);
            return tmp;
        }
    }
}
