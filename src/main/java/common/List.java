package common;

import java.util.Iterator;
import java.util.function.Consumer;

public class List<E> {
    private class iterator<E> implements Iterator<E> {
        NodeWrapper<E> cur;

        public iterator(NodeWrapper<E> start) {
            cur = start;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            NodeWrapper<E> node = cur;
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

    private NodeWrapper<E> head, tail;

    public NodeWrapper<E> getHead() {
        return this.head;
    }

    public NodeWrapper<E> getTail() {
        return this.tail;
    }


    public void addFirst(NodeWrapper<E> node) {
        node.setList(this);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        NodeWrapper<E> oldHead = head;
        this.head = node;
        this.head.setNext(oldHead);
        oldHead.setPrev(this.head);
    }

    public boolean insertAfter(NodeWrapper<E> node, NodeWrapper<E> pre) {
        if (pre.getList() != this) {
            return false;
        }
        node.setList(this);
        pre.setNext(node);
        node.setPrev(pre);
        if (pre == tail) {
            tail = node;
        }
        return true;
    }

    public void addTail(NodeWrapper<E> node) {
        node.setList(this);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        NodeWrapper<E> oldTail = tail;
        this.tail = node;
        oldTail.setNext(this.tail);
        this.tail.setPrev(oldTail);
    }

    public boolean remove(NodeWrapper<E> node) {
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
            NodeWrapper<E> prev = node.getPrev();
            NodeWrapper<E> next = node.getNext();
            prev.setNext(next);
            next.setPrev(prev);
        }
        node.reset();
        return true;
    }

    public boolean empty() {
        return this.head == null;
    }
}
