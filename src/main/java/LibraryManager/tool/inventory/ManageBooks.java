package LibraryManager.tool.inventory;

import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class ManageBooks extends Tool {
    @Override
    public void start() {
        textIO.getTextTerminal();

        BookService.getInstance().allBooksPrinter().print(textIO.getTextTerminal());

        String bookId = textIO.newStringInputReader().withMinLength(0)
                .read("Which Book do you want to manage? (Enter to exit)");

        try {
            int id = Integer.parseInt(bookId);

            if (BookService.getInstance().get(id) != null) {
                new ManageBook(BookService.getInstance().get(id)).start();
            } else {
                printf("The Book with ID %d does not exist\n", id);
                start();
            }
        } catch (NumberFormatException ignored) {
        }
    }
}
