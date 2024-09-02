package MyInterface;

public interface IMyIterator<E> {
    boolean hasNext();

    E next();

    void remove();
}
