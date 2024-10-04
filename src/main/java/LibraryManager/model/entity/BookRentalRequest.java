package LibraryManager.model.entity;

public class BookRentalRequest {
    private final Book book;
    private final String borrower;

    public BookRentalRequest(Book book, String borrower) {
        this.book = book;
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public String getBorrower() {
        return borrower;
    }
}
