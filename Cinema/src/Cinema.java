import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    //"table of contents":
    //line 16 Movies
    //line 47 Rows
    //line 59 Screening Room
    //line 95 Screenings
    //line 161 Customer
    //line 198 Ticket

    //Movies
    public List<Movie> movieList = new ArrayList<>();

    public void newMovie(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] lineElem = line.split("-");
            String title = String.valueOf(lineElem[0]);
            String genre = String.valueOf(lineElem[1]);
            Movie m = new Movie(title, genre);
            movieList.add(m);
        }
    }

    public void findMovieByGenre(String genre) throws NoMovieException {
        ArrayList<Movie> byGenre = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getGenre().equals(genre)) {
                byGenre.add(m);
            }
        }
        if (byGenre.size() >= 1) {
            JOptionPane.showMessageDialog(null, genre + " movies: " + byGenre);
        } else {
            throw new NoMovieException("There is no " + genre + " movie");
        }
    }

    //Rows
    public Row newRow(int numberOfSeats) {
        int i;
        List<Seat> seatList = new ArrayList<>();
        for (i = 1; i <= numberOfSeats; i++) {
            Seat s = new Seat();
            seatList.add(s);
        }
        Row r = new Row(seatList, numberOfSeats);
        return r;
    }

    //Screening Room
    public List<ScreeningRoom> SRList = new ArrayList<>();
    public List<ScreeningRoom> basicSRList = new ArrayList<>();

    public void newScreeningRoom(String size) {
        List<Row> rowList = new ArrayList<>();
        if (size.equals("small")) {
            int i;
            for (i = 1; i <= 10; i++) {
                rowList.add(newRow(7));
            }
            ScreeningRoom SR = new ScreeningRoom(rowList);
            SR.getType().equals("small");
            SRList.add(SR);
            basicSRList.add(SR);
        } else if (size.equals("big")) {
            int x;
            for (x = 1; x <= 12; x++) {
                rowList.add(newRow(12));
            }
            ScreeningRoom SR = new ScreeningRoom(rowList);
            SR.getType().equals("big");
            SRList.add(SR);
            basicSRList.add(SR);
        }
    }

    public ScreeningRoom findSRbyNumber(int i) {
        for (ScreeningRoom SR : SRList) {
            if (SR.getNumber() == i) {
                return SR;
            }
        }
        return null;
    }

    //Screenings
    public List<Screening> ScreeningList = new ArrayList<>();

    public void newScreening(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        int i = 1;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            i++;
            String[] lineElem = line.split("-");
            Movie movie = movieList.get(Integer.parseInt(lineElem[0]));
            String day = String.valueOf(lineElem[1]);
            String time = String.valueOf(lineElem[2]);
            if (Integer.parseInt(lineElem[3]) > basicSRList.size()) {
                System.out.println("There is wrong Screening Room Number in the line number "
                        + i + " in folder Showings.txt");
            } else {
                int SRNumber = Integer.parseInt(lineElem[3]);
                ArrayList<Row> myRowList = new ArrayList<>(findSRbyNumber(SRNumber).getRowList());
                ScreeningRoom mySR = new ScreeningRoom(myRowList);
                SRList.add(mySR);
                int mySRNumber = mySR.getNumber();
                Screening myS = new Screening(movie, day, time, mySRNumber);
                ScreeningList.add(myS);
            }
        }
    }

    public void showScreeningsOfMovie(String title) throws NoScreeningException {
        int x = 0;
        ArrayList<String> thisMoviesScreening = new ArrayList<>();
        for (Screening s : ScreeningList) {
            if (s.getScreeningTitle().equals(title)) {
                int a = ScreeningList.indexOf(s);
                String i = "\n" + "on " + s.getDay() + " at " + s.getTime() + " - screening number " + a;
                thisMoviesScreening.add(i);
                x++;
            }
        }
        if (x == 0) {
            throw new NoScreeningException("There is no screening of the movie " + title);
        }
        JOptionPane.showMessageDialog(null, "List of screenings of movie "
                + title + ": " + thisMoviesScreening);
    }

    public void showScreeningsByDay(String day) throws NoScreeningException {
        int x = 0;
        ArrayList<String> thisDayScreening = new ArrayList<>();
        for (Screening s : ScreeningList) {
            if (s.getDay().equals(day)) {
                int a = ScreeningList.indexOf(s);
                String i = "\n" + "title " + s.getMovie() + " at " + s.getTime() + " - screening number " + a;
                thisDayScreening.add(i);
                x++;
            }
        }
        if (x == 0) {
            throw new NoScreeningException("There is no screening on " + day);
        }
        JOptionPane.showMessageDialog(null, "List of screenings on "
                + day + ": " + thisDayScreening);
    }

    //Customer
    List<Customer> customerList = new ArrayList<>();

    public void newCustomer(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] lineElem = line.split("-");
            String firstName = String.valueOf(lineElem[0]);
            String lastName = String.valueOf(lineElem[1]);
            int age = Integer.parseInt(lineElem[2]);
            Customer cust = new Customer(firstName, lastName, age);
            customerList.add(cust);
        }
    }

    private boolean regularCust(Customer cust) {
        return cust.getNumberOfTickets() > 5;
    }

    public Customer findCustByName(String firstName, String lastName, int age) throws IOException {
        for (Customer cust : customerList) {
            if (cust.getFirstName().equals(firstName) && cust.getLastName().equals(lastName)) {
                return cust;
            }
        }
        Customer cust1 = new Customer(firstName, lastName, age);
        Writer output;
        output = new BufferedWriter(new FileWriter("Customers.txt", true));
        output.append("\n" + firstName + "-" + lastName + "-" + age);
        output.close();
        customerList.add(cust1);
        return cust1;
    }

    //Ticket
    private double ticketPrice(Ticket t) {
        double n = 20;
        Screening s = ScreeningList.get(t.getScreeningNumber());
        if (s.getDay().equals("Saturday") || s.getDay().equals("Sunday")) {
            n = n + 5;
        }
        if (t.getCust().getAge() < 18 || t.getCust().getAge() > 65) {
            n = 0.8 * n;
        }
        if (regularCust(t.getCust())) {
            n = 0.8 * n;
        }
        return n;
    }

    private Seat chooseSeat(Ticket t, int rowNumber, int seatNumber) {
        Screening s = ScreeningList.get(t.getScreeningNumber());
        int SRNumber = s.getSRNumber();
        ScreeningRoom theCH = findSRbyNumber(SRNumber);
        Row theRow = theCH.getRowList().get(rowNumber - 1);
        Seat theSeat = theRow.getSeatList().get(seatNumber - 1);
        return theSeat;
    }

    public void buyTicket(Ticket t, int rowNumber, int seatNumber) throws NoAvailableSeatException {
        double n = ticketPrice(t);
        Seat theSeat = chooseSeat(t, rowNumber, seatNumber);
        Screening s = ScreeningList.get(t.getScreeningNumber());
        if (theSeat.getStatus().equals("free")) {
            theSeat.setStatus("taken");
            t.getCust().changeNumberOfTickets();
            System.out.println("You bought the ticket for the movie " + s.getScreeningTitle() +
                    "; Row " + rowNumber + " Seat " + seatNumber + " Price " + n + "zł");
        } else {
            throw new NoAvailableSeatException("This seat is not available.");
        }
    }

    public void bookTicket(Ticket t, int rowNumber, int seatNumber) throws NoAvailableSeatException {
        Seat theSeat = chooseSeat(t, rowNumber, seatNumber);
        Screening s = ScreeningList.get(t.getScreeningNumber());
        if (theSeat.getStatus().equals("free")) {
            theSeat.setStatus("reserved");
            t.getCust().changeNumberOfTickets();
            System.out.println("You reserved the ticket for the movie " + s.getScreeningTitle() +
                    "; Row " + rowNumber + " Seat " + seatNumber);
        } else {
            throw new NoAvailableSeatException("This seat is not available.");
        }
    }

    public String toString() {
        return "Cinema\n" +
                "movies:\n" + movieList;
    }
}