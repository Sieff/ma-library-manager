package LibraryManager.model.entity;

public class BookRental {
    private final Integer id;
    private final Book book;
    private final String borrower;

    public BookRental(Integer id, BookRentalRequest request) {
        this.id = id;
        this.book = request.getBook();
        this.borrower = request.getBorrower();
    }

    public Integer getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public String getBorrower() {
        return borrower;
    }
}
