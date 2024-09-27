package LibraryManager.tool.inventory;

import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class AddBook extends Tool {
    @Override
    public void start() {
        String title = textIO.newStringInputReader()
                .read("What is the title of the book?");
        String author = textIO.newStringInputReader()
                .read("What is the author of the book?");
        String publisher = textIO.newStringInputReader()
                .read("What is the publisher of the book?");
        String isbn = textIO.newStringInputReader()
                .read("What is the isbn of the book?");
        boolean isEBook = textIO.newBooleanInputReader()
                .read("What is the isbn of the book?");

        Book book = new Book(BookService.getInstance().getNextId(), title, author, publisher, isbn, isEBook);

        boolean success = BookService.getInstance().add(book);
        if (success) {
            println("Added Book: " + book.getTitle());
        } else {
            println("Failed to add Book: " + book.getTitle());
        }
    }
}
