public class Seat {

    private static Integer lastSeatNumber = 0;
    private int seatNumber;
    private String status;

    public Seat() {
        this.seatNumber = lastSeatNumber++;
        this.status = "free";
    }

    public int getSeatNumber() {return seatNumber;}
    public String getStatus() {return status;}
    public void setStatus(String status) {
        this.status = status;
    }

    public void showSeat() {
        if (status.equals("free")) {
            System.out.print("o");
        } else if (status.equals("reserved")) {
            System.out.print("*");
        } else {
            System.out.print("x");
        }
    }

    public String toString() {
        return "\nseat: " + seatNumber;
    }
}