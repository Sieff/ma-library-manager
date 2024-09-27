package LibraryManager.tool.rental;

import LibraryManager.model.entity.Book;

import java.util.Date;

public class BookRentalRequest {
    private final Book book;
    private final String borrower;
    private final Boolean asEbook;
    private final Date dueDate;

    public BookRentalRequest(Book book, String borrower, boolean asEbook, Date dueDate) {
        this.book = book;
        this.borrower = borrower;
        this.asEbook = asEbook;
        this.dueDate = dueDate;
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
