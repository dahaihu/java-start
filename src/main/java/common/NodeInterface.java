package common;

public interface NodeInterface {
    public void setPrev(NodeInterface prev);

    public NodeInterface getPrev();

    public void setNext(NodeInterface next);

    public NodeInterface getNext();

    public NodeInterface newInstance();

    public void setList(List list);

    public List getList();

    public void reset();
}
