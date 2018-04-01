public class Insertion {
    public static void sort(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && Helper.less(a[j], a[j - 1]); j--) {
                Helper.exch(a, j, j - 1);
            }
        }
        Helper.print(a);
    }
}
