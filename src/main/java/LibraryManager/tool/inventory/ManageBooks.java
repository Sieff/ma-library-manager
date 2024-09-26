package LibraryManager.tool.inventory;

import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class ManageBooks extends Tool<String> {
    @Override
    protected String showForm() {
        textIO.getTextTerminal();

        BookService.getInstance().allBooksPrinter().print(textIO.getTextTerminal());

        return textIO.newStringInputReader().withMinLength(0)
                .read("Which Book do you want to manage? (Enter to exit)");
    }

    @Override
    protected void handleResult(String result) {
        try {
            int id = Integer.parseInt(result);

            if (BookService.getInstance().get(id) != null) {
                new ManageBook().withParameter(BookService.getInstance().get(id)).start();
            } else {
                printf("The Book with ID %d does not exist\n", id);
                start();
            }
        } catch (NumberFormatException ignored) {
        }
    }
}
