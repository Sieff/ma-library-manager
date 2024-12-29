package LibraryManager.tool.location;

import LibraryManager.model.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

import java.util.ArrayList;
import java.util.List;

public class GetLocationTool extends Tool {
    private final BookService bookService = BookService.getInstance();
    @Override
    public void start() {
        print(bookService.allBooksAsString());

        String bookId = stringInput("Which Book do you want to get the location for? (Enter to exit)", true);

        try {
            int id = Integer.parseInt(bookId);

            Book book = bookService.get(id);
            if (book != null) {

                // TODO: TASK: Retrieve location information for book
                println("The book is located here:");
                println("Example: The book is in bookcase \"A\", on shelf \"b\", in position 3.\n");

                // TODO: TASK: Retrieve neighboring books for book
                List<Book> neighboringBooks = new ArrayList<>();

                if (!neighboringBooks.isEmpty()) {
                    println("The following books are next to it:");
                    println(bookService.booksAsString(neighboringBooks));
                } else {
                    println("There are no books next to the book.\n");
                }

                // TODO: TASK: Retrieve books on the same shelf of the bookcase for book
                List<Book> booksOnSameShelf = new ArrayList<>();

                if (!booksOnSameShelf.isEmpty()) {
                    println("The following books are on the same shelf:");
                    println(bookService.booksAsString(booksOnSameShelf));
                } else {
                    println("There are no books on the same shelf.\n");
                }

            } else {
                printf("The Book with ID %d does not exist\n", id);
                start();
            }
        } catch (NumberFormatException ignored) {
        }
    }
}
