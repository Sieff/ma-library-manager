package LibraryManager.tool.mainMenu;

import LibraryManager.tool.Tool;
import LibraryManager.tool.inventory.InventoryMenuTool;
import LibraryManager.tool.location.LocationMenu;
import LibraryManager.tool.rental.BookRentalTool;
import LibraryManager.tool.rental.ReturnBookTool;

/**
 * A user interface to display the main menu of the tool.
 */
public class MainMenuTool extends Tool {
    @Override
    public void start() {
        MainMenuOption option =  enumInput(MainMenuOption.class, "What do you want to do?");

        switch (option) {
            case INVENTORY -> new InventoryMenuTool().start();
            case RENT -> new BookRentalTool().start();
            case RETURN -> new ReturnBookTool().start();
            case LOCATION -> new LocationMenu().start();
        }
    }
}
