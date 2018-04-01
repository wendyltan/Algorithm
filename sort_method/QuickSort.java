import java.util.Random;

public class QuickSort {
    public static void sort(int[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
        Helper.print(a);
    }

    //打乱数组元素
    private static void shuffle(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }

    }

    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
    //切分
    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = a[lo];
        while (true) {
            //找到第一个大于等于它的元素（左向右扫描
            while (Helper.less(a[++i], v)) if (i == hi) break;
            //找到第一个小于等于它的元素（右向左扫描
            while (Helper.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            //交换两个的位置
            Helper.exch(a, i, j);
        }
        //当两个指针相遇时，将切分元素 a[lo] 和 a[j] 交换位置。
        Helper.exch(a, lo, j);
        return j;
    }

    //使用三向切分的排序
    public static void sortThree(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        int  v = a[lo];
        while (i <= gt) {
            if (Helper.less(a[i],v)) Helper.exch(a, lt++, i++);
            else if (!Helper.less(a[i],v)) Helper.exch(a, i, gt--);
            else i++;
        }
        sortThree(a, lo, lt - 1);
        sortThree(a, gt + 1, hi);
    }


}
