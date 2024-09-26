package LibraryManager.tool.rent;


import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.service.RentService;
import LibraryManager.tool.Tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentBook extends Tool<RentBookRequest> {
    private Book book;

    @Override
    public RentBook withParameter(Object book) {
        this.book = (Book) book;
        return this;
    }

    @Override
    protected RentBookRequest showForm() {
        BookService.getInstance().bookPrinter(book).print(textIO.getTextTerminal());

        String borrower = textIO.newStringInputReader().read("Who wants to rent the book?");

        boolean asEBook = textIO.newBooleanInputReader()
                .read("Do you want to rent the book as an E-Book?");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        Date expirationDate = null;
        while (expirationDate == null) {
            String dateInput = textIO.newStringInputReader().read("Enter an expiration date (dd.MM.yyyy):");
            try {
                expirationDate = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                println("Invalid date format. Please try again.");
            }
        }

        return new RentBookRequest(book, borrower, asEBook, expirationDate);
    }

    @Override
    protected void handleResult(RentBookRequest result) {
        boolean success = RentService.getInstance().rentBook(result);
        if (success) {
            printf("The book is rented for %s until %s\n", result.getBorrower(), result.getExpiration().toString());
        } else {
            println("The book is not available.");
            new RentBook().withParameter(result.getBook()).start();
        }
    }
}
