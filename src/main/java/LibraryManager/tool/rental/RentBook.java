package LibraryManager.tool.rental;


import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.service.RentalService;
import LibraryManager.tool.Tool;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

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

        boolean asEBook = textIO.newBooleanInputReader()
                .read("Do you want to rent the book as an E-Book?");

        Date dueDate = null;
        boolean validDate = false;
        while (!validDate) {
            String dateInput = textIO.newStringInputReader().read("Enter an expiration date (dd.MM.yyyy):");
            try {
                dueDate = rentalService.getDateFormat().parse(dateInput);
                if (dueDate.before(Date.from(Instant.now()))) {
                    println("Invalid date. Please choose a future date.");
                } else {
                    validDate = true;
                }
            } catch (ParseException e) {
                println("Invalid date format. Please try again.");
            }
        }

        BookRentalRequest request = new BookRentalRequest(book, borrower, asEBook, dueDate);

        boolean success = RentalService.getInstance().rentBook(request);
        if (success) {
            printf("The book is rented for %s until %s\n", request.getBorrower(), rentalService.getDateFormat().format(request.getDueDate()));
        } else {
            println("The book is not available.");
        }
    }
}
