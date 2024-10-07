package LibraryManager.model;

/**
 * Represents a request to rent a book
 */
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
