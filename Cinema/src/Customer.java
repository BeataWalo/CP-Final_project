public class Customer {
    private static Integer lastCustID = 0;
    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private int numberOfTickets;

    Customer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = lastCustID++;
        this.numberOfTickets = 0;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getAge() {return age;}
    public int getNumberOfTickets() { return numberOfTickets;}
    public void changeNumberOfTickets() {this.numberOfTickets++;}
}