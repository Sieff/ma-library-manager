package LibraryManager.ui.location;

import LibraryManager.model.*;
import LibraryManager.service.BookService;
import LibraryManager.ui.AbstractUi;

public class ManageLocationUi extends AbstractUi {
    private final BookService bookService = BookService.getInstance();
    @Override
    public void start() {

        print(bookService.allBooksAsString());

        String bookId = stringInput("Which Book do you want to manage? (Enter to exit)", true);

        try {
            int id = Integer.parseInt(bookId);

            if (bookService.get(id) != null) {
                createLocation(bookService.get(id));
            } else {
                printf("The Book with ID %d does not exist\n", id);
                start();
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void createLocation(Book book) {
        String bookcase = stringInput("Which bookcase is the book in?", false);
        String shelf = stringInput("Which shelf of the bookcase is the book on?", false);
        Integer position = integerInput("Which position within the shelf is the book in? (Default: Last position)", -1);

        // TODO: TASK: Save location information for book
    }
}
