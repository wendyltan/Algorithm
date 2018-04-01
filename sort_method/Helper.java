public class Helper {
    public static boolean less(int v, int w){
        return v < w;
    }
    public static void exch(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void print(int a[]){
        System.out.print("After sorting: ");
        for(int item : a){
            System.out.print(item+" ");
        }
        System.out.print('\n');
    }

}
