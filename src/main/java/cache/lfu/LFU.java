package cache.lfu;

import cache.Node;
import common.List;
import common.NodeWrapper;

import java.util.HashMap;

public class LFU<K, V> {
    private final List<Node<Integer, List<LFUNode<K, V>>>> frequencyList;
    private final HashMap<Integer, NodeWrapper<Node<Integer, List<LFUNode<K, V>>>>> frequencyMap;
    private final HashMap<K, NodeWrapper<LFUNode<K, V>>> valuesMap;
    private final int capacity;

    public LFU(int capacity) throws Exception {
        if (capacity <= 1) {
            throw new Exception("invalid capacity");
        }
        this.capacity = capacity;
        frequencyList = new List<>();
        frequencyMap = new HashMap<>();
        valuesMap = new HashMap<>();
    }

    public void moveNext(NodeWrapper<LFUNode<K, V>> node, K key) {
        LFUNode<K, V> realNode = node.getValue();
        List<LFUNode<K, V>> nodeList = realNode.getList();
        nodeList.remove(node);
        Integer frequency = realNode.getFrequency();
        NodeWrapper<Node<Integer, List<LFUNode<K, V>>>> nextFreqNode = frequencyMap.get(frequency + 1);
        List<LFUNode<K, V>> nextNodeList;
        if (nextFreqNode == null) {
            nextNodeList = new List<>();
            nextFreqNode = new common.NodeWrapper<>(new Node<>(frequency + 1, nextNodeList));
            frequencyMap.put(frequency + 1, nextFreqNode);
        } else {
            nextNodeList = nextFreqNode.getValue().getValue();
        }
        realNode.setFrequencyNode(nextFreqNode.getValue());
        node = new NodeWrapper<>(realNode);
        nextNodeList.addFirst(node);
        valuesMap.put(key, node);
        frequencyList.insertAfter(nextFreqNode, frequencyMap.get(frequency));
        if (nodeList.empty()) {
            frequencyList.remove(frequencyMap.get(frequency));
            frequencyMap.remove(frequency);
        }
    }

    public V get(K key) {
        NodeWrapper<LFUNode<K, V>> wrap = valuesMap.get(key);
        if (wrap != null) {
            this.moveNext(wrap, key);
            LFUNode<K, V> realNode = wrap.getValue();
            return realNode.getValue();
        }
        return null;
    }

    public void evict() {
        NodeWrapper<Node<Integer, List<LFUNode<K, V>>>> head = frequencyList.getHead();
        List<LFUNode<K, V>> list = head.getValue().getValue();
        NodeWrapper<LFUNode<K, V>> remove = list.getTail();
        list.remove(remove);
        valuesMap.remove(remove.getValue().getKey());
        if (list.empty()) {
            Integer removedFreq = head.getValue().getKey();
            frequencyList.remove(frequencyMap.get(removedFreq));
            frequencyMap.remove(removedFreq);
        }
    }

    public void put(K key, V value) {
        NodeWrapper<LFUNode<K, V>> wrap = valuesMap.get(key);
        if (wrap != null) {
            this.moveNext(wrap, key);
            wrap.getValue().setValue(value);
            return;
        }
        if (valuesMap.size() == capacity) {
            this.evict();
        }
        NodeWrapper<Node<Integer, List<LFUNode<K, V>>>> freqNode = frequencyMap.get(1);
        if (freqNode == null) {
            freqNode = new NodeWrapper<>(new Node<>(1, new List<>()));
            frequencyList.addFirst(freqNode);
            frequencyMap.put(1, freqNode);
        }
        Node<Integer, List<LFUNode<K, V>>> realFreqNode = freqNode.getValue();
        NodeWrapper<LFUNode<K, V>> addedNode = new NodeWrapper<>(new LFUNode<>(key, value, realFreqNode));
        realFreqNode.getValue().addFirst(addedNode);
        valuesMap.put(key, addedNode);
    }

    public void print() {
        for (NodeWrapper<Node<Integer, List<LFUNode<K, V>>>> cur = this.frequencyList.getHead(); cur != null; cur = cur.getNext()) {
            System.out.printf("%d: \n", cur.getValue().getKey());
            for (NodeWrapper<LFUNode<K, V>> inner = cur.getValue().getValue().getHead(); inner != null; inner = inner.getNext()) {
                System.out.printf("=> %s(%s)", inner.getValue().getKey(), inner.getValue().getValue());
            }
            System.out.println();
        }
    }
}
