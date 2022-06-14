package cache.lru;

import common.List;

import java.util.HashMap;
import java.util.Iterator;

public class LRU<K, V> {
    private final HashMap<K, common.Node<Node<K, V>>> map;
    private final List<Node<K, V>> list;
    private final int capacity;


    public LRU(int length) {
        this.capacity = length;
        this.list = new List<>();
        this.map = new HashMap<>();
    }

    private void updatePosition(common.Node<Node<K, V>> node) {
        list.remove(node);
        list.addFirst(node);
    }

    private void push(Node<K, V> node) {
        common.Node<Node<K, V>> wrapped = new common.Node<>(node);
        list.addFirst(wrapped);
        map.put(node.getKey(), wrapped);
    }

    private void evict() {
        common.Node<Node<K, V>> node = list.getTail();
        boolean removed = list.remove(node);
        if (!removed) {
            System.out.println("no evict????");
        } else {
            map.remove(node.getValue().getKey());
        }
    }

    public void put(K k, V v) {
        common.Node<Node<K, V>> node = map.get(k);
        if (node != null) {
            this.updatePosition(node);
            node.getValue().setValue(v);
            return;
        }
        if (this.capacity == this.map.size()) {
            this.evict();
        }
        Node<K, V> newNode = new Node<>(k, v);
        this.push(newNode);
    }

    public V get(K k) {
        common.Node<Node<K, V>> node = map.get(k);
        if (node != null) {
            this.updatePosition(node);
            return node.getValue().getValue();
        }
        return null;
    }

    public int size() {
        return this.map.size();
    }

    public void print() {
        for (Iterator<Node<K, V>> it = this.list.iterator(); it.hasNext(); ) {
            Node<K, V> node = it.next();
            if (it.hasNext()) {
                System.out.printf("%s(%s) -> ", node.getKey(), node.getValue());
            } else {
                System.out.printf("%s(%s)", node.getKey(), node.getValue());
            }
        }
        System.out.println();
    }
}
