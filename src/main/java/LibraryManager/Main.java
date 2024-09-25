package LibraryManager;

import LibraryManager.menu.MainMenu;

public class Main {


    public static void main(String[] args) {
        while (true) {
            MainMenu menu = new MainMenu();
            menu.start();
        }
    }
}
