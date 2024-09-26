package LibraryManager.tool.inventory;

import LibraryManager.tool.Tool;

public class InventoryMenu extends Tool<InventoryMenuOption> {
    @Override
    protected InventoryMenuOption showForm() {
        return textIO.newEnumInputReader(InventoryMenuOption.class)
                .read("What do you want to do?");
    }

    @Override
    protected void handleResult(InventoryMenuOption result) {
        switch (result) {
            case ADD_BOOK -> {
                new AddBook().start();
                start();
            }
            case MANAGE_BOOKS -> {
                new ManageBooks().start();
                start();
            }
        }
    }
}
