package sort_method;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int [] arr = {8,1,0,4,6,2,7,9,5,3};
        quickSort(arr,0,arr.length-1);
        for(int i :arr){
            System.out.print(i+" ");
        }
    }
    public static void quickSort(int[]arr,int low,int high){
        if (low < high) {
            int middle = getMiddle(arr, low, high);
            quickSort(arr, low, middle - 1);
            quickSort(arr, middle + 1, high);
        }
    }
    public static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];
        while (low < high) {
            /**
             * 从high开始向前扫描到第一个比tmp小的值与tmp交换。
             * 从low向后扫描第一比tmp大的值与tmp交换。
             * 重复①②过程只到，high=low完成一次快速排序，然后递归子序列。
             */
            while (low < high && list[high] >= tmp) {
                high--;
            }
            list[low] = list[high];
            while (low < high && list[low] <= tmp) {
                low++;
            }
            list[high] = list[low];
        }
        list[low] = tmp;
        return low;
    }


}
