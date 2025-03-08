package LibraryManager.ui.rental;

import LibraryManager.model.BookRental;
import LibraryManager.service.RentalService;
import LibraryManager.ui.AbstractUi;

/**
 * A user interface to return a book to the library.
 *
 */
public class ReturnBookUi extends AbstractUi {
    private final RentalService rentalService = RentalService.getInstance();

    @Override
    public void start() {
        if (rentalService.getAll().isEmpty()) {
            println("No books are currently rented.\n");
            return;
        }

        print(rentalService.allRentalsAsString());

        String inputId = stringInput("Enter Rental-ID to return a book (Enter to exit)", true);

        if (inputId.isEmpty()) {
            return;
        }

        try {
            Integer id = Integer.parseInt(inputId);
            BookRental removed = rentalService.returnBook(id);
            if (removed != null) {
                println("Successfully returned book\n");
            } else {
                printf("There was no rental with ID %d\n", id);
                start();
            }
        } catch (NumberFormatException e) {
            print("Not a valid ID, please enter an integer value\n");
            start();
        }
    }
}
