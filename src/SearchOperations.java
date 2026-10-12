public class SearchOperations {

    public static class SearchResult {
        public final String algorithm;
        public final boolean found;
        public final int index;
        public final long steps;
        public final long nanos;

        SearchResult(String algorithm, boolean found, int index, long steps, long nanos) {
            this.algorithm = algorithm;
            this.found = found;
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }

        @Override
        public String toString() {
            return String.format("%s: %s, steps=%d, time=%d ns", algorithm,
                    found ? "found at index " + index : "not found", steps, nanos);
        }
    }

    /** O(n) - checks every element until found. */
    public static SearchResult linearSearch(int[] arr, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            steps++;
            if (arr[i] == target) { idx = i; break; }
        }
        return new SearchResult("Linear Search", idx >= 0, idx, steps, System.nanoTime() - start);
    }

    /** O(log n) - array MUST be sorted ascending. */
    public static SearchResult binarySearch(int[] arr, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int low = 0, high = arr.length - 1, idx = -1;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) { idx = mid; break; }
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return new SearchResult("Binary Search", idx >= 0, idx, steps, System.nanoTime() - start);
    }
}
