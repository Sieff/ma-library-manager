package LibraryManager.ui.inventory;


import LibraryManager.model.Book;
import LibraryManager.service.BookService;
import LibraryManager.ui.AbstractUi;

/**
 * A user interface to display a book and options to manage it.
 * Accepts a book to manage on creation.
 */
public class ManageBookUi extends AbstractUi {
    private final Book book;
    private final BookService bookService = BookService.getInstance();

    /**
     * To manage a book, pass the book when creating the tool
     * @param book The book to be managed
     */
    public ManageBookUi(Book book) {
        this.book = book;
    }

    @Override
    public void start() {
        print(bookService.bookAsString(book));

        ManageBookOption option = enumInput(ManageBookOption.class, "What do you want to do with the Book?");

        switch (option) {
            case DELETE -> bookService.remove(book);
        }
    }
}
