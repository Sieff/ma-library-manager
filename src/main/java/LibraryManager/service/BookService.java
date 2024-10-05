package LibraryManager.service;

import LibraryManager.model.Book;
import LibraryManager.util.RowTablePrinter;
import LibraryManager.util.TableStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
    private static final BookService instance = new BookService();
    private final Map<Integer, Book> books;

    private BookService() {
        books = new HashMap<>();
    }

    public static BookService getInstance() {
        return instance;
    }

    public boolean add(Book book) {
        if (books.containsKey(book.getId())) {
            return false;
        }

        books.put(book.getId(), book);
        return true;
    }

    public Integer getNextId() {
        return books.keySet().stream().mapToInt(Integer::intValue).max().orElse(0) + 1;
    }

    public List<Book> getAll() {
        return books.values().stream().toList();
    }

    public Book get(int id) {
        return books.get(id);
    }

    public void remove(Book book) {
        books.remove(book.getId());
    }

    private List<String> getBookHeaders() {
        return List.of("ID", "Title", "Author", "Genre", "Publication Year");
    }

    private List<String> getBookData(Book book) {
        return List.of(
                book.getId().toString(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublicationYear().toString()
        );
    }

    public String allBooksAsString() {
        TableStringBuilder tablePrinter = new RowTablePrinter();
        tablePrinter.setHeaders(getBookHeaders());
        for (Book book : getAll()) {
            tablePrinter.addDataPoint(getBookData(book));
        }
        return tablePrinter.toString();
    }

    public String bookAsString(Book book) {
        TableStringBuilder tablePrinter = new RowTablePrinter();
        tablePrinter.setHeaders(getBookHeaders());
        tablePrinter.addDataPoint(getBookData(book));

        return tablePrinter.toString();
    }
}
