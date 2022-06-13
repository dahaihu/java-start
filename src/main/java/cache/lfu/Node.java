package cache.lfu;

public class Node<K, V> extends common.Node<K, V> {
    private common.Node<K, common.List<Integer, Node<K, V>>> freqNode;

    public Node(K key, V value) {
        super(key, value);
    }

    public void setFreqNode(common.Node<K, common.List<Integer, Node<K, V>>> freqNode) {
        this.freqNode = freqNode;
    }

    public common.List<Integer, Node<K, V>> getNodeList() {
        return this.freqNode.getValue();
    }
}
