package LibraryManager.tool.rent;

import LibraryManager.service.RentService;
import LibraryManager.tool.Tool;

public class ReturnMenu extends Tool {
    @Override
    public void start() {
        RentService.getInstance().allRentalsPrinter().print(textIO.getTextTerminal());

        textIO.newStringInputReader().withMinLength(0)
                .read("Who is returning a book? (Enter to exit)");
    }
}
