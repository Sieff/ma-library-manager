package LibraryManager.service;

import LibraryManager.model.entity.Book;
import LibraryManager.model.entity.BookRental;
import LibraryManager.tool.TablePrinter;
import LibraryManager.tool.rental.BookRentalRequest;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class RentalService {
    private final SimpleDateFormat dateFormat;

    private static final RentalService instance = new RentalService();
    private final Map<Integer, BookRental> bookRentals = new HashMap<>();

    private RentalService() {
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
    }

    public static RentalService getInstance() {
        return instance;
    }

    public boolean rentBook(BookRentalRequest request) {
        Integer id = getNextId();

        if (request.isAsEbook()) {
            bookRentals.put(id, new BookRental(getNextId(), request));
            return true;
        }

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
            if (rental.getBook() == book && rental.getDueDate().after(Date.from(Instant.now())) && !rental.isAsEbook()) {
                return false;
            }
        }

        return true;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public TablePrinter allRentalsPrinter() {
        List<BookRental> allRentals = bookRentals.values().stream().toList();

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setHeaders(List.of("ID", "Borrower", "Expiration", "As E-Book", "Book-ID", "Title", "ISBN"));
        tablePrinter.addColumn(allRentals.stream().map(rental -> rental.getId().toString()).toList());
        tablePrinter.addColumn(allRentals.stream().map(BookRental::getBorrower).toList());
        tablePrinter.addColumn(allRentals.stream().map(rental -> dateFormat.format(rental.getDueDate())).toList());
        tablePrinter.addColumn(allRentals.stream().map(rental -> rental.isAsEbook().toString()).toList());
        tablePrinter.addColumn(allRentals.stream().map(rental -> rental.getBook().getId().toString()).toList());
        tablePrinter.addColumn(allRentals.stream().map(rental -> rental.getBook().getTitle()).toList());
        tablePrinter.addColumn(allRentals.stream().map(rental -> rental.getBook().getIsbn()).toList());

        return tablePrinter;
    }
}
