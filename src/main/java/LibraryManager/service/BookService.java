package LibraryManager.service;

import LibraryManager.model.Book;
import LibraryManager.util.RowTableStringBuilder;
import LibraryManager.util.TableStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A service to manage books in the library
 */
public class BookService {
    private static final BookService instance = new BookService();
    private final Map<Integer, Book> books;

    private BookService() {
        books = new HashMap<>();
    }

    /**
     * Get the instance of the BookService
     * @return The instance of the service
     */
    public static BookService getInstance() {
        return instance;
    }

    /**
     * Add a book to the library
     * @param book The book to be added
     * @return A boolean to indicate success of adding a book. Fails if ID already exists.
     */
    public boolean add(Book book) {
        if (books.containsKey(book.getId())) {
            return false;
        }

        books.put(book.getId(), book);
        return true;
    }

    /**
     * Get the next ID for a book
     * @return An integer to be used as an ID to create the next book for the library
     */
    public Integer getNextId() {
        return books.keySet().stream().mapToInt(Integer::intValue).max().orElse(0) + 1;
    }

    /**
     * Get all books in the library
     * @return A list of books
     */
    public List<Book> getAll() {
        return books.values().stream().toList();
    }

    /**
     * Get a Book by ID
     * @param id The ID of the book
     * @return The book with the given ID. null if it doesn't exist
     */
    public Book get(int id) {
        return books.get(id);
    }

    /**
     * Removes a book from the library
     * @param book the book to be removed
     */
    public void remove(Book book) {
        books.remove(book.getId());
    }

    /**
     * Get a list of table headers to render one or multiple books
     * @return A list of strings with the table headers
     */
    private List<String> getBookHeaders() {
        return List.of("ID", "Title", "Author", "Genre", "Publication Year");
    }

    /**
     * Get a data row for a single book
     * @param book The book to be turned into a data row
     * @return A list of strings representing the values of a book
     */
    private List<String> getBookData(Book book) {
        return List.of(
                book.getId().toString(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublicationYear().toString()
        );
    }

    /**
     * Render all books in the library as a table
     * @return A string representing all books in the library
     */
    public String allBooksAsString() {
        TableStringBuilder tableStringBuilder = new RowTableStringBuilder();
        tableStringBuilder.setHeaders(getBookHeaders());
        for (Book book : getAll()) {
            tableStringBuilder.addDataPoint(getBookData(book));
        }
        return tableStringBuilder.render();
    }

    /**
     * Render a list of books as a table
     * @param books A list of books
     * @return A string representing the list of books
     */
    public String booksAsString(List<Book> books) {
        TableStringBuilder tableStringBuilder = new RowTableStringBuilder();
        tableStringBuilder.setHeaders(getBookHeaders());
        for (Book book : books) {
            tableStringBuilder.addDataPoint(getBookData(book));
        }
        return tableStringBuilder.render();
    }

    /**
     * Render a single book as a table with one row
     * @param book The book to be rendered
     * @return A string that represents the given book
     */
    public String bookAsString(Book book) {
        TableStringBuilder tableStringBuilder = new RowTableStringBuilder();
        tableStringBuilder.setHeaders(getBookHeaders());
        tableStringBuilder.addDataPoint(getBookData(book));

        return tableStringBuilder.render();
    }
}
