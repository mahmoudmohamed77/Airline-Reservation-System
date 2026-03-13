import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
public class Booking
{
    private String name_passenger;
    private int number_flight;
    private int count_tickets;
    private int total_cost;
    private static int counter = 1;
    private static int in=-1;
    private int grade;
    private String io;
    private int bookingId;
    String bookingDate;
    Trip trip;
    ArrayList<Booking> list=new ArrayList<Booking>();
    ArrayList<Booking> list2=new ArrayList<Booking>();
    DBManager jk=new DBManager();
    public Booking(){

    }
    public Booking(String name_passenger, int number_flight, int count_tickets, int total_cost, String bookingDate) {
        this.name_passenger = name_passenger;
        this.number_flight = number_flight;
        this.count_tickets = count_tickets;
        this.total_cost = total_cost;
        this.bookingDate = bookingDate;
    }

    public Booking(String name, int flip , int count_flip,Trip trip) {
        this.trip = trip;
        name_passenger=name;
        number_flight=flip;
        count_tickets=count_flip;
    }
    public Booking(int id, String name, int flightNumber, int tickets, int totalCost,String date) {
        this.bookingId = id;
        this.name_passenger = name;
        this.number_flight = flightNumber;
        this.count_tickets = tickets;
        this.total_cost = totalCost;
        this.bookingDate=date;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setList(Booking b) {
        list.add(b);
        list2.add(b);
    }

    public void setGrade() {
        this.grade = in;
    }

    public int getGrade() {
        return grade;
    }

    public String getIo() {
       io= trip.getGrads()[in-1];
        return io;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "Id:"+ getBookingId()
                +", Name:"+name_passenger
                +", number flight:"+ trip.getTrip_number()
                +", Road "+ trip.getFrom()+"->"+trip.getTo()+
                ", Price:" + total_cost
                +", Seats:" + count_tickets
                + ",booking Date:"+bookingDate+
                '}';
    }
    public String getName_passenger() {
        return name_passenger;
    }

    public int getNumber_flight() {
        return number_flight;
    }

    public int getCount_tickets() {
        return count_tickets;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setBookingId1(int f) throws IOException, SQLException {
        bookingId=f;
    }
    public void setBookingId() throws IOException, SQLException {
        bookingId=jk.jok()+1;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setCount_tickets(int count_tickets) {
        this.count_tickets = count_tickets;
    }

    public static int getIn() {
        return in;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public void setName_passenger(String name_passenger) {
        this.name_passenger = name_passenger;
    }
    public void Caulate_total_cost(int coun,int grade){
        int rr =grade-1;
        total_cost=coun*trip.getPrice()[rr];
        in=grade;

    }
    public void display_offer(){
        System.out.println("Name:"+name_passenger);
        System.out.println("total cost:"+total_cost);
    }
    public void display_book(){
        System.out.println("------------------------------");
        System.out.println("Id:"+ getBookingId()
                +"\nName:"+name_passenger
                +"\nnumber flight:"+ trip.getTrip_number()
                +"\nfrom:"+ trip.getFrom()+"->"+trip.getTo() + "\ndate trip:"+trip.getDay()+"/"+trip.getMonth()+"/"+trip.getYear()
                +  "\nprice:" + total_cost
                + "\nGrade:" + in
                +"\nseats:" + count_tickets
                + "\nbooking_date:"+bookingDate);

        System.out.println("------------------------------");

    }
}
