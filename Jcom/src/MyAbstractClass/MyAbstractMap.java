package MyAbstractClass;
import MyInterface.IMap;

public abstract class MyAbstractMap<K,V> implements IMap<K,V> {
    public int size() {return entrySet().size();}
    public boolean isEmpty(){return this.size() == 0;}


}
