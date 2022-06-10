package common;


public class List<K, V> {
    private Node<K, V> dummy;

    public List() {
        this.dummy = new Node<>(null, null);
        this.dummy.setPrev(this.dummy);
        this.dummy.setNext(this.dummy);
    }

    public void pushBack(Node<K, V> node) {
        node.setList(this);

        Node<K, V> oldTail = this.dummy.getPrev();
        oldTail.setNext(node);
        node.setPrev(oldTail);

        node.setNext(dummy);
        dummy.setPrev(node);
    }

    public void pushHead(Node<K, V> node) {
        node.setList(this);

        Node<K, V> oldHead = this.dummy.getNext();
        node.setNext(oldHead);
        oldHead.setPrev(node);

        dummy.setNext(node);
        node.setPrev(dummy);
    }

    public Node<K, V> head() {
        if (this.empty()) {
            return null;
        }
        return dummy.getNext();
    }

    public Node<K, V> tail() {
        if (this.empty()) {
            return null;
        }
        return dummy.getPrev();
    }

    public Node<K, V> removeBack() {
        if (this.empty()) {
            return null;
        }
        Node<K, V> last = this.dummy.getPrev();
        Node<K, V> prev = last.getPrev();
        prev.setNext(dummy);
        dummy.setPrev(prev);
        last.reset();
        return last;
    }

    public void remove(Node<K, V> node) {
        if (node.getList() != this) {
            return;
        }
        Node<K, V> prev = node.getPrev();
        Node<K, V> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
    }

    public boolean empty() {
        return dummy.getNext() == dummy;
    }

}
