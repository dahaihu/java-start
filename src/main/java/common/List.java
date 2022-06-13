package common;


public class List {
    protected NodeInterface dummy;

    public List() {
        this.dummy = new Node<>(null, null);
        this.dummy.setPrev(this.dummy);
        this.dummy.setNext(this.dummy);
    }

    public void pushBack(NodeInterface node) {
        node.setList(this);

        NodeInterface oldTail = this.dummy.getPrev();
        oldTail.setNext(node);
        node.setPrev(oldTail);

        node.setNext(dummy);
        dummy.setPrev(node);
    }

    public void pushHead(NodeInterface node) {
        node.setList(this);

        NodeInterface oldHead = this.dummy.getNext();
        node.setNext(oldHead);
        oldHead.setPrev(node);

        dummy.setNext(node);
        node.setPrev(dummy);
    }

    public NodeInterface head() {
        if (this.empty()) {
            return null;
        }
        return dummy.getNext();
    }

    public NodeInterface tail() {
        if (this.empty()) {
            return null;
        }
        return dummy.getPrev();
    }

    public NodeInterface removeBack() {
        if (this.empty()) {
            return null;
        }
        NodeInterface last = this.dummy.getPrev();
        NodeInterface prev = last.getPrev();
        prev.setNext(dummy);
        dummy.setPrev(prev);
        last.reset();
        return last;
    }

    public void remove(NodeInterface node) {
        if (node.getList() != this) {
            return;
        }
        NodeInterface prev = node.getPrev();
        NodeInterface next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
    }

    public boolean empty() {
        return dummy.getNext() == dummy;
    }

}
