package LibraryManager.ui.mainMenu;

import LibraryManager.ui.AbstractUi;
import LibraryManager.ui.inventory.InventoryMenuUi;
import LibraryManager.ui.location.LocationMenuUi;
import LibraryManager.ui.rental.BookRentalUi;
import LibraryManager.ui.rental.ReturnBookUi;

/**
 * A user interface to display the main menu of the tool.
 */
public class MainMenuUi extends AbstractUi {
    @Override
    public void start() {
        MainMenuOption option =  enumInput(MainMenuOption.class, "What do you want to do?");

        switch (option) {
            case INVENTORY -> new InventoryMenuUi().start();
            case RENT -> new BookRentalUi().start();
            case RETURN -> new ReturnBookUi().start();
            case LOCATION -> new LocationMenuUi().start();
        }
    }
}
