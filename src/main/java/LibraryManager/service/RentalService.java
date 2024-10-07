package LibraryManager.service;

import LibraryManager.model.Book;
import LibraryManager.model.BookRental;
import LibraryManager.util.RowTableStringBuilder;
import LibraryManager.util.TableStringBuilder;
import LibraryManager.model.BookRentalRequest;

import java.util.*;

/**
 * A service to manage rentals for the library
 */
public class RentalService {
    private static final RentalService instance = new RentalService();
    private final Map<Integer, BookRental> bookRentals = new HashMap<>();

    private RentalService() {
    }

    /**
     * Get the instance of the RentalService
     * @return The instance of the service
     */
    public static RentalService getInstance() {
        return instance;
    }

    /**
     * Try to rent a book via a book rental request
     * @param request The request to rent a book
     * @return The boolean representing success of renting a book
     */
    public boolean rentBook(BookRentalRequest request) {
        Integer id = getNextId();

        if (isAvailable(request.getBook())) {
            bookRentals.put(id, new BookRental(getNextId(), request));
            return true;
        }

        return false;
    }

    /**
     * Gets all current rentals
     * @return A list of book rentals that are active in the library
     */
    public List<BookRental> getAll() {
        return bookRentals.values().stream().toList();
    }

    /**
     * Return a book by book rental ID
     * @param id ID of the book rental to be returned
     * @return The value of the book rental that was returned
     */
    public BookRental returnBook(Integer id) {
        return bookRentals.remove(id);
    }

    /**
     * Gets the ID for the next book rental to be created
     * @return The integer for the next ID for a book rental
     */
    private Integer getNextId() {
        return bookRentals.values().stream().map(BookRental::getId).max(Integer::compareTo).orElse(0) + 1;
    }

    /**
     * Checks for availability of a book to be rented
     * @param book The book to check availability
     * @return The boolean representing availability of the book
     */
    public boolean isAvailable(Book book) {
        for (BookRental rental : bookRentals.values()) {
            if (rental.getBook() == book) {
                return false;
            }
        }

        return true;
    }

    /**
     * Get a list of table headers to render one or multiple book rentals
     * @return A list of strings with the table headers
     */
    private List<String> getRentalHeaders() {
        return List.of("Rental-ID", "Borrower", "Title", "Author");
    }

    /**
     * Get a data row for a single book rental
     * @param rental The book rental to be turned into a data row
     * @return A list of strings representing the values of a book rental
     */
    private List<String> getRentalData(BookRental rental) {
        return List.of(
                rental.getId().toString(),
                rental.getBorrower(),
                rental.getBook().getTitle(),
                rental.getBook().getAuthor()
        );
    }

    /**
     * Render all book rentals in the library as a table
     * @return A string representing all book rentals in the library
     */
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
