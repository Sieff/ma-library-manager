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

        String borrower = textIO.newStringInputReader()
                .read("Who wants to rent the book?");

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

        String bookId = textIO.newStringInputReader().withMinLength(0)
                .read("Which Book do you want to rent? (Enter to exit)");

        try {
            int id = Integer.parseInt(bookId);

            if (bookService.get(id) != null) {
                return bookService.get(id);
            } else {
                printf("The Book with ID %d does not exist\n", id);
                start();
                return null;
            }

        } catch (NumberFormatException e) {
            return null;
        }
    }
}
