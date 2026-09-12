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