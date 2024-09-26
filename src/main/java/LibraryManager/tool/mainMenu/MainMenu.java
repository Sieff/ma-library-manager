package LibraryManager.tool.mainMenu;

import LibraryManager.tool.Tool;
import LibraryManager.tool.inventory.InventoryMenu;
import LibraryManager.tool.rent.RentMenu;
import LibraryManager.tool.rent.ReturnMenu;

public class MainMenu extends Tool<MainMenuOption> {
    @Override
    protected MainMenuOption showForm() {
        return textIO.newEnumInputReader(MainMenuOption.class)
                .read("What do you want to do?");
    }

    @Override
    protected void handleResult(MainMenuOption result) {
        switch (result) {
            case INVENTORY -> new InventoryMenu().start();
            case RENT -> new RentMenu().start();
            case RETURN -> new ReturnMenu().start();
        }
    }
}
