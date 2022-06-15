import cache.lfu.LFU;

public class Application {
    public static void main(String[] args) throws Exception {
        LFU<Integer, Integer> lru = new LFU<>(10);
        lru.put(100, 100);
        lru.put(200, 200);
        lru.put(100, 200);
        lru.put(200, 200);
        lru.put(1, 1);
        lru.put(100, 500);
        for (int i =0; i<10; i++) {
            lru.put(1, i);
        }
        for (int i =0; i<8; i++) {
            lru.put(100, i);
        }
        lru.print();
//        lru.get(200);
//        lru.put(300, 300);
    }
}
