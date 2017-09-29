package jp.ascii;

public class Magazine {
    private String title;
    private Long price;

    public Magazine(String title) {
        this.title = title;
    }

    public Magazine(Long price) {
        this.price = price;
    }

    public String toString() {
        return getClass().getName()
            + "@"
            + Integer.toHexString(hashCode())
            + "(\"" + title + "\", " + price + ")";
    }
}
