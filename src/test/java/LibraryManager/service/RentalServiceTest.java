package LibraryManager.service;

import LibraryManager.model.Book;
import LibraryManager.model.BookRental;
import LibraryManager.model.BookRentalRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {
    private final BookService bookService = BookService.getInstance();
    private final RentalService rentalService = RentalService.getInstance();

    private final Book book1 = new Book(1, "", "", "", 0);
    private final Book book2 = new Book(2, "", "", "", 0);
    private final Book book3 = new Book(3, "", "", "", 0);

    @BeforeEach
    public void setUp() {
        List<Book> allBooks = bookService.getAll();
        for (Book book : allBooks) {
            bookService.remove(book);
        }

        assertEquals(0, bookService.getAll().size());

        bookService.add(book1);
        bookService.add(book2);
        bookService.add(book3);

        assertEquals(3, bookService.getAll().size());

        List<BookRental> allRentals = rentalService.getAll();
        for (BookRental bookRental : allRentals) {
            rentalService.returnBook(bookRental.getId());
        }

        assertEquals(0, rentalService.getAll().size());
    }

    @Test
    void testRentAvailable() {
        assertEquals(0, rentalService.getAll().size());

        BookRentalRequest request = new BookRentalRequest(book1, "");

        boolean success = rentalService.rentBook(request);

        assertEquals(1, rentalService.getAll().size());
        assertTrue(success);
    }

    @Test
    void testRentUnavailable() {
        assertEquals(0, rentalService.getAll().size());

        BookRentalRequest request = new BookRentalRequest(book1, "");

        rentalService.rentBook(request);

        assertEquals(1, rentalService.getAll().size());

        boolean success = rentalService.rentBook(request);

        assertEquals(1, rentalService.getAll().size());
        assertFalse(success);
    }

    @Test
    void testReturn() {
        assertEquals(0, rentalService.getAll().size());
        BookRentalRequest request1 = new BookRentalRequest(book1, "");
        BookRentalRequest request2 = new BookRentalRequest(book2, "");
        BookRentalRequest request3 = new BookRentalRequest(book3, "");
        rentalService.rentBook(request1);
        rentalService.rentBook(request2);
        rentalService.rentBook(request3);
        assertEquals(3, rentalService.getAll().size());

        BookRental removed = rentalService.returnBook(1);
        assertEquals(2, rentalService.getAll().size());
        assertNotNull(removed);

        BookRental removedAgain = rentalService.returnBook(1);
        assertNull(removedAgain);
    }

    @Test
    void testSingleton() {
        assertEquals(rentalService, RentalService.getInstance());
    }

    @Test
    void testGetAll() {
        BookRentalRequest request1 = new BookRentalRequest(book1, "");
        BookRentalRequest request2 = new BookRentalRequest(book2, "");

        rentalService.rentBook(request1);
        rentalService.rentBook(request2);

        List<BookRental> allRentals = rentalService.getAll();

        assertEquals(2, allRentals.size());
        assertTrue(allRentals.stream().anyMatch(rental -> rental.getBook() == book1));
        assertTrue(allRentals.stream().anyMatch(rental -> rental.getBook() == book2));
    }
}
