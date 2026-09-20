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

### Implementation

The Deterministic Select implementation was tested by comparing its results with the corresponding elements of a sorted copy of the same array.

The algorithm correctly returns the expected `k`-th smallest element for the tested arrays.

The current implementation uses sorting of the medians array to select the pivot. Therefore, the implementation demonstrates the median-of-medians idea, but the pivot-selection step can be further optimized to achieve the theoretical linear-time bound strictly.

## 4. Closest Pair of Points

Closest Pair finds the minimum Euclidean distance between two points in a set, using a divide-and-conquer approach.

### Implementation

The implementation works as follows:

1. The points are sorted by X-coordinate once at the start.
2. The point set is recursively divided into a left half and a right half.
3. For ranges below a fixed size threshold, the algorithm falls back to a brute-force pairwise comparison.
4. The closest pair is found recursively in the left and right halves, and the smaller of the two distances (`d`) is kept.
5. A "strip" of points within distance `d` of the dividing line is built, ordered by Y-coordinate.
6. Each point in the strip is compared only against a limited number of neighboring points (thanks to the Y-ordering), which keeps this step linear in the strip size.
7. The overall minimum distance from the recursive calls and the strip check is returned.

### Complexity

The recurrence for Closest Pair is:

`T(n) = 2T(n/2) + Θ(n)`

The initial sort takes `Θ(n log n)`. The recursive division produces two subproblems of size `n/2`, and the strip-merging step runs in linear time thanks to the bounded number of comparisons per point.

Therefore:

**Time complexity: Θ(n log n)**

**Auxiliary space: O(n)** for the strip array and Y-sorted auxiliary arrays.

**Recursion depth: O(log n)**

### Current implementation

The Closest Pair implementation was tested with randomly generated point sets and a brute-force base case for small inputs. All tests currently pass.

## 5. Experimental Analysis (Experiment.java)

`Experiment.java` runs the four implemented algorithms (Merge Sort, Quick Sort, Deterministic Select, Closest Pair) across varying input sizes and input distributions, and records the results for further analysis.

### What is measured

Each run of `Experiment.java` produces a row in `results.csv` with the following columns:

`Algorithm, InputType, Size, TimeNs, ExtraMetric`

* **Algorithm** — `MergeSort`, `QuickSort`, `DeterministicSelect`, or `ClosestPair`
* **InputType** — `RANDOM`, `SORTED`, or `REVERSE`
* **Size** — number of elements (or points, for Closest Pair)
* **TimeNs** — wall-clock running time in nanoseconds, measured with `System.nanoTime()`
* **ExtraMetric** — an algorithm-specific extra value: `k` for Deterministic Select, `points` (input size) for Closest Pair

### Results

Measurements were collected for input sizes ranging from 100 to 20,000 elements (up to 2,000 points for Closest Pair), across random, sorted, and reverse-sorted inputs.

A few observations from the current data:

* **Merge Sort** scales roughly as expected for `Θ(n log n)` on random input, but shows some non-monotonic jumps between sizes (e.g. a smaller jump between 5,000 and 10,000 than between 1,000 and 5,000), most likely due to JIT warm-up and JVM noise rather than the algorithm itself — single-run measurements without warm-up iterations or averaging are sensitive to this.
* **Quick Sort** is consistently faster than Merge Sort on random input at larger sizes (e.g. ~1.98 ms vs ~2.8 ms at n = 20,000), which fits its lower constant factor and in-place partitioning.
* On **sorted** and **reverse-sorted** input, Quick Sort's randomized pivot selection keeps its running time close to Merge Sort's, with no sign of quadratic blow-up — consistent with randomized pivoting avoiding the classic worst case of naive Quick Sort on already-sorted data.
* **Deterministic Select** is markedly more expensive per element than the sorts (e.g. ~17.7 ms at n = 20,000 vs ~2 ms for the sorts), which matches the larger constant factor of median-of-medians (grouping, per-group Bubble Sort, and recursion on both the medians array and the partition).
* **Closest Pair** grows slower than linearly with the tested sizes here (100 to 2,000 points), consistent with the `Θ(n log n)` bound, though the range tested is too small to draw a strong conclusion.

### Known limitations of the current measurements

* `ExtraMetric` for Merge Sort and Quick Sort is currently a placeholder (`comparisons_not_tracked`) — comparison counting has not yet been wired into `MergeSorting` / `QuickSorting`.
* Maximum recursion depth is not yet present as a CSV column, even though depth counters were added to `MergeSorting`, `QuickSorting`, `DeterministicSelector`, and `ClosestPairSolver` — this still needs to be plugged into `Experiment.java`'s output.
* Each configuration was run once rather than averaged over multiple runs, so timings include some JVM/JIT noise, especially at smaller input sizes.

### Recursion-depth instrumentation

To let `Experiment.java` report maximum recursion depth, lightweight depth counters were added to:

* `MergeSorting`
* `QuickSorting`
* `DeterministicSelector`
* `ClosestPairSolver`

This is especially relevant for Quick Sort, since the assignment specifically asks for the effect of smaller-side recursion on recursion depth to be demonstrated.

### Status

Algorithm implementations, their unit tests, and the `Experiment.java` benchmarking harness are complete, with timing results exported to `results.csv`. Remaining work: wiring comparison counts and recursion depth into the CSV output, and averaging multiple runs per configuration to reduce measurement noise.