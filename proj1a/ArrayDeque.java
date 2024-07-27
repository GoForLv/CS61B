public class ArrayDeque<T> {
    private int size;
    private int capacity = 8;
    private T[] data;
    private static int RFACTOR = 2;
    private static int LFACTOR = 4;
    private int head, tail;

    public ArrayDeque() {
        data = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = capacity - 1;
    }

//    public ArrayDeque(ArrayDeque<T> other) {
//        size = other.size;
//        capacity = (size / capacity + 1) * capacity;
//        data = (T[]) new Object[capacity];
//        for (int i = 0; i < size; i++) {
//            data[i] = other.get(i);
//        }
//        head = 0;
//        tail = size - 1;
//    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        capacity *= RFACTOR;
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
            data[i] = null;
        }
        data = newData;
    }

    public void addFirst(T value) {
        if (size == capacity) {
            resize();
        }
        head -= 1;
        if (head < 0) {
            head = capacity - 1;
        }
        data[head] = value;
        size += 1;
    }

    public void addLast(T value) {
        if (size == capacity) {
            resize();
        }
        tail += 1;
        if (tail >= capacity) {
            tail = 0;
        }
        data[tail] = value;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T value = data[head];
        data[head] = null;
        head += 1;
        if (head >= capacity) {
            head = 0;
        }
        size -= 1;
        if (size >= 16 && size * LFACTOR < capacity) {
            capacity /= RFACTOR;
        }
        return value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T value = data[tail];
        data[tail] = null;
        tail -= 1;
        if (tail < 0) {
            tail = capacity - 1;
        }
        size -= 1;
        if (size >= 16 && size * LFACTOR < capacity) {
            capacity /= RFACTOR;
        }
        return value;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return data[(head + index) % capacity];
    }

    public void printDeque() {
        int cur = head;
        for (int i = 0; i < size; i++) {
            System.out.print(data[cur] + " ");
            cur += 1;
            if (cur >= capacity) {
                cur = 0;
            }
        }
        System.out.println();
    }
}
