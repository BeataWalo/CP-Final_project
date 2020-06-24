public class Screening {
    private static Integer lastScreeningID = 0;
    private Integer id;
    private String time;
    private String day;
    private Movie m;
    private int SRNumber;

    public Screening(Movie m, String day , String time, int SRNumber) {
        this.m = m;
        this.day = day;
        this.time = time;
        this.SRNumber = SRNumber;
        this.id = lastScreeningID++;
    }

    public Movie getMovie() { return m; }
    public String getScreeningTitle() {
        return getMovie().getTitle();
    }
    public String getDay() {return day;}
    public String getTime() {return time;}
    public int getSRNumber() {return SRNumber;}

    public String toString() {
        return "\n" + "Screening: " + m + " on " + day + " at " + time;
    }
}