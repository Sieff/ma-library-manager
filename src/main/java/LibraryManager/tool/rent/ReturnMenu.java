package LibraryManager.tool.rent;

import LibraryManager.service.RentService;
import LibraryManager.tool.Tool;

public class ReturnMenu extends Tool<String> {
    @Override
    protected String showForm() {
        RentService.getInstance().allRentalsPrinter().print(textIO.getTextTerminal());

        return textIO.newStringInputReader().withMinLength(0)
                .read("Who is returning a book? (Enter to exit)");
    }

    @Override
    protected void handleResult(String result) {

    }
}
