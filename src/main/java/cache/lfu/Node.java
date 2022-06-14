package cache.lfu;

import common.List;

public class Node<K, V> extends cache.lru.Node<K, V> {
    private cache.lru.Node<Integer, List<cache.lfu.Node<K, V>>> frequencyNode;

    public Node(K key, V value, cache.lru.Node<Integer, List<cache.lfu.Node<K, V>>> frequencyNode) {
        super(key, value);
        this.frequencyNode = frequencyNode;
    }

    public List<cache.lfu.Node<K, V>> getNodeList() {
        return this.frequencyNode.getValue();
    }

    public Integer getFrequency() {
        return this.frequencyNode.getKey();
    }
}
