public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int capacity = 8;
    private T[] data;
    private static final int RFACTOR = 2;
    private int head, tail;

    public ArrayDeque() {
        data = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0 ; i < size ; i++) {
            newData[i] = get(i);
        }
        data = newData;
        head = 0;
        tail = size;
        capacity = newCapacity;
    }

    @Override
    public void addFirst(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        head = (head - 1 + capacity) % capacity;
        data[head] = value;
        size += 1;
    }

    @Override
    public void addLast(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[tail] = value;
        tail = (tail + 1) % capacity;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T value = data[head];
        head = (head + 1) % capacity;
        size -= 1;
        if (size >= 16 && size <= capacity / 4) {
            resize(capacity / RFACTOR);
        }
        return value;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        tail = (tail - 1 + capacity) % capacity;
        T value = data[tail];
        size -= 1;
        if (size >= 16 && size <= capacity / 4) {
            resize(capacity / RFACTOR);
        }
        return value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return data[(head + index) % capacity];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
