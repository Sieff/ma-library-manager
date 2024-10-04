package LibraryManager.tool.rental;


import LibraryManager.model.entity.Book;
import LibraryManager.model.entity.BookRentalRequest;
import LibraryManager.service.BookService;
import LibraryManager.service.RentalService;
import LibraryManager.tool.Tool;

public class RentBook extends Tool {
    private final Book book;
    private final RentalService rentalService = RentalService.getInstance();

    public RentBook(Book book) {
        this.book = book;
    }

    @Override
    public void start() {
        BookService.getInstance().bookPrinter(book).print(textIO.getTextTerminal());

        String borrower = textIO.newStringInputReader().read("Who wants to rent the book?");

        BookRentalRequest request = new BookRentalRequest(book, borrower);

        boolean success = rentalService.rentBook(request);
        if (success) {
            printf("The book is rented for %s.\n", request.getBorrower());
        } else {
            println("The book is not available.");
        }
    }
}
