import java.util.Random;

/** Runs comparisons between algorithms / data structures and prints a results table. */
public class PerformanceAnalyzer {

    private static final String LINE = "=====================================================================";

    public static void runAll() {
        System.out.println(LINE);
        System.out.println("PERFORMANCE COMPARISON");
        System.out.println(LINE);
        System.out.printf("%-16s %-24s %-12s %-12s %s%n", "Operation", "Algorithm", "Size", "Steps", "Time (ns)");
        System.out.println("---------------------------------------------------------------------");

        searchComparison(1_000);
        searchComparison(100_000);
        graphComparison(1_000, 2_000);
        graphComparison(10_000, 20_000);
        insertComparison(5_000);
        stackQueueComparison(100_000);

        System.out.println(LINE);
        System.out.println("Note: step counts are exact; times vary per run/machine (JIT warm-up, OS).");
        System.out.println("Linear O(n) vs Binary O(log n); BFS and DFS are both O(V+E);");
        System.out.println("Array insert-at-front O(n) per op vs Linked List insert-at-head O(1) per op.");
    }

    private static void row(String op, String algo, int size, long steps, long nanos) {
        System.out.printf("%-16s %-24s %-12d %-12d %d%n", op, algo, size, steps, nanos);
        ResultsLog.add(String.format("Performance | %s | %s | n=%d | steps=%d | %d ns", op, algo, size, steps, nanos));
    }

    /** Worst case for linear search: target is the last element. */
    private static void searchComparison(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i * 2; // sorted
        int target = arr[n - 1];
        SearchOperations.linearSearch(arr, target);  // warm-up
        SearchOperations.binarySearch(arr, target);
        SearchOperations.SearchResult lin = SearchOperations.linearSearch(arr, target);
        SearchOperations.SearchResult bin = SearchOperations.binarySearch(arr, target);
        row("Search", "Linear Search", n, lin.steps, lin.nanos);
        row("Search", "Binary Search", n, bin.steps, bin.nanos);
    }

    private static void graphComparison(int v, int extra) {
        Graph g = Graph.randomConnected(v, extra, 42);
        g.bfs("V0");
        g.dfs("V0");
        Graph.TraversalResult b = g.bfs("V0");
        Graph.TraversalResult d = g.dfs("V0");
        row("Graph Traversal", "BFS (" + g.edgeCount() + " edges)", v, b.steps, b.nanos);
        row("Graph Traversal", "DFS (" + g.edgeCount() + " edges)", v, d.steps, d.nanos);
    }

    private static void insertComparison(int n) {
        ArrayOperations arr = new ArrayOperations();
        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) arr.insertAt(0, i);
        long arrTime = System.nanoTime() - t0;

        LinkedListDS list = new LinkedListDS();
        t0 = System.nanoTime();
        for (int i = 0; i < n; i++) list.insertAtHead(i);
        long listTime = System.nanoTime() - t0;

        row("Insert at front", "Array (shifting)", n, arr.getTotalShifts(), arrTime);
        row("Insert at front", "Linked List (head)", n, n, listTime);
    }

    private static void stackQueueComparison(int n) {
        StackDS s = new StackDS();
        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) s.push(i);
        while (!s.isEmpty()) s.pop();
        row("Push+Pop all", "Stack", n, 2L * n, System.nanoTime() - t0);

        QueueDS q = new QueueDS();
        t0 = System.nanoTime();
        for (int i = 0; i < n; i++) q.enqueue(i);
        while (!q.isEmpty()) q.dequeue();
        row("Enq+Deq all", "Queue", n, 2L * n, System.nanoTime() - t0);
    }
}
