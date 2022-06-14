package cache.lfu;

import common.List;

import java.util.HashMap;

public class LFU<K, V> {
    private List<cache.lru.Node<Integer, List<Node<K, V>>>> frequencyList;
    private HashMap<K, common.Node<Node<K, V>>> map;
    private HashMap<Integer, common.Node<cache.lru.Node<K, V>>> frequencyMap;

    public V get(K key) {
        common.Node<Node<K, V>> node = map.get(key);
        if (node != null) {
            cache.lfu.Node<K, V> realNode = node.getValue();
            List<cache.lfu.Node<K, V>> nodelist = realNode.getNodeList();
            nodelist.remove(node);
            Integer frequency = realNode.getFrequency();
            if (!frequencyMap.containsKey(frequency+1)) {

            }
        }
        return null;
    }

    public void set(K key, V value) {

    }
}
