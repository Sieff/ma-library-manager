package LibraryManager;

import LibraryManager.model.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.mainMenu.MainMenuTool;

public class Main {
    private final static BookService bookService = BookService.getInstance();

    public static void main(String[] args) {
        populateInventory();
        while (true) {
            MainMenuTool menu = new MainMenuTool();
            menu.start();
        }
    }

    private static void populateInventory() {
        bookService.add(new Book(bookService.getNextId(), "Harry Potter and the Philosopher's Stone", "J. K. Rowling", "Fantasy", 1997));
        bookService.add(new Book(bookService.getNextId(), "Die Welt ist nicht immer Freitag", "Horst Evers", "Humor", 2001));
        bookService.add(new Book(bookService.getNextId(), "Die Känguru-Chroniken", "Marc-Uwe Kling", "Humor", 2001));
        bookService.add(new Book(bookService.getNextId(), "The Hobbit", "J. R. R. Tolkien", "Fantasy", 1937));
        bookService.add(new Book(bookService.getNextId(), "1984", "George Orwell", "Dystopian", 1949));
        bookService.add(new Book(bookService.getNextId(), "To Kill a Mockingbird", "Harper Lee", "Fiction", 1960));
        bookService.add(new Book(bookService.getNextId(), "Pride and Prejudice", "Jane Austen", "Romance", 1813));
        bookService.add(new Book(bookService.getNextId(), "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925));
        bookService.add(new Book(bookService.getNextId(), "Moby-Dick", "Herman Melville", "Adventure", 1851));
        bookService.add(new Book(bookService.getNextId(), "Brave New World", "Aldous Huxley", "Science Fiction", 1932));
        /*
        bookService.add(new Book(bookService.getNextId(), "The Catcher in the Rye", "J. D. Salinger", "Fiction", 1951));
        bookService.add(new Book(bookService.getNextId(), "The Lord of the Rings", "J. R. R. Tolkien", "Fantasy", 1954));
        bookService.add(new Book(bookService.getNextId(), "The Chronicles of Narnia", "C. S. Lewis", "Fantasy", 1950));
        bookService.add(new Book(bookService.getNextId(), "Fahrenheit 451", "Ray Bradbury", "Dystopian", 1953));
        bookService.add(new Book(bookService.getNextId(), "Jane Eyre", "Charlotte Brontë", "Romance", 1847));
        bookService.add(new Book(bookService.getNextId(), "Wuthering Heights", "Emily Brontë", "Romance", 1847));
        bookService.add(new Book(bookService.getNextId(), "Crime and Punishment", "Fyodor Dostoevsky", "Psychological Fiction", 1866));
        bookService.add(new Book(bookService.getNextId(), "The Divine Comedy", "Dante Alighieri", "Epic Poetry", 1320));
        bookService.add(new Book(bookService.getNextId(), "Dracula", "Bram Stoker", "Horror", 1897));
        bookService.add(new Book(bookService.getNextId(), "Frankenstein", "Mary Shelley", "Horror", 1818));
        bookService.add(new Book(bookService.getNextId(), "Les Misérables", "Victor Hugo", "Historical Fiction", 1862));

         */

    }
}
