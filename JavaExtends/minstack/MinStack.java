package JavaExtends.minstack;
import java.util.Stack;
public class MinStack<E extends Comparable<E>> {
    private Stack<E> stack;
    private Stack<E> minstack;

    public MinStack() {
        stack = new Stack<>();
        minstack = new Stack<>();
    }
    public void push(E elements) {
        stack.push(elements);
        //最小栈为空,或者入栈值小于最小栈的val
        if(minstack.isEmpty()||elements.compareTo(minstack.peek())<=0){
            minstack.push(elements);
        }
    }

    public void pop(){
        if(stack.isEmpty())
            return;
        E popVal = stack.pop();
        if(popVal.equals(minstack.peek())){
            minstack.pop();
        }
    }

    public E top(){
        if(stack.isEmpty())
            return null;
        return stack.peek();
    }

    public E getMin() {
        if(minstack.isEmpty())
            return null;
        return minstack.peek();
    }
}