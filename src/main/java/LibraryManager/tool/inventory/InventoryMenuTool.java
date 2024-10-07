package LibraryManager.tool.inventory;

import LibraryManager.tool.Tool;

/**
 * A user interface to display the inventory menu.
 */
public class InventoryMenuTool extends Tool {
    @Override
    public void start() {
        InventoryMenuOption option = enumInput(InventoryMenuOption.class, "What do you want to do?");

        switch (option) {
            case ADD_BOOK -> {
                new AddBookTool().start();
                start();
            }
            case MANAGE_BOOKS -> {
                new ManageBooksTool().start();
                start();
            }
        }
    }
}
