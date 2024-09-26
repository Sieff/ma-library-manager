package LibraryManager;

import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.mainMenu.MainMenu;

public class Main {
    public static void main(String[] args) {
        populateInventory();
        while (true) {
            MainMenu menu = new MainMenu();
            menu.start();
        }
    }

    private static void populateInventory() {
        BookService.getInstance().add(new Book(1, "Harry Potter Und der Stein der Weisen", "J. K. Rowling", "Carlsen", "978-3-551-35401-3", true));
        BookService.getInstance().add(new Book(2, "Der Herr der Ringe", "J. R. R. Tolkien", "Klett Cotta", "978-3-608-93828-9", true));
        BookService.getInstance().add(new Book(3, "Die Stadt der Regenfresser", "Thomas Thiemeyer", "Thomas Thiemeyer", "978-3-948-09330-3", false));
        BookService.getInstance().add(new Book(4, "Im Tal der Buchstabennudeln", " Gernot Gricksch", "Cecilie Dressler", "978-3-791-50723-1", false));
        BookService.getInstance().add(new Book(5, "Die Welt ist nicht immer Freitag", "Horst Evers", "Rowohlt Taschenbuch", "978-3-499-24251-9", true));
        BookService.getInstance().add(new Book(6, "Die Känguru-Chroniken", "Marc-Uwe Kling", "Ullstein Taschenbuch", "978-3-548-37257-0", true));
    }
}
