import sort.Sort;

import java.util.*;

import static sort.Sort.quickSort;

/**
 * @author 90774
 */
public class Test
{

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> it = list.iterator();
        while(it.hasNext())
        {
            System.out.println(it.next());
        }
//        for(Integer x:list)
//        {
//            System.out.print(x+" ");
//        }
//        System.out.println("修改前:"+ list);
//        List<Integer> subList = list.subList(1,3);//获取[1,3)区间的子列表.
//        subList.set(1,666);
//        System.out.println("修改后:"+ list);
    }
    public static void main７(String[] args) {
        int[] array = {8,3,22,7,10,30,16,9,17,28,32};
        System.out.println("打印前:" + Arrays.toString(array));
        quickSort(array);
        System.out.println("打印后:" + Arrays.toString(array));

    }
    public static void main5(String[] args) {
        Map<String,Integer> mp = new TreeMap<>();
        mp.put("C",0);
        mp.put("C++",1);
        mp.put("Java",2);
        mp.put("C#",3);
        mp.put("JavaScript",4);
        mp.put("Python",5);

        int val = mp.get("C++");
        System.out.println(val);

        //会抛空指针异常.---键值对不存在
        //int val2 = mp.get("Shell");
        //getOrDefault,如果不确定对象是否存在返回默认值更安全.
        int val2 = mp.getOrDefault("Shell",-1);
        //或者,调用Contains方法判断一下.
        if(mp.containsKey("C++")) {
            System.out.println(mp.get("Java"));
        }

        //移除
        mp.remove("C++");
        System.out.println(mp.containsKey("C++"));
        //返回Key的集合
        mp.put("C++",0);
        Set<String> set= mp.keySet();
        System.out.println(set);
        //返回value的集合
        //由于value允许重复,用更大的Collection接口
        Collection<Integer> col = mp.values();
        System.out.println(col);
        Set<Map.Entry<String,Integer>> entry = mp.entrySet();
        System.out.println(entry);
        //通过迭代器遍历
        for(var e:entry)
        {
            System.out.println("key:"+e.getKey()+ " value:"+e.getValue());
        }
    }
    public static void main4(String[] args) {
        int[] arr = {110,100,0};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void main3(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        var stack = new Stack<Integer>();
        for(int x:list) {
            stack.push(x);
        }
        while(!stack.isEmpty())
        {
            System.out.println(stack.pop()+" ");
        }
    }
    public static void main1(String[] args) {
        BST bst = new BST();
        bst.insert(0);
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(-6);
        bst.insert(-3);
        bst.insert(-8);
        bst.remove(-6);
    }
//        BST root = BST.createBST();
//        BST.TreeNode node = root.searchByLoop(0);
//        System.out.println(node);
//        if(null!=node)
//            System.out.println(node.val);
//    }
}