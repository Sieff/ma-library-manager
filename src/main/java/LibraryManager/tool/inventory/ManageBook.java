package LibraryManager.tool.inventory;


import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.util.TablePrinter;
import LibraryManager.tool.Tool;

public class ManageBook extends Tool {
    private final Book book;

    public ManageBook(Book book) {
        this.book = book;
    }

    @Override
    public void start() {
        textIO.getTextTerminal();

        TablePrinter tablePrinter = BookService.getInstance().bookPrinter(book);

        tablePrinter.print(textIO.getTextTerminal());

        ManageBookOption option = textIO.newEnumInputReader(ManageBookOption.class)
                .read("What do you want to do with the Book?");

        switch (option) {
            case DELETE -> BookService.getInstance().remove(book);
        }
    }
}
