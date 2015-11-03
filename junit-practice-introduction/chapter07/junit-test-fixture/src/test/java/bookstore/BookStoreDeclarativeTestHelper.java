package bookstore;

/**
 * @author yukung
 */
public class BookStoreDeclarativeTestHelper {
    public static Book Bookオブジェクトの作成_MartinFowlerのRefactoring() {
        return new Book() {
            // インスタンスイニシャライザ
            {
                // private フィールドは使えない
                title = "Refactoring";
                price = 4500;
                author = new Author() {
                    {
                        firstName = "Martin";
                        lastName = "Fowler";
                    }
                };
            }
        };
    }
}
