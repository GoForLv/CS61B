public class LinkedListDeque<T> {
    private static class Node<T> {
        T val;
        Node<T> prev, next;
        public Node(T val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
        public Node(T val, Node<T> prev, Node<T> next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> head, tail;
    private int size;

    public LinkedListDeque() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        for (int i = 0; i < other.size; i++) {
            addLast(other.get(i));
        }
    }

    public void addFirst(T val) {
        Node<T> newNode = new Node<>(val, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size += 1;
    }

    public void addLast(T val) {
        Node<T> newNode = new Node<>(val, tail.prev, tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> p = head.next;
        while (p.next != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) { return null; }
        Node<T> p = head.next;
        p.next.prev = head;
        head.next = p.next;
        size -= 1;
        return  p.val;
    }

    public T removeLast() {
        if (isEmpty()) { return null; }
        Node<T> p = tail.prev;
        p.prev.next = tail;
        tail.prev = p.prev;
        size -= 1;
        return p.val;
    }

    public T get(int index) {
        if (index < 0 || index >= size) { return null; }
        Node<T> p = head.next;
        while (index-- > 0) {
            p = p.next;
        }
        return p.val;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) { return null; }
        return getRecursive(index, head.next);
    }

    private T getRecursive(int index, Node<T> node) {
        if (index == 0) { return node.val; }
        return getRecursive(index - 1, node.next);
    }
}
