package bookstore;

import org.junit.Test;

import static bookstore.BookStoreTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BookStoreTest {
    @Test
    public void getTotalPrice() throws Exception {
        // SetUp
        BookStore sut = new BookStore();
        Book book = Bookオブジェクトの作成_MartinFowlerのRefactoring();
        sut.addToCart(book, 1);
        // Exercise&Verify
        assertThat(sut.getTotalPrice(), is(4500));
    }

    @Test
    public void get_0() throws Exception {
        // SetUp
        BookStore sut = new BookStore();
        Book book = Bookオブジェクトの作成_MartinFowlerのRefactoring();
        sut.addToCart(book, 1);
        // Exercise&Verify
        assertThat(sut.get(0), is(sameInstance(book)));
    }
}
