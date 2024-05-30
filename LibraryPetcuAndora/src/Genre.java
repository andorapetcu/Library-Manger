public class Genre {
    protected int genreId;
    protected String name;
    protected boolean active;

    public Genre(int genreId, String name, boolean active) {
        this.genreId = genreId;
        this.name = name;
        this.active = active;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
