package LibraryManager.tool.inventory;


import LibraryManager.model.entity.Book;
import LibraryManager.service.BookService;
import LibraryManager.tool.TablePrinter;
import LibraryManager.tool.Tool;

import java.util.List;

public class ManageBook extends Tool<ManageBookOption> {
    private Book book;

    @Override
    public ManageBook withParameter(Object book) {
        this.book = (Book) book;
        return this;
    }

    @Override
    protected ManageBookOption showForm() {
        textIO.getTextTerminal();

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setHeaders(List.of("ID", "Title", "Author", "Publisher", "ISBN", "E-Book"));
        tablePrinter.addColumn(List.of(book.getId().toString()));
        tablePrinter.addColumn(List.of(book.getTitle()));
        tablePrinter.addColumn(List.of(book.getAuthor()));
        tablePrinter.addColumn(List.of(book.getPublisher()));
        tablePrinter.addColumn(List.of(book.getIsbn()));
        tablePrinter.addColumn(List.of(book.isEBook().toString()));

        tablePrinter.print(textIO.getTextTerminal());

        return textIO.newEnumInputReader(ManageBookOption.class)
                .read("What do you want to do with the Book?");
    }

    @Override
    protected void handleResult(ManageBookOption result) {
        switch (result) {
            case DELETE -> BookService.getInstance().remove(book);
        }
    }
}
