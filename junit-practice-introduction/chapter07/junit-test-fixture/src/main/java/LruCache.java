import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LruCache<E> {

    final List<String> keys = new ArrayList<>();
    private final Map<String, E> entries = new HashMap<>();

    public void put(String key, E value) {
        entries.put(key, value);
        keys.remove(key);
        keys.add(key);
    }

    public int size() {
        return entries.size();
    }

    public E get(String key) {
        if (entries.containsKey(key)) {
            keys.remove(key);
            keys.add(key);
        }
        return entries.get(key);
    }
}
