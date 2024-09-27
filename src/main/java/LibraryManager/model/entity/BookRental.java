package LibraryManager.model.entity;

import LibraryManager.tool.rental.BookRentalRequest;

import java.util.Date;

public class BookRental {
    private final Integer id;
    private final Book book;
    private final String borrower;
    private final Boolean asEbook;
    private final Date dueDate;

    public BookRental(Integer id, BookRentalRequest request) {
        this.id = id;
        this.book = request.getBook();
        this.borrower = request.getBorrower();
        this.asEbook = request.isAsEbook();
        this.dueDate = request.getDueDate();
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

    public Boolean isAsEbook() {
        return asEbook;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
