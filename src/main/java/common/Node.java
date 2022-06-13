package common;

public class Node<K, V> implements NodeInterface {
    protected K key;
    protected V value;
    protected List<K, V> list;
    protected NodeInterface next, prev;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
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

    @Override
    public void setPrev(NodeInterface prev) {
        this.prev = prev;
    }

    @Override
    public NodeInterface getPrev() {
        return this.prev;
    }


    @Override
    public void setNext(NodeInterface next) {
        this.next = next;
    }

    @Override
    public NodeInterface getNext() {
        return null;
    }


    @Override
    public NodeInterface newInstance() {
        return new Node<K, V>(null, null);
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
