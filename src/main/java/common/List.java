package common;

import java.util.Iterator;
import java.util.function.Consumer;

public class List<E> {
    private class iterator<E> implements Iterator<E> {
        Node<E> cur;

        public iterator(Node<E> start) {
            cur = start;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            Node<E> node = cur;
            cur = cur.getNext();
            return node.getValue();
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    public Iterator<E> iterator() {
        return new iterator<>(head);
    }

    private Node<E> head, tail;

    public Node<E> getHead() {
        return this.head;
    }

    public Node<E> getTail() {
        return this.tail;
    }


    public void addFirst(Node<E> node) {
        node.setList(this);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        Node<E> oldHead = head;
        this.head = node;
        this.head.setNext(oldHead);
        oldHead.setPrev(this.head);
    }

    public void addTail(Node<E> node) {
        node.setList(this);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        Node<E> oldTail = tail;
        this.tail = node;
        oldTail.setNext(this.tail);
        this.tail.setPrev(oldTail);
    }

    public boolean remove(Node<E> node) {
        if (this != node.getList()) {
            return false;
        }
        if (this.head == node && this.tail == node) {
            this.head = null;
            this.tail = null;
        } else if (this.head == node) {
            this.head = node.getNext();
            this.head.setPrev(null);
        } else if (this.tail == node) {
            this.tail = node.getPrev();
            this.tail.setNext(null);
        } else {
            Node<E> prev = node.getPrev();
            Node<E> next = node.getNext();
            prev.setNext(next);
            next.setPrev(prev);
        }
        node.reset();
        return true;
    }
}
