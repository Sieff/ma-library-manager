package LibraryManager.service;

import LibraryManager.model.entity.Book;
import LibraryManager.model.entity.BookRental;
import LibraryManager.util.ColumnTablePrinter;
import LibraryManager.util.TablePrinter;
import LibraryManager.model.entity.BookRentalRequest;

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

    public BookRental returnBook(Integer id) {
        return bookRentals.remove(id);
    }

    private Integer getNextId() {
        return bookRentals.values().stream().map(BookRental::getId).max(Integer::compareTo).orElse(0) + 1;
    }

    private boolean isAvailable(Book book) {
        for (BookRental rental : bookRentals.values()) {
            if (rental.getBook() == book) {
                return false;
            }
        }

        return true;
    }

    public TablePrinter allRentalsPrinter() {
        List<BookRental> allRentals = bookRentals.values().stream().toList();

        TablePrinter tablePrinter = new ColumnTablePrinter();
        tablePrinter.setHeaders(List.of("Rental-ID", "Borrower", "Book-ID", "Title"));
        tablePrinter.addDataPoint(allRentals.stream().map(rental -> rental.getId().toString()).toList());
        tablePrinter.addDataPoint(allRentals.stream().map(BookRental::getBorrower).toList());
        tablePrinter.addDataPoint(allRentals.stream().map(rental -> rental.getBook().getId().toString()).toList());
        tablePrinter.addDataPoint(allRentals.stream().map(rental -> rental.getBook().getTitle()).toList());

        return tablePrinter;
    }
}
