package LibraryManager.tool.mainMenu;

import LibraryManager.tool.Tool;
import LibraryManager.tool.inventory.InventoryMenu;
import LibraryManager.tool.rental.RentalMenu;
import LibraryManager.tool.rental.ReturnMenu;

public class MainMenu extends Tool {
    @Override
    public void start() {
        MainMenuOption option =  textIO.newEnumInputReader(MainMenuOption.class)
                .read("What do you want to do?");

        switch (option) {
            case INVENTORY -> new InventoryMenu().start();
            case RENT -> new RentalMenu().start();
            case RETURN -> new ReturnMenu().start();
        }
    }
}
