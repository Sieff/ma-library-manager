package LibraryManager.tool.rental;

import LibraryManager.model.entity.BookRental;
import LibraryManager.service.RentalService;
import LibraryManager.tool.Tool;

public class ReturnMenu extends Tool {
    @Override
    public void start() {
        RentalService.getInstance().allRentalsPrinter().print(textIO.getTextTerminal());

        String inputId = textIO.newStringInputReader().withMinLength(0)
                .read("Enter rental ID to return the book (Enter to exit)");

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
