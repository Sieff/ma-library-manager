package LibraryManager.model;

/**
 * Represents a book
 */
public class Book {
    private final Integer id;
    private final String title;
    private final String author;
    private final String genre;
    private final Integer publicationYear;


    public Book(Integer id, String title, String author, String genre, Integer publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
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

    public String getGenre() {
        return genre;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }
}
