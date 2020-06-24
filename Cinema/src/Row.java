import java.util.List;

public class Row {

    private static Integer lastRowNumber = 0;
    private Integer rowNumber;
    public List<Seat> seatList;
    private int numberOfSeats;

    public Row(List<Seat> seatList, int numberOfSeats) {
        this.seatList = seatList;
        this.numberOfSeats = numberOfSeats;
        rowNumber = lastRowNumber++;
    }

    public List<Seat> getSeatList() {return seatList;}
    public void showRow() {
        for (Seat s : seatList) {
            s.showSeat();
        }
        System.out.print("\n");
    }

    public String toString() {
        return "\nrow: " + rowNumber;
    }
}