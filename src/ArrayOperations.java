import java.util.Arrays;

/** Resizable integer array with insert, delete, search and display. */
public class ArrayOperations {
    private int[] data = new int[10];
    private int size = 0;
    private long totalShifts = 0; // counts element shifts (used by performance analysis)

    private void ensureCapacity() {
        if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
    }

    /** Insert at the end - O(1) amortised. */
    public void insert(int value) {
        ensureCapacity();
        data[size++] = value;
    }

    /** Insert at a given index - O(n) because elements are shifted right. */
    public void insertAt(int index, int value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + size);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            totalShifts++;
        }
        data[index] = value;
        size++;
    }

    /** Delete by index - O(n). */
    public int deleteAt(int index) {
        if (isEmpty()) throw new IllegalStateException("Array is empty.");
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (size - 1));
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            totalShifts++;
        }
        size--;
        return removed;
    }

    /** Delete first occurrence of a value. */
    public boolean deleteValue(int value) {
        int idx = indexOf(value);
        if (idx < 0) return false;
        deleteAt(idx);
        return true;
    }

    /** Linear search - returns index or -1. */
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) if (data[i] == value) return i;
        return -1;
    }

    public void sort() { Arrays.sort(data, 0, size); }

    public void clear() { size = 0; }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    public long getTotalShifts() { return totalShifts; }
    public int[] toArray() { return Arrays.copyOf(data, size); }

    public void display() {
        if (isEmpty()) { System.out.println("Array is empty."); return; }
        System.out.println("Array (size " + size + "): " + Arrays.toString(toArray()));
    }
}
