public class Book {
    protected int bookId;
    protected String title;
    protected int authorId;
    protected String publisher;
    protected int genreId;
    protected int publicationYear;
    protected boolean active;

    public Book(int bookId, String title, int authorId, String publisher, int genreId, int publicationYear, boolean active) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.publisher = publisher;
        this.genreId = genreId;
        this.publicationYear = publicationYear;
        this.active = active;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", publisher='" + publisher + '\'' +
                ", genreId=" + genreId +
                ", publicationYear=" + publicationYear +
                ", active=" + active +
                '}';
    }
}
