import java.util.HashMap;
import java.util.Map;

public class ItemStock {

    private Map<String, Integer> stock = new HashMap<>();

    public int size(String key) {
        return stock.size();
    }

    public boolean contains(String key) {
        return stock.containsKey(key);
    }

    public void add(String key, int value) {
        stock.put(key, value);
    }
}
