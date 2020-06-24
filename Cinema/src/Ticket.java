public class Ticket {
    private Customer cust;
    private int screeningNumber;

    Ticket(int screeningNumber, Customer cust) {
        this.screeningNumber = screeningNumber;
        this.cust = cust;
    }

    public int getScreeningNumber() {return screeningNumber;}
    public Customer getCust() {return cust;}

}