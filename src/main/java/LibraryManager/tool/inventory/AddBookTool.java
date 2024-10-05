package LibraryManager.tool.inventory;

import LibraryManager.model.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class AddBookTool extends Tool {
    private final BookService bookService = BookService.getInstance();

    @Override
    public void start() {
        String title = textIO.newStringInputReader()
                .read("What is the title of the book?");
        String author = textIO.newStringInputReader()
                .read("What is the author of the book?");
        String genre = textIO.newStringInputReader()
                .read("What is the genre of the book?");
        int publicationYear = textIO.newIntInputReader()
                .read("What is the publication year of the book?");

        Book book = new Book(bookService.getNextId(), title, author, genre, publicationYear);

        boolean success = bookService.add(book);
        if (success) {
            println("Added Book: " + book.getTitle());
        } else {
            println("Failed to add Book: " + book.getTitle());
        }
    }
}
