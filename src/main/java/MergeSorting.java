public class MergeSorting {
    private int[] buffer;
    private static final int CUTOFF = 16;

    public MergeSorting(int size) {
        buffer = new int[size];
    }
    private void insertionSort(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = array[i];
            int j = i - 1;
            
            while (j >= start && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int size = end - start + 1;
        if (size <= CUTOFF) {
            insertionSort(array, start, end);
            return;
        }
        int middle = start + (end - start) / 2;
        sort(array, start, middle);
        sort(array, middle + 1, end);
        merge(array, start, end, middle);
    }

    public void merge(int[] array, int start, int end, int middle) {
        int leftIndex = start;
        int rightIndex = middle + 1;
        int bufferIndex = 0;
        while (leftIndex <= middle && rightIndex <= end){
            if (array[leftIndex] <= array[rightIndex]) {
                buffer[bufferIndex] = array[leftIndex];
                bufferIndex++;
                leftIndex++;
            }
            else{
                buffer[bufferIndex] = array[rightIndex];
                bufferIndex++;
                rightIndex++;
            }
        }
        while (leftIndex <= middle) {
            buffer[bufferIndex] = array[leftIndex];
            bufferIndex++;
            leftIndex++;
        }
        while (rightIndex <= end) {
            buffer[bufferIndex] = array[rightIndex];
            bufferIndex++;
            rightIndex++;
        }
        for (int i = 0; i < bufferIndex; i++) {
            array[start + i] = buffer[i];
        }
    }
}
