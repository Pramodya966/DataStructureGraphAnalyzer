import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayOperations array = new ArrayOperations();
        Graph graph = new Graph();

        int choice;
        do {
            System.out.println("\n======================================");
            System.out.println("  DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("======================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Graph Operations");
            System.out.println("3. Exit");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    arrayMenu(scanner, array);
                    break;
                case 2:
                    graphMenu(scanner, graph);
                    break;
                case 3:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-3.");
            }
        } while (choice != 3);

        scanner.close();
    }

    // ================= ARRAY MENU =================
    private static void arrayMenu(Scanner scanner, ArrayOperations array) {
        int choice;
        do {
            System.out.println("\n--- ARRAY OPERATIONS ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scanner.next();
                        System.out.print("Enter value to insert: ");
                    }
                    array.insert(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Value inserted.");
                    break;
                case 2:
                    System.out.print("Enter index to delete: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scanner.next();
                        System.out.print("Enter index to delete: ");
                    }
                    int delIndex = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        int removed = array.deleteAt(delIndex);
                        System.out.println("Deleted value: " + removed);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter value to search: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scanner.next();
                        System.out.print("Enter value to search: ");
                    }
                    int searchValue = scanner.nextInt();
                    scanner.nextLine();
                    int index = array.indexOf(searchValue);
                    if (index == -1) {
                        System.out.println("Value " + searchValue + " not found.");
                    } else {
                        System.out.println("Value " + searchValue + " found at index " + index + ".");
                    }
                    break;
                case 4:
                    array.display();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        } while (choice != 5);
    }

    // ================= GRAPH MENU =================
    private static void graphMenu(Scanner scanner, Graph graph) {
        int choice;
        do {
            System.out.println("\n--- GRAPH OPERATIONS ---");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter vertex name: ");
                    String v = scanner.nextLine().trim();
                    if (graph.addVertex(v)) {
                        System.out.println("Vertex added: " + v);
                    } else {
                        System.out.println("Vertex already exists: " + v);
                    }
                    break;
                case 2:
                    System.out.print("Enter source vertex: ");
                    String src = scanner.nextLine().trim();
                    System.out.print("Enter destination vertex: ");
                    String dest = scanner.nextLine().trim();
                    String error = graph.addEdge(src, dest);
                    if (error == null) {
                        System.out.println("Edge added: " + src + " <-> " + dest);
                    } else {
                        System.out.println("Error: " + error);
                    }
                    break;
                case 3:
                    graph.display();
                    break;
                case 4:
                    System.out.print("Enter starting vertex for BFS: ");
                    String bfsStart = scanner.nextLine().trim();
                    if (!graph.hasVertex(bfsStart)) {
                        System.out.println("Start vertex not found: " + bfsStart);
                    } else {
                        System.out.println(graph.bfs(bfsStart));
                    }
                    break;
                case 5:
                    System.out.print("Enter starting vertex for DFS: ");
                    String dfsStart = scanner.nextLine().trim();
                    if (!graph.hasVertex(dfsStart)) {
                        System.out.println("Start vertex not found: " + dfsStart);
                    } else {
                        System.out.println(graph.dfs(dfsStart));
                    }
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-6.");
            }
        } while (choice != 6);
    }
}