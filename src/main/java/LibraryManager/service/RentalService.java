package LibraryManager.service;

import LibraryManager.model.Book;
import LibraryManager.model.BookRental;
import LibraryManager.util.RowTableStringBuilder;
import LibraryManager.util.TableStringBuilder;
import LibraryManager.model.BookRentalRequest;

import java.util.*;

public class RentalService {
    private static final RentalService instance = new RentalService();
    private final Map<Integer, BookRental> bookRentals = new HashMap<>();

    private RentalService() {
    }

    public static RentalService getInstance() {
        return instance;
    }

    public boolean rentBook(BookRentalRequest request) {
        Integer id = getNextId();

        if (isAvailable(request.getBook())) {
            bookRentals.put(id, new BookRental(getNextId(), request));
            return true;
        }

        return false;
    }

    public List<BookRental> getAll() {
        return bookRentals.values().stream().toList();
    }

    public BookRental returnBook(Integer id) {
        return bookRentals.remove(id);
    }

    private Integer getNextId() {
        return bookRentals.values().stream().map(BookRental::getId).max(Integer::compareTo).orElse(0) + 1;
    }

    public boolean isAvailable(Book book) {
        for (BookRental rental : bookRentals.values()) {
            if (rental.getBook() == book) {
                return false;
            }
        }

        return true;
    }

    private List<String> getRentalHeaders() {
        return List.of("Rental-ID", "Borrower", "Book-ID", "Title");
    }

    private List<String> getRentalData(BookRental rental) {
        return List.of(
                rental.getId().toString(),
                rental.getBorrower(),
                rental.getBook().getId().toString(),
                rental.getBook().getTitle()
        );
    }

    public String allRentalsAsString() {
        List<BookRental> allRentals = bookRentals.values().stream().toList();

        TableStringBuilder tableStringBuilder = new RowTableStringBuilder();
        tableStringBuilder.setHeaders(getRentalHeaders());
        for (BookRental bookRental : allRentals) {
            tableStringBuilder.addDataPoint(getRentalData(bookRental));
        }

        return tableStringBuilder.toString();
    }
}
