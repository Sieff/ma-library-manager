package LibraryManager.service;

import LibraryManager.model.entity.Book;
import LibraryManager.tool.TablePrinter;

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

    public TablePrinter allBooksPrinter() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setHeaders(List.of("ID", "Title", "Author"));
        tablePrinter.addColumn(getAll().stream().map(book -> book.getId().toString()).toList());
        tablePrinter.addColumn(getAll().stream().map(Book::getTitle).toList());
        tablePrinter.addColumn(getAll().stream().map(Book::getAuthor).toList());

        return tablePrinter;
    }

    public TablePrinter bookPrinter(Book book) {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setHeaders(List.of("ID", "Title", "Author"));
        tablePrinter.addColumn(List.of(book.getId().toString()));
        tablePrinter.addColumn(List.of(book.getTitle()));
        tablePrinter.addColumn(List.of(book.getAuthor()));

        return tablePrinter;
    }
}
