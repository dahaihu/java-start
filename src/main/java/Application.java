import cache.lfu.List;
import cache.lfu.Node;

public class Application {
    public static void main(String[] args) {
        List<Integer, Integer> list = new List<>();
        list.pushHead(new Node<>(1, 1));
        System.out.println(list.head().getKey() + " " + list.head().getValue());
    }
}
