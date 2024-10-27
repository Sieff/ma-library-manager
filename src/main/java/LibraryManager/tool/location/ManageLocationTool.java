package LibraryManager.tool.location;

import LibraryManager.model.*;
import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class ManageLocationTool extends Tool {
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
        String region = stringInput("Which region is the bookshelf in?", false);
        String bookshelf = stringInput("Which bookshelf is the book on?", false);
        String tier = stringInput("Which tier of the bookshelf is the book on?", false);
        Integer position = integerInput("Which tier of the bookshelf is the book on? (Enter nothing for last position)", -1);

        // TODO: TASK: Save location information for book
    }
}
