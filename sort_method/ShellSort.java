public class ShellSort {
    public static void main(String[] args) {
        int[] number = {13 ,14 ,94 ,33 ,82 ,25 ,59 ,94 ,65 ,23 ,45 ,27 ,73 ,25 ,39 ,10};
        shellSort(number);
    }
    public static void shellSort(int[] a) {
        int gap = 1, i, j, len = a.length;
        int temp;//插入排序交换值的暂存
        //确定初始步长
        while (gap < len / 3){
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3){//循环遍历步长，最后必为1
            System.out.println("gap:"+gap);
            for (i = gap; i < len; i++) {//每一列依次向前做插入排序
                temp = a[i];
                //每一列中在a[i]上面且比a[i]大的元素依次向下移动
                for (j = i - gap; j >= 0 && a[j] > temp; j -= gap){
                    a[j + gap] = a[j];
                }
                //a[i]填补空白，完成一列中的依次插入排序
                a[j + gap] = temp;
                for(int k = 0; k < a.length; k++) {
                    System.out.print(a[k] + " ");
                }
                System.out.println("");
            }

        }
    }

}
