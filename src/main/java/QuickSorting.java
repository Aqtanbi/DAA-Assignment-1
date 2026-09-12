import java.util.Random;

public class QuickSorting {
    private Random random = new Random();
    public void sort(int[] array, int low, int high) {
        while (low < high) {
            int pivotIndex = partition(array, low, high);

            int leftSize = pivotIndex - low;
            int rightSize = high - pivotIndex;

            if (leftSize < rightSize) {
                sort(array, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                sort(array, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }
    public int partition(int[] array, int low, int high){
        int i = low - 1;
        int pivotIndex = random.nextInt(low, high+1);

        int temp = array[pivotIndex];
        array[pivotIndex] = array[high];
        array[high] = temp;
        int pivot = array[high];
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }

        int t = array[i+1];
        array[i+1] = array[high];
        array[high] = t;
        return i + 1;
    }
}
