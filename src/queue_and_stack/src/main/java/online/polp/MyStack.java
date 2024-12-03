package online.polp;

import java.util.Optional;

public class MyStack<T> {
    private Node<T> head;

    public MyStack() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Optional<T> peek() {
        if (isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(head.getData());
    }

    public Optional<T> pop() {
        if (isEmpty()) {
            return Optional.empty();
        }
        T data = head.getData();
        head = head.getNext();
        return Optional.of(data);
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
    }

    public Optional<Integer> search(T data) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.getData().equals(data)) {
                return Optional.of(index);
            }
            current = current.getNext();
            index++;
        }
        return Optional.empty();
    }
}
