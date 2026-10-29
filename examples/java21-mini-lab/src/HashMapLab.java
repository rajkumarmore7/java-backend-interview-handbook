import java.util.HashMap;
import java.util.Map;

public class HashMapLab {
    record Key(int id) {
        @Override public int hashCode() { return 7; }
    }

    public static void main(String[] args) {
        Map<Long, String> invoiceStatus = new HashMap<>();
        invoiceStatus.put(101L, "APPROVED");
        System.out.println(invoiceStatus.get(101L));

        Map<Key, String> collisions = new HashMap<>();
        collisions.put(new Key(1), "One");
        collisions.put(new Key(2), "Two");
        System.out.println(collisions.get(new Key(2)));
    }
}
