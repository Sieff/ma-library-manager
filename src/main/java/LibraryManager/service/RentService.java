package LibraryManager.service;

import LibraryManager.model.entity.Book;
import LibraryManager.tool.TablePrinter;
import LibraryManager.tool.rent.RentBookRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentService {

    private static final RentService instance = new RentService();
    private final List<RentBookRequest> bookRentals = new ArrayList<>();

    private RentService() {
    }

    public static RentService getInstance() {
        return instance;
    }

    public boolean rentBook(RentBookRequest request) {
        if (request.isAsEbook()) {
            bookRentals.add(request);
            return true;
        }

        if (isAvailable(request.getBook())) {
            bookRentals.add(request);
            return true;
        }

        return false;
    }

    private boolean isAvailable(Book book) {
        for (RentBookRequest rentals : bookRentals) {
            if (rentals.getBook().equals(book) && rentals.getExpiration().after(Date.from(Instant.now())) && !rentals.isAsEbook()) {
                return false;
            }
        }

        return true;
    }



    public TablePrinter allRentalsPrinter() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setHeaders(List.of("Borrower", "Expiration", "As E-Book", "Book-ID", "Title", "ISBN"));
        tablePrinter.addColumn(bookRentals.stream().map(RentBookRequest::getBorrower).toList());
        tablePrinter.addColumn(bookRentals.stream().map(rental -> rental.getExpiration().toString()).toList());
        tablePrinter.addColumn(bookRentals.stream().map(rental -> rental.isAsEbook().toString()).toList());
        tablePrinter.addColumn(bookRentals.stream().map(rental -> rental.getBook().getId().toString()).toList());
        tablePrinter.addColumn(bookRentals.stream().map(rental -> rental.getBook().getTitle()).toList());
        tablePrinter.addColumn(bookRentals.stream().map(rental -> rental.getBook().getIsbn()).toList());

        return tablePrinter;
    }
}
