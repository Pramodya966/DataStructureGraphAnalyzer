# Data Structure & Graph Performance Analyzer

**Module:** CIT300 Data Structures and Algorithms – Graded Practical Assignment 2 (Week 12)
**Institution:** Sri Lanka Technology Campus (SLTC)
**Language:** Java (console application)

## Project Description
A single integrated console application that demonstrates arrays, stacks, queues, linked lists,
searching (linear and binary), graphs (BFS/DFS) and a performance/complexity comparison
(step counts and execution time), with input validation and handling of empty structures.

## Team Members
> **Fill in EXACT details for every member before submitting (missing or wrong details lose marks).**

| Student Name | Student ID | Assigned Responsibility |
|---|---|---|
| PRAMODYA MILINDA | 23DA2-0252 | Array and Searching implementation |
| (Full name 2) | (IT________) | Stack and Queue implementation |
| (Full name 3) | (IT________) | Linked List implementation |
| (Full name 4) | (IT________) | Graph implementation and traversal |
| (Full name 5) | (IT________) | Performance comparison, main menu, integration and testing |

*(Fewer members? Combine responsibilities – every component must still be present.)*

## Individual Contributions
Copy this block for each member:

```
Student Name:
Student ID:
Assigned Responsibility:
Individual Contribution:
- Implemented ...
- Tested ...
- Integrated ... with the main application
```

Suggested mapping of files to responsibilities:

| Responsibility | Files |
|---|---|
| Array + Searching | `ArrayOperations.java`, `SearchOperations.java` |
| Stack + Queue | `StackDS.java`, `QueueDS.java` |
| Linked List | `LinkedListDS.java` |
| Graph | `Graph.java` |
| Performance, menu, integration | `PerformanceAnalyzer.java`, `Main.java`, `InputHelper.java`, `ResultsLog.java` |

## Technologies Used
- Java 11 or newer (developed and tested on Java 21)
- Git & GitHub (branches, commits, pull requests)

## Main Features
| Component | Features | Complexity |
|---|---|---|
| Array | insert (end/index), delete (index/value), search, display, auto-resize | insert end O(1)*, insert/delete index O(n) |
| Stack | push, pop, peek, display, underflow handling | all O(1) |
| Queue | enqueue, dequeue, peek, display, circular array, underflow handling | all O(1) |
| Linked List | insert head/tail/position, delete, search, display | head O(1); others O(n) |
| Searching | linear and binary search with step count + timing, side-by-side comparison | O(n) vs O(log n) |
| Graph | add vertex/edge, display (adjacency list), BFS, DFS, path search, sample graph | O(V + E) traversals |
| Performance | search, graph traversal, array vs linked-list insertion, stack/queue throughput | – |
| Results | "Display All Results" shows everything recorded in the session | – |

\* amortised

## How to Run
Requires a JDK (11+). From the project folder:

**Linux / macOS**
```bash
./run.sh
```
**Windows**
```bat
run.bat
```
**Manually**
```bash
mkdir out
javac -d out src/*.java
java -cp out Main
```

## Project Structure
```
src/
  Main.java                 main menu + submenus (integration)
  ArrayOperations.java
  StackDS.java
  QueueDS.java
  LinkedListDS.java
  SearchOperations.java
  Graph.java
  PerformanceAnalyzer.java
  InputHelper.java          input validation
  ResultsLog.java           stores results for "Display All Results"
```

## Explaining the Performance Results
- **Linear vs Binary search:** linear checks up to *n* elements (O(n)); binary halves the range each step (O(log n)), so 100,000 elements need ~17 steps instead of 100,000. Binary search needs sorted data.
- **BFS vs DFS:** both visit every vertex and examine every edge, so both are O(V + E); step counts match and only the visiting order differs (queue vs stack). Time differences are mostly JVM/memory effects.
- **Array vs Linked List insertion at front:** the array shifts every element (O(n) each, ~n²/2 total); the linked list just relinks the head (O(1) each).
- **Stack / Queue:** push, pop, enqueue and dequeue are O(1), so total steps grow linearly with the number of operations.

## GitHub Workflow (suggested)
```bash
git init
git checkout -b main
git add . && git commit -m "Initial project structure"
git checkout -b feature/graph      # each member works on their own branch
# ... commit often with meaningful messages ...
git push origin feature/graph      # open a Pull Request, review, merge into main
```
