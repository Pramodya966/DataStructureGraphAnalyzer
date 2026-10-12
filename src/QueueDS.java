/** Circular array-based queue (FIFO). */
public class QueueDS {
    private int[] items = new int[10];
    private int front = 0, count = 0;

    public void enqueue(int value) {
        if (count == items.length) grow();
        items[(front + count) % items.length] = value;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue underflow: cannot dequeue from an empty queue.");
        int v = items[front];
        front = (front + 1) % items.length;
        count--;
        return v;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty: no front element.");
        return items[front];
    }

    private void grow() {
        int[] bigger = new int[items.length * 2];
        for (int i = 0; i < count; i++) bigger[i] = items[(front + i) % items.length];
        items = bigger;
        front = 0;
    }

    public boolean isEmpty() { return count == 0; }
    public int size() { return count; }

    public void display() {
        if (isEmpty()) { System.out.println("Queue is empty."); return; }
        StringBuilder sb = new StringBuilder("Queue (front -> rear): ");
        for (int i = 0; i < count; i++) {
            sb.append(items[(front + i) % items.length]);
            if (i < count - 1) sb.append(" <- ");
        }
        System.out.println(sb);
    }
}
