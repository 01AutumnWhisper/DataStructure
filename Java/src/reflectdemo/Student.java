package reflectdemo;

/**
 * @author 90774
 */
public class Student {
    private String name = "hello";
    private int age = 99;
    private Student(){
        System.out.println("Student():");
    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "student{"
                +"name:"
                +name
                +",age:"
                +age
                +"}";
    }
}
