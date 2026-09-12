public class DeterministicSelector {
    public int select(int[] array, int k){
        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("Wrong k-value!!!");
        }
        return select(array, 0, array.length-1, k);
    }
    private int select(int[] array, int low, int high, int k){
        if (high-low+1 <= 5) {
            BubbleSort(array, low, high);
            return array[k];
        }
        int size = high-low+1;
        int groups = (size+4)/5;
        int[] medians = new int[groups];
        for (int i = 0; i < medians.length; i++) {
            int groupStart = low + i * 5;
            int groupEnd = Math.min(groupStart + 4, high);
            BubbleSort(array, groupStart, groupEnd);
            int medianIndex  = groupStart + (groupEnd-groupStart)/2;
            medians[i] = array[medianIndex];
        }
        int pivot = findMedianOfMedians(medians);
        int pivotIndex = partition(array, low, high, pivot);
        if (pivotIndex == k) {
            return array[pivotIndex];
        } else if (k < pivotIndex) {
            return select(array, low, pivotIndex - 1, k);
        } else {
            return select(array, pivotIndex + 1, high, k);
        }
    }

    private int findMedianOfMedians(int[] medians){
        BubbleSort(medians, 0, medians.length-1);
        return medians[medians.length/2];
    }
// [1, 5, 2, 4, 5, 7]
    public int partition(int[] array, int low, int high, int pivot){
        int p;
        for (int j = low; j <= high; j++) {
            if (array[j] == pivot) {
                int temp = array[high];
                array[high] = array[j];
                array[j] = temp;
                break;
            }}
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;
        return i+1;
    }

    public void BubbleSort(int[] array, int low, int high){
        for (int i = 0; i < high-low ; i++) {
            for (int j = low; j < high-i; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
