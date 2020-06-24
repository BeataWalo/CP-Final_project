public class Movie {
    private static Integer lastMovieID = 0;
    private Integer id;
    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.id = lastMovieID++;
    }

    public Integer getID() {return id;}
    public String getTitle() {return title;}
    public String getGenre() {return genre;}

    public String toString() {
        return title;
    }
}