import java.util.HashMap;
import java.util.Map;

/**
 * @author yukung
 */
public class ItemStockImpl implements ItemStock {
    private Map<String, Integer> stock = new HashMap<>();

    @Override
    public void add(String item, int num) {
        stock.put(item, num);
    }

    @Override
    public int size(String item) {
        return stock.size();
    }

    @Override
    public boolean contains(String item) {
        return stock.containsKey(item);
    }
}
