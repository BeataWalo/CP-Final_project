import java.io.IOException;
import java.util.Scanner;

public class Main {
    //assuming correct values

    public static void main(String[] args) throws IOException {
        Cinema myCinema = new Cinema();

        //Firstly I'm making 3 small and 3 big cinema rooms
        myCinema.newScreeningRoom("small");
        myCinema.newScreeningRoom("small");
        myCinema.newScreeningRoom("small");
        myCinema.newScreeningRoom("big");
        myCinema.newScreeningRoom("big");
        myCinema.newScreeningRoom("big");

        //then program imports movies, screenings and customers data from folders
        myCinema.newMovie("data/Movies.txt");
        myCinema.newScreening("data/Screenings.txt");
        myCinema.newCustomer("data/Customers.txt");


        //BUYING OR BOOKING A TICKET
        try {
            //firstly the customers should choose the screening so they can find its number by title
            myCinema.showScreeningsOfMovie("La la land");
            //or by day
            //myCinema.showScreeningsByDay("Monday");

            Scanner scan = new Scanner(System.in);

            //then the customer types in chosen number
            System.out.println("Type in screening number: ");
            int showingNumber = Integer.parseInt(scan.nextLine());

            //next we see the cinema room with free, taken or reserved seats
            int theCHNumber = myCinema.ScreeningList.get(showingNumber).getSRNumber();
            myCinema.findSRbyNumber(theCHNumber).showScreeningRoom();

            //then the customer types in numbers of chosen row and seat
            System.out.println("Type in row number: ");
            int rowNumber = Integer.parseInt(scan.nextLine());
            System.out.println("Type in seat number: ");
            int seatNumber = Integer.parseInt(scan.nextLine());

            //later the customer types in his/her data
            System.out.println("Type in your first name: ");
            String firstName = String.valueOf(scan.nextLine());
            System.out.println("Type in your last name: ");
            String lastName = String.valueOf(scan.nextLine());
            System.out.println("Type in your age: ");
            int age = Integer.parseInt(scan.nextLine());

            Customer cust = myCinema.findCustByName(firstName, lastName, age);
            Ticket t1 = new Ticket(showingNumber, cust);

            try {
                //and the customer buys or books the ticket
                myCinema.buyTicket(t1, rowNumber, seatNumber );
                //or books the ticket
                //myCinema.bookTicket(t1, rowNumber, seatNumber);
            } catch (NoAvailableSeatException e) {
                System.out.println("ERROR" + e);
            }

            //we can see, if the seat changed its status:
            myCinema.findSRbyNumber(theCHNumber).showScreeningRoom();

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            return;
        }

        //customer can also find films of a certain genre
        try {
            myCinema.findMovieByGenre("musical");
            myCinema.findMovieByGenre("comedy");
        } catch (NoMovieException e) {
            System.out.println("ERROR: " + e);
        }
    }
}