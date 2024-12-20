package online.polp;

import java.util.Optional;

public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;

    public MyQueue() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        return true;
    }

    public Optional<T> element() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return Optional.of(head.getData());
    }

    public Optional<T> remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T data = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return Optional.of(data);
    }

}
