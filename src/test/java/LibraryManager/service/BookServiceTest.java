package LibraryManager.service;

import LibraryManager.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private final BookService bookService = BookService.getInstance();

    @BeforeEach
    public void setUp() {
        List<Book> allBooks = bookService.getAll();
        for (Book book : allBooks) {
            bookService.remove(book);
        }

        assertEquals(0, bookService.getAll().size());
    }

    @Test
    void testAdd() {
        assertEquals(0, bookService.getAll().size());

        Book book = new Book(1, "", "", "", 0);

        bookService.add(book);

        assertEquals(1, bookService.getAll().size());

        assertEquals(book, bookService.get(1));
    }

    @Test
    void testRemove() {
        Book book1 = new Book(1, "", "", "", 0);
        Book book2 = new Book(2, "", "", "", 0);

        bookService.add(book1);
        bookService.add(book2);
        assertNotNull(bookService.get(1));

        assertEquals(2, bookService.getAll().size());

        bookService.remove(book1);

        assertEquals(1, bookService.getAll().size());

        assertNull(bookService.get(1));
        assertNotNull(bookService.get(2));
    }

    @Test
    void testSingleton() {
        assertEquals(bookService, BookService.getInstance());
    }

    @Test
    void testGetAll() {
        Book book1 = new Book(1, "", "", "", 0);
        Book book2 = new Book(2, "", "", "", 0);

        bookService.add(book1);
        bookService.add(book2);

        List<Book> allBooks = bookService.getAll();

        assertEquals(2, allBooks.size());
        assertTrue(allBooks.contains(book1));
        assertTrue(allBooks.contains(book2));
    }

    @Test
    void testGetById() {
        Book book1 = new Book(1, "", "", "", 0);
        Book book2 = new Book(2, "", "", "", 0);
        Book book3 = new Book(3, "", "", "", 0);

        bookService.add(book1);
        bookService.add(book2);
        bookService.add(book3);

        assertEquals(book2, bookService.get(book2.getId()));
    }

    @Test
    void testNextId() {
        assertEquals(1, bookService.getNextId());

        Book book1 = new Book(1, "", "", "", 0);
        Book book2 = new Book(2, "", "", "", 0);

        bookService.add(book1);
        bookService.add(book2);

        assertEquals(3, bookService.getNextId());

        bookService.remove(book1);

        assertEquals(3, bookService.getNextId());

        bookService.remove(book2);

        assertEquals(1, bookService.getNextId());
    }
}
