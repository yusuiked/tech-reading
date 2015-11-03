package bookstore;

import java.util.ArrayList;
import java.util.List;

public class BookStore {

    List<Book> cart = new ArrayList<>();

    public void addToCart(Book book, int num) {
        for (int i = 0; i < num; i++) {
            cart.add(book);
        }
    }

    public Book get(int idx) {
        return cart.get(idx);
    }

    public int getTotalPrice() {
        int total = 0;
        for (Book book : cart) {
            total += book.getPrice();
        }
        return total;
    }
}

