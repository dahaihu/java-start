import cache.lru.LRU;

public class Application {
    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<>(2);
        lru.put(100, 100);
        lru.put(200, 200);
        lru.put(100, 200);
        lru.get(200);
        lru.print();
        lru.put(300, 300);
        lru.print();
        System.out.println(lru.size());
    }
}
