public class MergeSort {

    private static int[] aux;
    //先归并
    private static void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; // 将数据复制到辅助数组
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (Helper.less(aux[i],a[j])) a[k] = aux[i++]; // 先进行这一步，保证稳定性
            else a[k] = aux[j++];
        }
    }

    //自顶向下排序
    public static void sort(int[] a) {
        aux = new int[a.length];
        sort(a, 0, a.length - 1);
    }
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //前一半排序
        sort(a, lo, mid);
        //后一半排序
        sort(a, mid + 1, hi);
        //归并
        merge(a, lo, mid, hi);
        Helper.print(a);
    }

    //自底向上排序
    public static void busort(int[] a) {
        int N = a.length;
        aux = new int[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }

    }


}
