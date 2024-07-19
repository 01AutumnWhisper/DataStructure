import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    public static double div(int a,int b) throws ArithmeticException{
        if(b == 0){
           /* throw new ArithmeticException();*/
            throw new ArithmeticException("b == 0");
        }
        return (double)a/b;
    }
    public void read(String filename)
    {
        try
        {
            var in = new FileInputStream(filename);
            int b;
            while((b = in.read())!=-1)
            {
                //process input
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Test.div(10,0);
    }
}
