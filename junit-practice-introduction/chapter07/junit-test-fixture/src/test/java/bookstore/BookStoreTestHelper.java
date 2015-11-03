package bookstore;

public class BookStoreTestHelper {
    public static Book Bookオブジェクトの作成_MartinFowlerのRefactoring() {
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPrice(4500);
        Author author = new Author();
        author.setFirstName("Martin");
        author.setLastName("Fowler");
        book.setAuthor(author);
        return book;
    }
}
