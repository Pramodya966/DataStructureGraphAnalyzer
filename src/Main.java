import java.util.Random;
public class Main {
    private static final ArrayOperations array = new ArrayOperations();
    private static final StackDS stack = new StackDS();
    private static final QueueDS queue = new QueueDS();
    private static final LinkedListDS list = new LinkedListDS();
    private static final Graph graph = new Graph();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=============================================");
            System.out.println("DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 9);
            switch (choice) {
                case 1: arrayMenu(); break;
                case 2: stackMenu(); break;
                case 3: queueMenu(); break;
                case 4: listMenu(); break;
                case 5: searchMenu(); break;
                case 6: graphMenu(); break;
                case 7: PerformanceAnalyzer.runAll(); InputHelper.pause(); break;
                case 8: ResultsLog.displayAll(); InputHelper.pause(); break;
                case 9: System.out.println("Goodbye!"); return;
            }
        }
    }

    // ---------------------------------------------------------------- ARRAY
    private static void arrayMenu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Index");
            System.out.println("3. Delete by Index");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search for Value");
            System.out.println("6. Display Array");
            System.out.println("7. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 7);
            try {
                switch (c) {
                    case 1: {
                        int v = InputHelper.readInt("Value: ");
                        array.insert(v);
                        System.out.println("Inserted " + v + ".");
                        break;
                    }
                    case 2: {
                        int idx = InputHelper.readInt("Index (0-" + array.size() + "): ");
                        int v = InputHelper.readInt("Value: ");
                        array.insertAt(idx, v);
                        System.out.println("Inserted " + v + " at index " + idx + ".");
                        break;
                    }
                    case 3: {
                        if (array.isEmpty()) { System.out.println("Array is empty."); break; }
                        int idx = InputHelper.readInt("Index to delete: ");
                        System.out.println("Deleted " + array.deleteAt(idx) + ".");
                        break;
                    }
                    case 4: {
                        if (array.isEmpty()) { System.out.println("Array is empty."); break; }
                        int v = InputHelper.readInt("Value to delete: ");
                        System.out.println(array.deleteValue(v) ? "Deleted " + v + "." : "Value not found.");
                        break;
                    }
                    case 5: {
                        if (array.isEmpty()) { System.out.println("Array is empty."); break; }
                        int v = InputHelper.readInt("Value to search: ");
                        int idx = array.indexOf(v);
                        System.out.println(idx >= 0 ? "Found at index " + idx + "." : "Value not found.");
                        break;
                    }
                    case 6: array.display(); break;
                    case 7: return;
                }
            } catch (RuntimeException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------------- STACK
    private static void stackMenu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: {
                        int v = InputHelper.readInt("Value to push: ");
                        stack.push(v);
                        System.out.println("Pushed " + v + ".");
                        break;
                    }
                    case 2: System.out.println("Popped " + stack.pop() + "."); break;
                    case 3: System.out.println("Top element: " + stack.peek()); break;
                    case 4: stack.display(); break;
                    case 5: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------------- QUEUE
    private static void queueMenu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: {
                        int v = InputHelper.readInt("Value to enqueue: ");
                        queue.enqueue(v);
                        System.out.println("Enqueued " + v + ".");
                        break;
                    }
                    case 2: System.out.println("Dequeued " + queue.dequeue() + "."); break;
                    case 3: System.out.println("Front element: " + queue.peek()); break;
                    case 4: queue.display(); break;
                    case 5: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------- LINKED LIST
    private static void listMenu() {
        while (true) {
            System.out.println("\n------------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at Head");
            System.out.println("2. Insert at Tail");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search for Value");
            System.out.println("6. Display List");
            System.out.println("7. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 7);
            try {
                switch (c) {
                    case 1: list.insertAtHead(InputHelper.readInt("Value: ")); System.out.println("Inserted at head."); break;
                    case 2: list.insertAtTail(InputHelper.readInt("Value: ")); System.out.println("Inserted at tail."); break;
                    case 3: {
                        int pos = InputHelper.readInt("Position (0-" + list.size() + "): ");
                        int v = InputHelper.readInt("Value: ");
                        list.insertAt(pos, v);
                        System.out.println("Inserted at position " + pos + ".");
                        break;
                    }
                    case 4: {
                        if (list.isEmpty()) { System.out.println("Linked list is empty."); break; }
                        int v = InputHelper.readInt("Value to delete: ");
                        System.out.println(list.delete(v) ? "Deleted " + v + "." : "Value not found.");
                        break;
                    }
                    case 5: {
                        if (list.isEmpty()) { System.out.println("Linked list is empty."); break; }
                        int v = InputHelper.readInt("Value to search: ");
                        int pos = list.search(v);
                        System.out.println(pos >= 0 ? "Found at position " + pos + "." : "Value not found.");
                        break;
                    }
                    case 6: list.display(); break;
                    case 7: return;
                }
            } catch (RuntimeException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ------------------------------------------------------------ SEARCHING
    private static void searchMenu() {
        while (true) {
            System.out.println("\n------------- SEARCHING OPERATIONS --------------");
            System.out.println("1. Linear Search (on Array component data)");
            System.out.println("2. Binary Search (on sorted copy of Array data)");
            System.out.println("3. Compare Linear vs Binary");
            System.out.println("4. Fill Array with Random Numbers");
            System.out.println("5. Display Array");
            System.out.println("6. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 6);
            if (c == 6) return;
            if (c == 5) { array.display(); continue; }
            if (c == 4) {
                int n = InputHelper.readIntInRange("How many numbers (1-1000000)? ", 1, 1_000_000);
                Random r = new Random();
                array.clear();
                for (int i = 0; i < n; i++) array.insert(r.nextInt(n * 2));
                System.out.println("Array filled with " + n + " random numbers.");
                if (n <= 50) array.display();
                continue;
            }
            if (array.isEmpty()) {
                System.out.println("  [!] Array is empty. Add data via Array Operations or option 4.");
                continue;
            }
            int target = InputHelper.readInt("Target value: ");
            int[] unsorted = array.toArray();
            int[] sorted = array.toArray();
            java.util.Arrays.sort(sorted);
            SearchOperations.SearchResult lin = null, bin = null;
            if (c == 1 || c == 3) {
                lin = SearchOperations.linearSearch(unsorted, target);
                System.out.println(lin);
                ResultsLog.add("Search | target=" + target + " | n=" + unsorted.length + " | " + lin);
            }
            if (c == 2 || c == 3) {
                bin = SearchOperations.binarySearch(sorted, target);
                System.out.println(bin + "  (on sorted copy)");
                ResultsLog.add("Search | target=" + target + " | n=" + sorted.length + " | " + bin);
            }
            if (c == 3) {
                System.out.println("\nLinear needed " + lin.steps + " steps; Binary needed " + bin.steps + " steps.");
                System.out.println("Linear is O(n); Binary is O(log n) but requires sorted data.");
            }
        }
    }

    // ---------------------------------------------------------------- GRAPH
    private static void graphMenu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Search (Path Exists?)");
            System.out.println("7. Load Sample Graph");
            System.out.println("8. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 8);
            switch (c) {
                case 1: {
                    String v = InputHelper.readLabel("Vertex label: ");
                    System.out.println(graph.addVertex(v) ? "Vertex " + v + " added." : "Vertex already exists.");
                    break;
                }
                case 2: {
                    if (graph.vertexCount() < 2) { System.out.println("  [!] Add at least two vertices first."); break; }
                    String a = InputHelper.readLabel("From vertex: ");
                    String b = InputHelper.readLabel("To vertex: ");
                    String err = graph.addEdge(a, b);
                    System.out.println(err == null ? "Edge " + a + " - " + b + " added." : "  [!] " + err);
                    break;
                }
                case 3: graph.display(); break;
                case 4: case 5: {
                    if (graph.isEmpty()) { System.out.println("Graph is empty."); break; }
                    String s = InputHelper.readLabel("Start vertex: ");
                    if (!graph.hasVertex(s)) { System.out.println("  [!] Vertex does not exist."); break; }
                    Graph.TraversalResult res = (c == 4) ? graph.bfs(s) : graph.dfs(s);
                    System.out.println(res);
                    ResultsLog.add("Graph | start=" + s + " | " + res);
                    break;
                }
                case 6: {
                    if (graph.isEmpty()) { System.out.println("Graph is empty."); break; }
                    String a = InputHelper.readLabel("From vertex: ");
                    String b = InputHelper.readLabel("To vertex: ");
                    if (!graph.hasVertex(a) || !graph.hasVertex(b)) { System.out.println("  [!] Vertex does not exist."); break; }
                    boolean ok = graph.pathExists(a, b);
                    System.out.println("Path from " + a + " to " + b + (ok ? " EXISTS." : " does NOT exist."));
                    break;
                }
                case 7: {
                    graph.clear();
                    for (String v : new String[]{"A", "B", "C", "D", "E", "F"}) graph.addVertex(v);
                    String[][] edges = {{"A","B"},{"A","C"},{"B","D"},{"C","E"},{"D","F"},{"E","F"}};
                    for (String[] e : edges) graph.addEdge(e[0], e[1]);
                    System.out.println("Sample graph loaded (A-F).");
                    graph.display();
                    break;
                }
                case 8: return;
            }
        }
    }
}
