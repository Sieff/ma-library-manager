package LibraryManager.ui.location;

import LibraryManager.ui.AbstractUi;

public class LocationMenuUi extends AbstractUi {
    @Override
    public void start() {
        LocationMenuOption option =  enumInput(LocationMenuOption.class, "What do you want to do?");

        switch (option) {
            case MANAGE_LOCATION -> new ManageLocationUi().start();
            case GET_LOCATION -> new GetLocationUi().start();
        }
    }
}
