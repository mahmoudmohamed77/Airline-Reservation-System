import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BookingService {
    private Manger manger;
    ArrayList<Booking> show_booked = new ArrayList<Booking>();
    DBManager file =new DBManager();
    public BookingService(Manger manger) {
        this.manger = manger;
    }
    public void setBookings(ArrayList<Booking> bookings) {
        this.show_booked = bookings;
        for (Booking b : show_booked) {
            Trip trip = manger.getTripByNumber(b.getNumber_flight());
            b.setTrip(trip);
           /* manger.change_shows(b.getNumber_flight(),b.getCount_tickets());*/
        }
    }
    public void trips() throws IOException, SQLException {
        file.final_trips.clear();
        for (Trip h:manger.shows){
            file.final_trips.add(h);
        }
        file.save_trips(file.final_trips);
    }


    public void display_book(String name, int number_flight, int count_tickets, int grade) {
        Booking booking1;
        Trip trip = manger.book(number_flight, count_tickets);
        booking1 = new Booking(name, number_flight, count_tickets, trip);
        booking1.setList(booking1);
        booking1.setName_passenger(name);
        booking1.Caulate_total_cost(count_tickets, grade);
        booking1.display_offer();
    }

    public void display_book1(int choose, String name, int number_flight, int count_tickets, int grade) throws IOException, SQLException {
        Booking booking1;
        Trip trip = manger.book1(number_flight, count_tickets);
        booking1 = new Booking(name, number_flight, count_tickets, trip);
        booking1.setList(booking1);
        booking1.setName_passenger(name);
        booking1.Caulate_total_cost(count_tickets, grade);
        confirm(choose, booking1, number_flight, count_tickets);

    }

    public void show_all_no_names(int day, int month, int year, int choose) {
        if (choose == 1) {
            manger.display_search_by_date(day, month, year);
        } else {
            manger.display();
        }
    }

    public void confirm(int choose, Booking booking1, int number_flight, int count_tickets) throws IOException, SQLException {
        if (choose == 1) {
            Trip trip=new Trip(number_flight);
            LocalDate date = LocalDate.now();
            booking1.setBookingDate(date.toString());
            System.out.println("Booking Confirmed");
            booking1.setBookingId();
            show_booked.add(booking1);
            booking1.display_book();
            file.updateTripColumn(number_flight, "available",trip.getAvailable()-count_tickets) ;
            TicketGenerator.generateTicket(booking1);
            file.saveBooking_one(booking1);
        } else if (choose == 2) {
            manger.handel_book(number_flight, count_tickets);

        }
    }

    public void show(int er, String e1, String e2, int o, int day, int month, int year) {
        if (er == 1) {
            if (o == 1) {
                manger.display_search_by_name_date(e1, e2, day, month, year);
            } else {
                manger.display_search(e1, e2);
            }
        }
    }

    public void show_all() {
        for (Booking b : show_booked) {
            System.out.println(b);
        }
    }

    public void show_my(String name) {
        for (Booking b : show_booked) {
            if (b.getName_passenger().equals(name)) {
                System.out.println(b);
            }
        }
    }

    public void cancel_book(String a, int number_flight,int p) throws IOException, SQLException {
        for (int i = 0; i < show_booked.size(); i++) {
            if (show_booked.get(i).getName_passenger().equals(a) && show_booked.get(i).getNumber_flight() == number_flight&&show_booked.get(i).getBookingId()==p) {
                Booking bf = show_booked.get(i);
                manger.handel_book(number_flight, show_booked.get(i).getCount_tickets());
                Trip trip=new Trip(number_flight);
                file.updateTripColumn(number_flight, "available",trip.getAvailable()+ show_booked.get(i).getCount_tickets());
                show_booked.remove(bf);
                file.deletebook(p);
            }
        }
    }
    public void cancel_book_by_admin(int a, int number_flight) throws IOException, SQLException {
        for (int i = 0; i < show_booked.size(); i++) {
            if (show_booked.get(i).getBookingId()==a && show_booked.get(i).getNumber_flight() == number_flight) {
                Booking bf = show_booked.get(i);
                manger.handel_book(number_flight, show_booked.get(i).getCount_tickets());
                show_booked.remove(bf);
                file.deletebook(a);
            }
        }
    }

    public int revenue() {
        int revenue = 0;
        for (Booking m : show_booked) {
            revenue += m.getTotal_cost();
        }
        return revenue;
    }
    public void revenue_by_one() {
        int flight,flight2,revenue = 0;
        ArrayList<Integer> sho = new ArrayList<>();
        for (int i=0;i<show_booked.size();i++) {
            flight=show_booked.get(i).getNumber_flight();
            for (int w=i;w<show_booked.size();w++){
                flight2=show_booked.get(w).getNumber_flight();
                if (flight==flight2 && (!sho.contains(show_booked.get(i).getNumber_flight()))){
                    revenue += show_booked.get(w).getTotal_cost();
                }
            }
            if (!sho.contains(show_booked.get(i).getNumber_flight())){
                System.out.println("flight"+flight+"->"+revenue);
                sho.add(show_booked.get(i).getNumber_flight());
            }
            revenue=0;
        }
    }

    public void Edit(int id, int update_tickets) throws IOException, SQLException {
        for (int i = 0; i < show_booked.size(); i++) {
            if (show_booked.get(i).getBookingId() == id) {
                Booking booking = show_booked.get(i);
                if (show_booked.get(i).getCount_tickets() < update_tickets) {
                    if (manger.check_tik(show_booked.get(i).getNumber_flight(), update_tickets)) {
                        int yu=update_tickets-show_booked.get(i).getCount_tickets();
                        manger.handel_book(show_booked.get(i).getNumber_flight(), booking.getCount_tickets());
                        manger.book1(show_booked.get(i).getNumber_flight(), update_tickets );
                        booking.Caulate_total_cost(update_tickets, show_booked.get(i).getIn());
                        show_booked.get(i).setCount_tickets(update_tickets);
                        System.out.println("Booking Confirmed");
                        show_booked.get(i).setTotal_cost(booking.getTotal_cost());
                        booking.display_book();
                        Trip trip=new Trip(show_booked.get(i).getNumber_flight());
                        file.update_bookingColumn(id, "count_tickets",update_tickets);
                        file.update_bookingColumn(id, "total_cost", booking.getTotal_cost());
                    }
                } else {
                    int yu=show_booked.get(i).getCount_tickets()-update_tickets;
                    manger.handel_book(show_booked.get(i).getNumber_flight(), booking.getCount_tickets());
                    manger.book1(show_booked.get(i).getNumber_flight(), update_tickets);
                    booking.Caulate_total_cost(update_tickets, Booking.getIn());
                    show_booked.get(i).setCount_tickets(update_tickets);
                    System.out.println("Booking Confirmed");
                    show_booked.get(i).setTotal_cost(booking.getTotal_cost());
                    booking.display_book();
                    Trip trip=new Trip(show_booked.get(i).getNumber_flight());
                    file.update_bookingColumn(id, "count_tickets",update_tickets);
                    file.update_bookingColumn(id, "total_cost", booking.getTotal_cost());
                }
            }
        }
    }

    public void Edit_Id(int id, int kl) throws IOException, SQLException {
        for (int i = 0; i < show_booked.size(); i++) {
            if (show_booked.get(i).getBookingId() == id) {
                Booking booking = show_booked.get(i);
                booking.Caulate_total_cost(show_booked.get(i).getCount_tickets(),kl);
                System.out.println("Booking Confirmed");
                show_booked.get(i).setTotal_cost(booking.getTotal_cost());
                booking.display_book();
                file.update_bookingColumn(id, "total_cost",booking.getTotal_cost()) ;
            }
        }
    }
    public void most_3_booked() {
        int flight,flight2,count = 0;
        ArrayList<Integer> sho = new ArrayList<>();
        Map<Integer, Integer> flightBookings = new HashMap<>();
        for (int i=0;i<show_booked.size();i++) {
            flight=show_booked.get(i).getNumber_flight();
            for (int w=i;w<show_booked.size();w++){
                flight2=show_booked.get(w).getNumber_flight();
                if (flight==flight2 && (!sho.contains(show_booked.get(i).getNumber_flight()))){
                    count += 1;
                }
            }
            if (!sho.contains(show_booked.get(i).getNumber_flight())){
                sho.add(show_booked.get(i).getNumber_flight());
                flightBookings.put(show_booked.get(i).getNumber_flight(),count);
            }
            count=0;
        }
        List<Map.Entry<Integer, Integer>> lit = new ArrayList<>(flightBookings.entrySet());
        lit.sort((e1, e2) -> e2.getValue() - e1.getValue());
        for (int i = 0; i < 3 && i < lit.size(); i++) {
            System.out.println("Flight: " + lit.get(i).getKey() +
                    " is " + lit.get(i).getValue() + " bookings");
        }

    }
    public void by_Range(int a,int b,int c){
        manger.Search_by_Range(a,b,c);
    }
    public void delete_flight(int h) throws IOException, SQLException {
          for (Trip i:manger.shows){
              if (i.getTrip_number()==h){
                  manger.shows.remove(i);
                  break;
              }
          }
        file.deleteTrip(h);

          trips();
    }
    public void Edit_flight_seats(int seat,int number_flight) throws IOException, SQLException {
        for (Trip i:manger.shows){
            if (i.getTrip_number()==number_flight){
                i.setAvailable(seat);
            }
        }
        file.updateTripColumn(number_flight, "available", seat);
        trips();
    }
    public void Edit_flight_price(int cost,int number_flight,int clas) throws IOException, SQLException {
        clas-=1;
        for (Trip i:manger.shows){
            if (i.getTrip_number()==number_flight){
                i.getPrice()[clas]=cost;
            }
        }
        String[] priceColumns = {"price_economy", "price_business", "price_first"};
        file.updateTripColumn(number_flight, priceColumns[clas], cost);
        trips();
    }
    public void Edit_flight_date(int day,int month,int yaer,int number_flight) throws IOException, SQLException {
        for (Trip i:manger.shows){
            if (i.getTrip_number()==number_flight){
                i.setDay(day);
                i.setMonth(month);
                i.setYear(yaer);
            }
        }
        file.updateTripColumn(number_flight, "day", day);
        file.updateTripColumn(number_flight, "month", month);
        file.updateTripColumn(number_flight, "year", yaer);
        trips();
    }
}