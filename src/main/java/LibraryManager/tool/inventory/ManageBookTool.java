package LibraryManager.tool.inventory;


import LibraryManager.model.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.Tool;

public class ManageBookTool extends Tool {
    private final Book book;
    private final BookService bookService = BookService.getInstance();

    public ManageBookTool(Book book) {
        this.book = book;
    }

    @Override
    public void start() {
        print(bookService.bookAsString(book));

        ManageBookOption option = textIO.newEnumInputReader(ManageBookOption.class)
                .read("What do you want to do with the Book?");

        switch (option) {
            case DELETE -> BookService.getInstance().remove(book);
        }
    }
}
