package LibraryManager.ui.inventory;

import LibraryManager.service.BookService;
import LibraryManager.ui.AbstractUi;

/**
 * A user interface to display all books and select one to manage.
 */
public class ManageBooksUi extends AbstractUi {
    private final BookService bookService = BookService.getInstance();

    @Override
    public void start() {
        print(bookService.allBooksAsString());

        String bookId = stringInput("Which Book do you want to manage? (Enter to exit)", true);

        try {
            int id = Integer.parseInt(bookId);

            if (bookService.get(id) != null) {
                new ManageBookUi(bookService.get(id)).start();
            } else {
                printf("The Book with ID %d does not exist\n", id);
                start();
            }
        } catch (NumberFormatException ignored) {
        }
    }
}
