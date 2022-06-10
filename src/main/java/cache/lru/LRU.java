package cache.lru;

import common.List;
import common.Node;

import java.util.HashMap;

public class LRU<K, V> {
    private HashMap<K, Node<K, V>> map;
    private List<K, V> list;
    private int length;


    public LRU(int length) {
        this.length = length;
        this.list = new List<K, V>();
        this.map = new HashMap<K, Node<K, V>>();
    }

    private void updatePosition(Node<K, V> node) {
        list.remove(node);
        list.pushHead(node);
    }

    private void push(Node<K, V> node) {
        map.put(node.getKey(), node);
        list.pushHead(node);
    }

    private void evict() {
        Node<K, V> removed = list.removeBack();
        map.remove(removed.getKey());
    }

    public void put(K k, V v) {
        Node<K, V> node = map.get(k);
        if (node != null) {
            this.updatePosition(node);
            node.setValue(v);
            return;
        }
        if (this.length == this.map.size()) {
            this.evict();
        }
        Node<K, V> newNode = new Node<K, V>(k, v);
        this.push(newNode);
    }

    public V get(K k) {
        Node<K, V> node = map.get(k);
        if (node != null) {
            this.updatePosition(node);
            return node.getValue();
        }
        return null;
    }

    public void print() {
        for (Node<K, V> node = this.list.head(); node != this.list.tail(); node = node.getNext()) {
            System.out.printf("%s (%s) -> ", node.getKey(), node.getValue());
        }
        Node<K, V> node = this.list.tail();
        if (node != null) {
            System.out.printf("%s(%s)", node.getKey(), node.getValue());
        }
        System.out.println();
    }
}
