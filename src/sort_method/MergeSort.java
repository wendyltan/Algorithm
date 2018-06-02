package sort_method;

public class MergeSort {
    public static void main(String[] args) {
        int [] arr ={6,5,3,1,8,7,2,4};
        merge_sort(arr);
        for(int i : arr){
            System.out.print(i+ " ");
        }
    }
    public static void merge_sort(int[] arr) {
        int len = arr.length;
        //用于合并的临时数组
        int[] result = new int[len];
        int block, start;

        //两两合并后块大小变大两倍 (注意最后一次block等于len)
        for(block = 1; block <=len ; block *= 2) {
            //把整个数组分成很多个块，每次合并处理两个块
            for(start = 0; start <len; start += 2 * block) {
                int low = start;
                int mid = (start + block) < len ? (start + block) : len;
                int high = (start + 2 * block) < len ? (start + 2 * block) : len;
                //两个块的起始下标及结束下标
                int start1 = low, end1 = mid;
                int start2 = mid, end2 = high;
                //开始对两个block进行归并排序
                while (start1 < end1 && start2 < end2) {
                    result[low++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
                }
                while(start1 < end1) {
                    result[low++] = arr[start1++];
                }
                while(start2 < end2) {
                    result[low++] = arr[start2++];
                }
            }
            //每次归并后把结果result存入arr中，以便进行下次归并
            int[] temp = arr;
            arr = result;
            result = temp;
        }
    }



}
