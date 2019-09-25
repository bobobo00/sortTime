package TestSort;


import com.sun.deploy.util.SyncAccess;

import java.util.*;

/**
 * @ClassName Lab
 * @Description TODO
 * @Auther danni
 * @Date 2019/9/25 11:06]
 * @Version 1.0
 **/

public class Lab {
    private  static SortMethod[] methods={new InsertSortMethod(),new SelectSortMethod(),new HeapSortMethod(),
            new BubbleSortMethod(), new ShellSortMethod()};

    public static  int[] buildArray(int n){
        Random random=new Random(2019-9-25);
        int[] array=new int[n];
        for (int i = 0; i <n ; i++) {
            array[i]=random.nextInt(n);
        }
        return array;
    }
    //构建升序有序数组
    public static int[] builedSortedUp(int n){
        Random random=new Random(2019-9-25);
        int[] arr=new int[n];
        for (int i = 0; i <n ; i++) {
           arr[i]=random.nextInt(n);
        }
        Arrays.sort(arr);
        return arr;
    }
    //构建降序有序数组
    public static int[] buildSortedDown(int n){
        Random random=new Random(2019-9-25);
        int[] array=new int[n];
        for (int i = 0; i <n ; i++) {
            array[i]=random.nextInt(n);
        }
        int gap=array.length;
        while(true){
            gap=(gap/3)+1;
            for (int i = gap; i<array.length ; i++) {
               int j;
               int key=array[i];
               for(j=i-gap;j>=0&&key>array[j];j-=gap){
                   array[j+gap]=array[j];
               }
                array[j+gap]=key;
            }
            if(gap==1){
                break;
            }
        }
        return array;
    }
   public static int[] buildEquals(int n){
        return new int[n];
    }
    public static double TestRuntime(SortMethod sm,int[] arr){
        long begin=System.nanoTime();
        sm.sort(arr);
        long end=System.nanoTime();
        double ms=(end-begin)*1.0/1000000;
        return ms;
    }
    public static void menue(List<Data> list){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要查看数组类型（随机，升序，降序，全部）");
        String str=scanner.next();
        System.out.println("排序方法   耗费时间(毫秒)   数组长度   数组初识状态");
        System.out.println("_______________________________________________");
        switch (str){
            case "随机":
                for (int i = 0; i <list.size() ; i++) {
                    Data temp=list.get(i);
                    if(temp.arraytype=="随机"){
                        System.out.println(temp);
                    }
                }
                break;
            case "升序":

                for (int i = 0; i <list.size() ; i++) {
                    Data temp=list.get(i);
                    if(temp.arraytype=="升序"){
                        System.out.println(temp);
                    }
                }
                break;
            case "降序":
                for (int i = 0; i <list.size() ; i++) {
                    Data temp=list.get(i);
                    if(temp.arraytype=="降序"){
                        System.out.println(temp);
                    }
                }
                break;
            case "全部":
                for (int i = 0; i <list.size() ; i++) {
                    Data temp=list.get(i);
                    System.out.println(temp);
                }
                break;
             default:break;
        }
    }
    public static List<Data> test(){
        List<Data> list=new ArrayList<>();
        for (int i = 1; i <5 ; i++) {
            int n=i*5000;
            int[] array=buildArray(n);
            int[] sortedUp=builedSortedUp(n);
            int[] sortedDown=buildSortedDown(n);
            int[] equals=buildEquals(n);

            for(SortMethod method:methods){
                int[] arr=array.clone();
                list.add(new Data(method.getNmae(),TestRuntime(method,arr),n,"随机"));
            }
            for(SortMethod method:methods){
                int[] arr=sortedUp.clone();
                list.add(new Data(method.getNmae(),TestRuntime(method,arr),n,"升序"));
            }
            for(SortMethod method:methods){
                int[] arr=sortedDown.clone();
                list.add(new Data(method.getNmae(),TestRuntime(method,arr),n,"降序"));
            }
        }
        return list;
    }
    public static void main(String[] args) {
        List<Data> result=test();
        menue(result);
    }
}
class Data {
    String sortname;
    double sorttime;
    int arraysize;
    String arraytype;

    public Data(String sortname, double sorttime, int arraysize, String arraytype) {
        this.sortname = sortname;
        this.sorttime = sorttime;
        this.arraysize = arraysize;
        this.arraytype = arraytype;
    }
    public Data() { }

    @Override
    public String toString() {
        return String.format("%s      %.5f       %d        %s",sortname,sorttime,arraysize,arraytype);
    }
}
interface method{
    String getNmae();
    void sort(int[] arr);
}
class SortMethod implements method {

    @Override
    public String getNmae() {
        return null;
    }

    @Override
    public void sort(int[] arr) {

    }
}
class InsertSortMethod  extends SortMethod {

    @Override
    public String getNmae() {
        return "插入排序";
    }

    @Override
    public void sort(int[] arr) {
        TestSort.insertSort(arr);
    }
}
class ShellSortMethod extends SortMethod{

    @Override
    public String getNmae() {
        return "希尔排序";
    }

    @Override
    public void sort(int[] arr) {
        TestSort.shellSort(arr);

    }
}
class BubbleSortMethod extends SortMethod{

    @Override
    public String getNmae() {
        return "冒泡排序";
    }

    @Override
    public void sort(int[] arr) {
        TestSort.bubbleSort(arr);
    }
}
class SelectSortMethod extends SortMethod{

    @Override
    public String getNmae() {
        return "选择排序";
    }

    @Override
    public void sort(int[] arr) {
        TestSort.selectSort(arr);
    }
}
class HeapSortMethod extends SortMethod{

    @Override
    public String getNmae() {
        return "堆排序  ";
    }

    @Override
    public void sort(int[] arr) {
        TestSort.heapSort(arr);
    }
}


