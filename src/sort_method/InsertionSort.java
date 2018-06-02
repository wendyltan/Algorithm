package sort_method;

public class InsertionSort {
    //直接插入排序(比较一个后移一个）
    public static void insertSort(int a[]){
        int j;       //当前要插入值的位置
        int preJ;     //依次指向j前的位置
        int key;       //后移时来暂存要插入的值

        //从数组的第二个位置开始遍历值
        for(j=1;j<a.length;j++){
            key=a[j];
            preJ=j-1;
            //a[preJ]比当前值大时，a[preJ]后移一位
            while(preJ>=0 && a[preJ]>key){
                a[preJ+1]=a[preJ]; //将a[preJ]值后移

                //这里注意:  a[preJ+1]=a[j]=key,把插入值已经存在了 key中
                //等于说, 留出来一个空白位置来实现依次后移（不会造成数据丢失问题）

                preJ--;         //preJ前移
            }
            //找到要插入的位置或已遍历完成（(preJ=0）
            a[preJ+1]=key;    //将当前值插入 空白位置
        }
    }
    //折半插入排序（先找到位置，然后一起移动）
    private static void binaryInsertSort(int[] a)
    {
        //从数组的第二个位置开始遍历值
        for(int i = 1; i < a.length; i++)  {
            int key = a[i];           //暂存要插入的值
            int pre = 0;              //有序序列开始和结尾下标申明
            int last = i - 1;
            // 折半查找出插入位置 a[pre]
            while(pre <= last)  {
                int mid = (pre + last) / 2;
                if(key < a[mid])  {
                    last = mid - 1;
                } else {
                    pre = mid + 1;
                }
            }
            //a[i]已经取出来存放在key中，把下标从pre + 1到 i-1的元素依次后移
            for(int j = i; j >= pre + 1; j--)  {
                a[j] = a[j - 1];
            }
            //把值插入空白位置
            a[pre] = key;
        }
    }

}
