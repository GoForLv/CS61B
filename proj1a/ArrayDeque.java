public class ArrayDeque<T> {
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

    public void addFirst(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        head = (head - 1 + capacity) % capacity;
        data[head] = value;
        size += 1;
    }

    public void addLast(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[tail] = value;
        tail = (tail + 1) % capacity;
        size += 1;
    }

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

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return data[(head + index) % capacity];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100000; i++) {
            deque.addLast(i);
        }
        System.out.println(deque.size());
        for (int i = 0; i < 90000; i++) {
            deque.removeFirst();
        }
        System.out.println(deque.size());
    }
}
