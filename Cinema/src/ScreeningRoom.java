import java.util.List;

public class ScreeningRoom {
    private static Integer lastSRNumber = 1;
    private Integer sRNumber;
    private List<Row> rowList;
    private String type;

    public ScreeningRoom(List<Row> rowList) {
        this.rowList = rowList;
        this.sRNumber = lastSRNumber++;
        this.type = " ";
    }

    public int getNumber() {
        return sRNumber;
    }
    public List<Row> getRowList() {return rowList;}
    public String getType() {return type;}


    public void showScreeningRoom() {
       int i = 1;
        for (Row r : rowList) {
            System.out.print(i + ": ");
            r.showRow();
            i++;
        }
        System.out.print("\n");
    }

    public String toString() {
        return "Screening Room number: " + sRNumber;
    }
}