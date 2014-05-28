QuickSort implementation in java.

(More or less expected) features:
- Uses random partition-indices.
- Uses insertion-sort when nearly sorted.
- More or less in-place swapping.

Room for improvement/tuning:
- tail-recursion.
- Instead of random partitioning, base it on the insertion-sort switch-over factor.
- Do a better job of collecting dupe values together next to the pivot.

see http://www.sorting-algorithms.com/quick-sort-3-way
see http://en.wikipedia.org/wiki/Quicksort
see http://stackoverflow.com/questions/12454866/how-to-optimize-quicksort
