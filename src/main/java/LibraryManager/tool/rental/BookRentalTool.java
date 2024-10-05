package LibraryManager.tool.rental;

import LibraryManager.model.Book;
import LibraryManager.model.BookRentalRequest;
import LibraryManager.service.BookService;
import LibraryManager.service.RentalService;
import LibraryManager.tool.Tool;

public class BookRentalTool extends Tool {
    private final BookService bookService = BookService.getInstance();
    private final RentalService rentalService = RentalService.getInstance();
    @Override
    public void start() {

        Book book = bookInput();
        if (book == null) {
            return;
        }
        if (!rentalService.isAvailable(book)) {
            println("The book is currently not available.");
            return;
        }

        print(bookService.bookAsString(book));

        String borrower = stringInput("Who wants to rent the book?", false);

        BookRentalRequest request = new BookRentalRequest(book, borrower);

        boolean success = rentalService.rentBook(request);
        if (success) {
            printf("The book is rented for %s.\n", request.getBorrower());
        } else {
            println("The book is currently not available.");
        }

    }

    private Book bookInput() {
        print(bookService.allBooksAsString());

        String bookId = stringInput("Which Book do you want to rent? (Enter to exit)", true);

        if (bookId.isEmpty()) {
            return null;
        }

        try {
            int id = Integer.parseInt(bookId);

            if (bookService.get(id) != null) {
                return bookService.get(id);
            } else {
                printf("The Book with ID %d does not exist\n", id);
                return bookInput();
            }

        } catch (NumberFormatException e) {
            print("Not a valid ID, please enter a number\n");
            return bookInput();
        }
    }
}
