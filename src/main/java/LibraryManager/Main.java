package LibraryManager;

import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.mainMenu.MainMenu;

public class Main {
    private final static BookService bookService = BookService.getInstance();

    public static void main(String[] args) {
        populateInventory();
        while (true) {
            MainMenu menu = new MainMenu();
            menu.start();
        }
    }

    private static void populateInventory() {
        bookService.add(new Book(1, "Harry Potter and the Philosopher's Stone", "J. K. Rowling", "Fantasy", 1997));
        bookService.add(new Book(2, "Die Welt ist nicht immer Freitag", "Horst Evers", "Humor", 2001));
        bookService.add(new Book(3, "Die Känguru-Chroniken", "Marc-Uwe Kling", "Humor", 2001));
        bookService.add(new Book(4, "The Hobbit", "J. R. R. Tolkien", "Fantasy", 1937));
        bookService.add(new Book(5, "1984", "George Orwell", "Dystopian", 1949));
        bookService.add(new Book(6, "To Kill a Mockingbird", "Harper Lee", "Fiction", 1960));
        bookService.add(new Book(7, "Pride and Prejudice", "Jane Austen", "Romance", 1813));
        bookService.add(new Book(8, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925));
        bookService.add(new Book(9, "Moby-Dick", "Herman Melville", "Adventure", 1851));
        bookService.add(new Book(10, "Brave New World", "Aldous Huxley", "Science Fiction", 1932));
        bookService.add(new Book(11, "The Catcher in the Rye", "J. D. Salinger", "Fiction", 1951));
        bookService.add(new Book(12, "The Lord of the Rings", "J. R. R. Tolkien", "Fantasy", 1954));
        bookService.add(new Book(13, "The Chronicles of Narnia", "C. S. Lewis", "Fantasy", 1950));
        bookService.add(new Book(14, "Fahrenheit 451", "Ray Bradbury", "Dystopian", 1953));
        bookService.add(new Book(15, "Jane Eyre", "Charlotte Brontë", "Romance", 1847));
        bookService.add(new Book(16, "Wuthering Heights", "Emily Brontë", "Romance", 1847));
        bookService.add(new Book(17, "Crime and Punishment", "Fyodor Dostoevsky", "Psychological Fiction", 1866));
        bookService.add(new Book(18, "The Divine Comedy", "Dante Alighieri", "Epic Poetry", 1320));
        bookService.add(new Book(19, "Dracula", "Bram Stoker", "Horror", 1897));
        bookService.add(new Book(20, "Frankenstein", "Mary Shelley", "Horror", 1818));
        bookService.add(new Book(21, "Les Misérables", "Victor Hugo", "Historical Fiction", 1862));

    }
}
