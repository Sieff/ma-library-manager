package LibraryManager.tool.inventory;

import LibraryManager.model.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

/**
 * A user interface to add a new book to the library.
 */
public class AddBookTool extends Tool {
    private final BookService bookService = BookService.getInstance();

    @Override
    public void start() {
        String title = stringInput("What is the title of the book?", false);
        String author = stringInput("What is the author of the book?", false);
        String genre = stringInput("What is the genre of the book?", false);
        int publicationYear = integerInput("What is the publication year of the book?");

        Book book = new Book(bookService.getNextId(), title, author, genre, publicationYear);

        boolean success = bookService.add(book);
        if (success) {
            printf("Added Book: %s\n\n", book.getTitle());
        } else {
            println("Failed to add Book: " + book.getTitle());
        }
    }
}
