package LibraryManager.tool.rent;

import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class RentMenu extends Tool {
    @Override
    public void start() {
        BookService.getInstance().allBooksPrinter().print(textIO.getTextTerminal());

        String bookId = textIO.newStringInputReader().withMinLength(0)
                .read("Which Book do you want to rent? (Enter to exit)");

        try {
            int id = Integer.parseInt(bookId);

            if (BookService.getInstance().get(id) != null) {
                new RentBook(BookService.getInstance().get(id)).start();
            } else {
                printf("The Book with ID %d does not exist\n", id);
                start();
            }
        } catch (NumberFormatException ignored) {
        }
    }
}
