package LibraryManager.tool.inventory;

import LibraryManager.tool.Tool;

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
