package reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 90774
 */
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException {
       Class c = Student.class;
       Constructor con = c.getDeclaredConstructor();
       try{
           Student stu = (Student)con.newInstance();
           System.out.println(stu);
       }catch(InstantiationException e)
       {
           throw new InstantiationException();
       }catch(IllegalAccessException e)
       {
           throw new IllegalAccessException();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }
    }
}
