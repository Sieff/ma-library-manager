package LibraryManager.tool.location;

import LibraryManager.tool.Tool;

public class LocationMenu extends Tool {
    @Override
    public void start() {
        LocationMenuOption option =  enumInput(LocationMenuOption.class, "What do you want to do?");

        switch (option) {
            case MANAGE_LOCATION -> new ManageLocationTool().start();
            case GET_LOCATION -> new GetLocationTool().start();
        }
    }
}
