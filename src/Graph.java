import java.util.*;
public class Graph {

    public static class TraversalResult {
        public final String algorithm;
        public final List<String> order;
        public final long steps;   // vertices visited + edges examined
        public final long nanos;

        TraversalResult(String algorithm, List<String> order, long steps, long nanos) {
            this.algorithm = algorithm;
            this.order = order;
            this.steps = steps;
            this.nanos = nanos;
        }

        @Override
        public String toString() {
            return algorithm + " order: " + String.join(" -> ", order)
                    + " | steps=" + steps + ", time=" + nanos + " ns";
        }
    }

    private final Map<String, List<String>> adjacency = new LinkedHashMap<>();

    public boolean addVertex(String v) {
        if (adjacency.containsKey(v)) return false;
        adjacency.put(v, new ArrayList<>());
        return true;
    }

    /** Adds an undirected edge. Returns an error message or null on success. */
    public String addEdge(String a, String b) {
        if (!adjacency.containsKey(a)) return "Vertex '" + a + "' does not exist.";
        if (!adjacency.containsKey(b)) return "Vertex '" + b + "' does not exist.";
        if (a.equals(b)) return "Self-loops are not allowed.";
        if (adjacency.get(a).contains(b)) return "Edge " + a + " - " + b + " already exists.";
        adjacency.get(a).add(b);
        adjacency.get(b).add(a);
        return null;
    }

    public boolean hasVertex(String v) { return adjacency.containsKey(v); }
    public boolean isEmpty() { return adjacency.isEmpty(); }
    public int vertexCount() { return adjacency.size(); }
    public int edgeCount() {
        int total = 0;
        for (List<String> l : adjacency.values()) total += l.size();
        return total / 2;
    }
    public String firstVertex() { return adjacency.keySet().iterator().next(); }

    public void clear() { adjacency.clear(); }

    public void display() {
        if (adjacency.isEmpty()) { System.out.println("Graph is empty."); return; }
        System.out.println("Adjacency list (" + vertexCount() + " vertices, " + edgeCount() + " edges):");
        for (Map.Entry<String, List<String>> e : adjacency.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
    }

    /** BFS using a queue - O(V + E). */
    public TraversalResult bfs(String start) {
        long t0 = System.nanoTime();
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        long steps = 0;
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            String v = queue.poll();
            order.add(v);
            steps++;
            for (String n : adjacency.get(v)) {
                steps++;
                if (visited.add(n)) queue.add(n);
            }
        }
        return new TraversalResult("BFS", order, steps, System.nanoTime() - t0);
    }

    /** DFS using an explicit stack (avoids StackOverflow on large graphs) - O(V + E). */
    public TraversalResult dfs(String start) {
        long t0 = System.nanoTime();
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        long steps = 0;
        stack.push(start);
        while (!stack.isEmpty()) {
            String v = stack.pop();
            if (!visited.add(v)) continue;
            order.add(v);
            steps++;
            List<String> nbrs = adjacency.get(v);
            for (int i = nbrs.size() - 1; i >= 0; i--) { // reverse so first neighbour is explored first
                steps++;
                if (!visited.contains(nbrs.get(i))) stack.push(nbrs.get(i));
            }
        }
        return new TraversalResult("DFS", order, steps, System.nanoTime() - t0);
    }

    /** Graph search: is there a path from a to b? (uses BFS) */
    public boolean pathExists(String a, String b) {
        return bfs(a).order.contains(b);
    }

    /** Builds a connected random graph for performance testing. */
    public static Graph randomConnected(int vertices, int extraEdges, long seed) {
        Graph g = new Graph();
        Random r = new Random(seed);
        for (int i = 0; i < vertices; i++) g.addVertex("V" + i);
        for (int i = 1; i < vertices; i++) g.addEdge("V" + i, "V" + r.nextInt(i)); // spanning tree
        for (int i = 0; i < extraEdges; i++) g.addEdge("V" + r.nextInt(vertices), "V" + r.nextInt(vertices));
        return g;
    }
}
