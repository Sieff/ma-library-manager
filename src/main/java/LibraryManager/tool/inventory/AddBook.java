package LibraryManager.tool.inventory;

import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class AddBook extends Tool<Book> {
    @Override
    protected Book showForm() {
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

        return new Book(BookService.getInstance().getNextId(), title, author, publisher, isbn, isEBook);
    }

    @Override
    protected void handleResult(Book result) {
        boolean success = BookService.getInstance().add(result);
        if (success) {
            println("Added Book: " + result.getTitle());
        } else {
            println("Failed to add Book: " + result.getTitle());
        }
    }
}
