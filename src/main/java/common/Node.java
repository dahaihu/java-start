package common;

public class Node<E> {
    List<E> list;
    Node<E> prev, next;
    E value;

    public Node(E value) {
        this.value = value;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getPrev() {
        return this.prev;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public E getValue() {
        return value;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
    public List<E> getList() {
        return this.list;
    }

    public void reset() {
        this.list = null;
        this.prev = null;
        this.next = null;
    }
}
