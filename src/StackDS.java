/** Array-based stack (LIFO). */
public class StackDS {
    private int[] items = new int[10];
    private int top = -1;

    public void push(int value) {
        if (top == items.length - 1) items = java.util.Arrays.copyOf(items, items.length * 2);
        items[++top] = value;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Stack underflow: cannot pop from an empty stack.");
        return items[top--];
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty: nothing to peek.");
        return items[top];
    }

    public boolean isEmpty() { return top == -1; }
    public int size() { return top + 1; }

    public void display() {
        if (isEmpty()) { System.out.println("Stack is empty."); return; }
        System.out.println("Stack (top -> bottom):");
        for (int i = top; i >= 0; i--) {
            System.out.println("  | " + items[i] + " |" + (i == top ? "  <- top" : ""));
        }
        System.out.println("  -----");
    }
}
