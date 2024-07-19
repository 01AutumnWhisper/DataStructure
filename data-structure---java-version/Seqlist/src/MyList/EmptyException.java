package MyList;

public class EmptyException extends RuntimeException{
    public EmptyException(){

    }
    public EmptyException(String msg){
        super(msg);
    }
}
