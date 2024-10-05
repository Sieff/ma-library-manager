package LibraryManager.tool.rental;

import LibraryManager.model.BookRental;
import LibraryManager.service.RentalService;
import LibraryManager.tool.Tool;

public class ReturnBookTool extends Tool {
    private final RentalService rentalService = RentalService.getInstance();
    @Override
    public void start() {
        if (rentalService.getAll().isEmpty()) {
            println("No books are currently rented.");
            return;
        }

        print(rentalService.allRentalsString());

        String inputId = textIO.newStringInputReader().withMinLength(0)
                .read("Enter Rental-ID to return a book (Enter to exit)");

        if (!inputId.isEmpty()) {
            try {
                Integer id = Integer.parseInt(inputId);
                BookRental removed = RentalService.getInstance().returnBook(id);
                if (removed != null) {
                    printf("Successfully returned book %d\n", removed.getBook().getId());
                } else {
                    printf("There was no rental with ID %d\n", id);
                }
            } catch (NumberFormatException ignored) {

            }
        }
    }
}
