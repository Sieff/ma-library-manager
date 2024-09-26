package LibraryManager.model.entity;

public class Book {
    private final Integer id;
    private final String title;
    private final String author;
    private final String publisher;
    private final String isbn;
    private final boolean isEBook;

    public Book(Integer id, String title, String author, String publisher, String isbn, boolean isEBook) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.isEBook = isEBook;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public Boolean isEBook() {
        return isEBook;
    }
}
