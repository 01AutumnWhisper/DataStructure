package sort;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 90774
 */
public class Sort {
    /**
     * 时间复杂度: 0(n^2)
     *      若数组已然有序,则不会进入内部循环,时间复杂度0(n)
     *      这意味着数组越接近有序,那么时间复杂度越接近线性.后续的希尔排序就是插入排序的plus版本,希尔排序的最后一次排序就是插入排序.
     * 空间复杂度:0(1)
     * 本身是稳定的排序
     * @param arr
     */
    public static void insertSort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i-1;
            while(j>=0&&arr[j]>tmp)
            {
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=tmp;
        }
    }
    private static final int getMiddleNum(int[] arr,int left,int right)
    {
        int mid = (left+right)/2;
        if(arr[left]<arr[right])
        {
            if(arr[mid]<arr[left])
            {
                return left;
            }
            else if(arr[mid]<arr[right])
            {
                return mid;
            }
            else
            {
                return right;
            }
        } else//arr[left]>=arr[right]
        {
            if(arr[mid]<arr[right])
            {
                return right;
            }
            else if(arr[mid]<arr[left])
            {
                return mid;
            }
            else
            {
                return left;
            }
        }
    }
    private static final int MAX_LENGTH_INSERT_SORT = 7;
    private static void insertSort(int[] array,int left ,int right)
    {
        for(int i=left+1;i<right;i++)
        {
            int tmp=array[i];
            int j = i-1;
            while(j>=0&&array[j]>=tmp)
            {
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=tmp;
        }
    }
    public static void quick2(int[] array,int left,int right)
    {
        Deque<Integer> stack = new ArrayDeque<>();
        int pivot = partition(array,left,right);
        if(pivot>left+1) {
            stack.push(left);
            stack.push(pivot - 1);
        }
        if(pivot<right-1)
        {
            stack.push(pivot+1);
            stack.push(right);
        }
        while(!stack.isEmpty())
        {
            right = stack.pop();
            left = stack.pop();
            pivot = partition(array,left,right);
            if(pivot>left+1) {
                stack.push(left);
                stack.push(pivot - 1);
            }
            if(pivot<right-1)
            {
                stack.push(pivot+1);
                stack.push(right);
            }
        }
    }
    public static void quickSort(int[] array)
    {
        quick(array,0,array.length-1);
    }
    private static void quick(int[] array,int left,int right) {
        if (left < right) {
            if(right - left + 1<MAX_LENGTH_INSERT_SORT)
            {
                insertSort(array,left,right);
                return ;
            }
            int mid = getMiddleNum(array, left, right);
            swap(array, left, mid);
            int pivot = partition(array, left, right);
            quick(array, left, pivot - 1);
            quick(array, pivot + 1, right);
        }
    }
    private static void swap(int[] array,int i,int j)
    {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    private static int partition(int[] array, int left, int right) {
        //先给左边挖个坑
        int key = array[left];
        //记录最初的坑点
        int i = left;
        while(left<right)
        {
            while(left<right&&array[right]>=key)
            {
                right--;
            }
            //右边挖坑填左边
            array[left]=array[right];
            //array[right]<x
            while(left<right&&array[left]<=key)
            {
                left++;
            }
            //左边挖坑填右边
            array[right]=array[left];
        }
        array[left]=key;
        return left;
    }
}
