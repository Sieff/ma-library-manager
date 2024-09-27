package LibraryManager.tool.inventory;

import LibraryManager.tool.Tool;

public class InventoryMenu extends Tool {
    @Override
    public void start() {
        InventoryMenuOption option = textIO.newEnumInputReader(InventoryMenuOption.class)
                .read("What do you want to do?");

        switch (option) {
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
