import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //calling MergeSort algorithm for checking
        int[] arraym = {3, 3, 1, 3, 2};
        MergeSorting Msort = new MergeSorting(arraym.length);
        Msort.sort(arraym, 0, arraym.length - 1);
        System.out.println("MergeSort" + Arrays.toString(arraym));

        int[] arrayq = {5, 6, 1, 0, -10, 200, 1, 3};
        QuickSorting qsort = new QuickSorting();
        qsort.sort(arrayq, 0, arrayq.length - 1);
        System.out.println("QuickSort" + Arrays.toString(arrayq));

    }
}
