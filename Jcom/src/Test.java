import MyClass.ArrayList.MyArrayList;
import MyClass.ArrayList.MyHashMap;
import MyClass.ArrayList.MyLinkedList;
import MyInterface.IMap;
import MyInterface.IMyDeque;
import MyInterface.IMyList;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        String[] lan = {"C","C++","Ruby","Java","JavaScript",
        "Python","Object-C","Go","Rust","SQL","HTML","Vue","CSS",
        "Scratch","XML","C#"};
        TreeSet<String> set = new TreeSet<>();
        for(String s:lan)
        {
            set.add(s);
        }
        Iterator it = set.iterator();
        while(it.hasNext())
        {
            System.out.println(it.next());
        }
    }
    public static void main3(String[] args) {
        MyHashMap<String,Integer> map = new MyHashMap<>();
        map.put("XiaoMing",80);
        map.put("LiHua",80);
        System.out.println(map.getNode("LiHua").toString());
        Set<String> setKey = map.keySet();


    }
    public static void main2(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What's your name");
        String s = scan.next();//读取单个单词
        System.out.printf("Hello %s",s);
    }
    public static void main1(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();

            list.add("Apple");
            list.add("banana");
            list.push("nihao");
        System.out.println(list.size());
        System.out.println(list.pop());
        System.out.println(list.size());
        list.print(); // Expected Output: Apple
    }
}
