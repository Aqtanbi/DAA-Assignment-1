import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //calling MergeSort algorithm for checking
        int[] array = {3, 3, 1, 3, 2};
        MergeSorting sorter = new MergeSorting(array.length);
        sorter.sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
