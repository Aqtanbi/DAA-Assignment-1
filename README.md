# Assignment 1 — Divide-and-Conquer Algorithm Analysis

## MergeSort

I implemented MergeSort using a divide-and-conquer approach.

### Implementation

The algorithm recursively divides the array into two halves until each subarray contains one element. Then, the sorted subarrays are merged together.

The implementation includes:

* Recursive divide-and-conquer strategy
* Linear-time merge operation
* Reusable auxiliary buffer
* Insertion Sort cutoff for small subarrays
* In-place copying of merged elements back into the original array

For small subarrays, the algorithm uses Insertion Sort instead of continuing the recursion. The cutoff value is set to 16 elements.

### Complexity

The recurrence for MergeSort is:

`T(n) = 2T(n/2) + Θ(n)`

The array is divided into two subproblems of approximately `n/2` elements, while the merge operation takes `Θ(n)` time.

The recursion has `Θ(log n)` levels, and each level performs `Θ(n)` total work.

Therefore:

**Time complexity: Θ(n log n)**

**Auxiliary space: O(n)** due to the reusable buffer.

**Recursion depth: O(log n)**

### Current implementation

The MergeSort implementation was tested on several integer arrays and produced correctly sorted results.
## 2. QuickSort

QuickSort was implemented using randomized pivot selection and in-place partitioning.

For each partition, a random element from the current range is selected as the pivot. The pivot is moved to the end of the range, and the remaining elements are partitioned around it.

The implementation uses the following strategy:

Randomized pivot selection
In-place partitioning
No auxiliary array for partitioning
Recursion into the smaller partition
Iteration over the larger partition using a while loop

Recursing only into the smaller partition helps keep the recursion stack small.

### Complexity:

**Typical time: O(n log n)**

**Worst-case time: O(n²)**

**Typical recursion depth: O(log n)**

Worst-case recursion depth with smaller-partition recursion: O(log n)
Extra space: O(log n) for the recursion stack

The worst case occurs when the pivot repeatedly produces highly unbalanced partitions, for example when one partition contains almost all elements and the other contains almost none.
## 3. Deterministic Select

Deterministic Select is used to find the `k`-th smallest element in an array without sorting the entire array.

The index `k` is zero-based. For example, if the sorted array is:

`[1, 2, 4, 6, 7, 9]`

then:

* `k = 0` returns `1`
* `k = 2` returns `4`
* `k = 5` returns `9`

### Implementation

The algorithm uses the median-of-medians technique to choose a pivot.

The implementation works as follows:

1. The current array range is divided into groups of at most five elements.
2. Each group is sorted using Bubble Sort.
3. The median element of every group is placed into a separate array.
4. The medians array is sorted, and its middle element is selected as the pivot.
5. The original array is partitioned around the pivot.
6. The algorithm compares the pivot position with `k`:
    * If the pivot position equals `k`, the pivot is the answer.
    * If `k` is smaller than the pivot position, the algorithm continues in the left partition.
    * If `k` is greater than the pivot position, the algorithm continues in the right partition.

Only the partition containing the required element is processed recursively. The other partition is ignored.

For ranges containing five or fewer elements, the algorithm sorts the range directly and returns the element at index `k`.

### Partitioning

The partition operation is similar to the partitioning step in QuickSort.

The pivot is first moved to the end of the current range. Then, the algorithm moves all elements smaller than the pivot to the left side.

Finally, the pivot is placed between the smaller and larger elements. The method returns the final index of the pivot.

The partition operation is performed in-place and does not require an additional array.

### Complexity

The theoretical median-of-medians algorithm has the following recurrence:

`T(n) = T(n/5) + T(7n/10) + Θ(n)`

The first recursive term finds the median of the medians. The second term processes only the partition that may contain the required element. The partitioning and grouping operations take linear time.

Therefore, the theoretical worst-case time complexity is:

**Time complexity: Θ(n)**

**Auxiliary space: O(n)** for the medians array used by the current implementation.

**Recursion depth: O(log n)** for the selection process.

### Current implementation

The Deterministic Select implementation was tested by comparing its results with the corresponding elements of a sorted copy of the same array.

The algorithm correctly returns the expected `k`-th smallest element for the tested arrays.

The current implementation uses sorting of the medians array to select the pivot. Therefore, the implementation demonstrates the median-of-medians idea, but the pivot-selection step can be further optimized to achieve the theoretical linear-time bound strictly.