public class Author {
    protected int authorId;
    protected String firstName;
    protected String lastName;
    protected String nationality;
    protected int genreId;
    protected boolean active;

    public Author(int authorId, String firstName, String lastName, String nationality, int genreId, boolean active) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.genreId = genreId;
        this.active = active;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", genreId=" + genreId +
                ", active=" + active +
                '}';
    }
}
