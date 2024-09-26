package LibraryManager.tool.rent;

import LibraryManager.model.entity.Book;

import java.util.Date;

public class RentBookRequest {
    private final Book book;
    private final String borrower;
    private final Boolean asEbook;
    private final Date expiration;

    public RentBookRequest(Book book, String borrower, boolean asEbook, Date expiration) {
        this.book = book;
        this.borrower = borrower;
        this.asEbook = asEbook;
        this.expiration = expiration;
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

    public Date getExpiration() {
        return expiration;
    }
}
