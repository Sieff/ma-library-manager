package LibraryManager.ui.inventory;

import LibraryManager.ui.AbstractUi;

/**
 * A user interface to display the inventory menu.
 */
public class InventoryMenuUi extends AbstractUi {
    @Override
    public void start() {
        InventoryMenuOption option = enumInput(InventoryMenuOption.class, "What do you want to do?");

        switch (option) {
            case ADD_BOOK -> {
                new AddBookUi().start();
                start();
            }
            case MANAGE_BOOKS -> {
                new ManageBooksUi().start();
                start();
            }
        }
    }
}
