package cache.lfu;

import cache.Node;
import common.List;

public class LFUNode<K, V> extends Node<K, V> {
    private Node<Integer, List<LFUNode<K, V>>> frequencyNode;

    public LFUNode(K key, V value, Node<Integer, List<LFUNode<K, V>>> frequencyNode) {
        super(key, value);
        this.frequencyNode = frequencyNode;
    }

    public List<LFUNode<K, V>> getList() {
        return frequencyNode.getValue();
    }

    public Integer getFrequency() {
        return frequencyNode.getKey();
    }

    public void setFrequencyNode(Node<Integer, List<LFUNode<K, V>>> frequencyNode) {
        this.frequencyNode = frequencyNode;
    }
}
