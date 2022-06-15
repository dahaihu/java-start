package common;

public class NodeWrapper<E> {
    List<E> list;
    NodeWrapper<E> prev, next;
    E value;

    public NodeWrapper(E value) {
        this.value = value;
    }

    public void setPrev(NodeWrapper<E> prev) {
        this.prev = prev;
    }

    public NodeWrapper<E> getPrev() {
        return this.prev;
    }

    public void setNext(NodeWrapper<E> next) {
        this.next = next;
    }

    public NodeWrapper<E> getNext() {
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
