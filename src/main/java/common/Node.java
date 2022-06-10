package common;

public class Node<K, V> {
    private K key;
    private V value;
    private List<K, V> list;
    private Node<K, V> next, prev;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    public void setPrev(Node<K, V> prev) {
        this.prev = prev;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getPrev() {
        return prev;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void reset() {
        prev = null;
        next = null;
        list = null;
    }

    public void setList(List<K, V> list) {
        this.list = list;
    }

    public List<K, V> getList() {
        return list;
    }
}
