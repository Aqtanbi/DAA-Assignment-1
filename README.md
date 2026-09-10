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
