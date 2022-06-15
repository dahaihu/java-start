package cache.lfu;

import common.List;

import java.util.HashMap;

public class LFU<K, V> {
    private List<cache.lru.Node<Integer, List<Node<K, V>>>> frequencyList;
    private HashMap<K, common.Node<Node<K, V>>> valuesMap;
    private HashMap<Integer, common.Node<cache.lru.Node<K, V>>> frequencyMap;

    public V get(K key) {
        common.Node<Node<K, V>> wrap = valuesMap.get(key);
        if (wrap != null) {
            cache.lfu.Node<K, V> realNode = wrap.getValue();
            List<Node<K, V>> nodelist = realNode.getNodeList();
            nodelist.remove(wrap);
            Integer frequency = realNode.getFrequency();
            if (!frequencyMap.containsKey(frequency + 1)) {
                List<Node<K, V>> nodeList = new List<>();
                wrap = new common.Node<>(realNode);
                nodeList.addFirst(wrap);
                valuesMap.put(key, wrap);
                cache.lru.Node<Integer, List<Node<K, V>>> nextFreqNode = new cache.lru.Node<>(frequency + 1, nodeList);
            }
        }
        return null;
    }

    public void set(K key, V value) {

    }
}
