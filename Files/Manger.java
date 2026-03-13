import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
public class Manger  {
    ArrayList<Trip> shows = new ArrayList<Trip>();
    Trip yu=new Trip();

    public Trip getYu() {
        return yu;
    }
    public Manger() throws IOException, SQLException {
        for (Trip t : getYu().loadTrips()) {
            shows.add(new Trip(t.getTrip_number(), t.getFrom(), t.getTo(), t.getPrice(), t.getAvailable(), t.getDay(), t.getMonth(), t.getYear()));
        }
    }
    public void refreshTrips() throws IOException, SQLException {
        shows.clear();
        for (Trip t : getYu().loadTrips()) {
            shows.add(new Trip(t.getTrip_number(), t.getFrom(), t.getTo(), t.getPrice(), t.getAvailable(), t.getDay(), t.getMonth(), t.getYear()));
        }
        System.out.println("Trips in file: " + getYu().loadTrips().size());
    }
    public void display() {
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s %-5s %-6s\n",
                "Number", "From", "To",
                "Economy", "Business", "First",
                "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Trip t : shows) {
            int[] prices = t.getPrice();

            System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d %-5d %-6d\n",
                    t.getTrip_number(),
                    t.getFrom(),
                    t.getTo(),
                    prices[0],
                    prices[1],
                    prices[2],
                    t.getAvailable(),
                    t.getDay(),
                    t.getMonth(),
                    t.getYear());
        }
    }


    public void display_search (String s1, String s2) {
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s %-5s %-6s\n",
                "Number", "From", "To",
                "Economy", "Business", "First",
                "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Trip t : shows) {
            if (t.getFrom().equals(s1) && t.getTo().equals(s2)) {
                int[] prices = t.getPrice();

                System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d %-5d %-6d\n",
                        t.getTrip_number(),
                        t.getFrom(),
                        t.getTo(),
                        prices[0],
                        prices[1],
                        prices[2],
                        t.getAvailable(),
                        t.getDay(),
                        t.getMonth(),
                        t.getYear());

            }
        }
    }
    public void display_sort_day_month(){
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s %-5s %-6s\n",
                "Number", "From", "To",
                "Economy", "Business", "First",
                "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        ArrayList<Trip> sot = new ArrayList<Trip>();
        for (Trip t : shows) {
            sot.add(t);
        }
        sot.sort((b1, b2) -> b1.getDay() - b2.getDay());
        sot.sort((b1, b2) -> b1.getMonth() - b2.getMonth());
        for (Trip t : sot ){
            int[] prices = t.getPrice();
            System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d %-5d %-6d\n",
                    t.getTrip_number(),
                    t.getFrom(),
                    t.getTo(),
                    prices[0],
                    prices[1],
                    prices[2],
                    t.getAvailable(),
                    t.getDay(),
                    t.getMonth(),
                    t.getYear());
        }
    }
    public void display_sort_price(int grade){
        grade-=1;
        int number=grade;
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s %-5s %-6s\n",
                "Number", "From", "To",
                "Economy", "Business", "First",
                "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        ArrayList<Trip> sot = new ArrayList<Trip>();
        for (Trip t : shows) {
            sot.add(t);
        }

        sot.sort((b1, b2) -> b1.getPrice()[number] - b2.getPrice()[number]);
        for (Trip t : sot ){
            int[] prices = t.getPrice();
            System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d %-5d %-6d\n",
                    t.getTrip_number(),
                    t.getFrom(),
                    t.getTo(),
                    prices[0],
                    prices[1],
                    prices[2],
                    t.getAvailable(),
                    t.getDay(),
                    t.getMonth(),
                    t.getYear());
        }
    }
    public void display_sort_available(){
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s %-5s %-6s\n",
                "Number", "From", "To",
                "Economy", "Business", "First",
                "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        ArrayList<Trip> sot = new ArrayList<Trip>();
        for (Trip t : shows) {
            sot.add(t);
        }
        sot.sort((b1, b2) -> b2.getAvailable() - b1.getAvailable());
        for (Trip t : sot ){
            int[] prices = t.getPrice();
            System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d %-5d %-6d\n",
                    t.getTrip_number(),
                    t.getFrom(),
                    t.getTo(),
                    prices[0],
                    prices[1],
                    prices[2],
                    t.getAvailable(),
                    t.getDay(),
                    t.getMonth(),
                    t.getYear());
        }
    }

    public int get_ave ( int y){
        for (Trip t : shows) {
            if (t.getTrip_number() == y) {
                return t.getAvailable();
            }
        }
        return 0;
    }
    public boolean check_tik ( int filght, int tick){
        for (Trip t : shows) {
            if (t.getTrip_number() == filght) {
                int g = t.getAvailable();
                g = g - tick;
                if (g < 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean check_flight ( int o){
        for (Trip t : shows) {
            if (t.getTrip_number() == o) {
                return true;

            }
        }
        return false;
    }
    public void display_search_by_name_date (String s1, String s2,int day, int month, int year) {
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s %-5s %-6s\n",
                "Number", "From", "To",
                "Economy", "Business", "First",
                "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Trip t : shows) {
            if (t.getFrom().equals(s1) && t.getTo().equals(s2)) {
                if (t.getDay() == day && t.getMonth() == month && t.getYear() == year) {

                    int[] prices = t.getPrice();

                    System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d %-5d %-6d\n",
                            t.getTrip_number(),
                            t.getFrom(),
                            t.getTo(),
                            prices[0],
                            prices[1],
                            prices[2],
                            t.getAvailable(),
                            t.getDay(),
                            t.getMonth(),
                            t.getYear());

                }
            }
        }
    }
    public void display_search_by_date ( int day, int month, int year) {
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s %-5s %-6s\n",
                "Number", "From", "To",
                "Economy", "Business", "First",
                "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Trip t : shows) {
            if (t.getDay() == day && t.getMonth() == month && t.getYear() == year) {
                int[] prices = t.getPrice();

                System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d %-5d %-6d\n",
                        t.getTrip_number(),
                        t.getFrom(),
                        t.getTo(),
                        prices[0],
                        prices[1],
                        prices[2],
                        t.getAvailable(),
                        t.getDay(),
                        t.getMonth(),
                        t.getYear());

            }
        }
    }

    public Trip book ( int number_flight, int count_tick){
        for (Trip t : shows) {
            if (t.getTrip_number() == number_flight) {
                return t;
            }
        }
        return null;
    }
    public Trip book1 ( int number_filb, int count_tick){
        for (Trip t : shows) {
            if (t.getTrip_number() == number_filb) {
                int k = t.getAvailable();
                k = k - count_tick;
                if (k < 0) {
                    k = 0;

                }
                t.setAvailable(k);
                return t;
            }
        }
        return null;
    }
    public void handel_book ( int numberfilb, int countick){
        for (Trip t : shows) {
            if (t.getTrip_number() == numberfilb) {
                int y = t.getAvailable();
                y = y + countick;
                t.setAvailable(y);
            }
        }
    }
    public void Search_by_Range(int less,int more,int grade){
        grade-=1;
        System.out.printf("%-10s %-12s %-12s %-10s %-10s %-10s %-10s %-5s \n",
                "Number", "From", "To",
                "Price", "Available", "Day", "Month", "Year");

        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Trip t : shows) {
            if (t.getPrice()[grade] >= less && t.getPrice()[grade] <= more ) {
                int[] prices = t.getPrice();
                System.out.printf("%-10d %-12s %-12s %-10d %-10d %-10d %-10d %-5d\n",
                        t.getTrip_number(),
                        t.getFrom(),
                        t.getTo(),
                        prices[grade],
                        t.getAvailable(),
                        t.getDay(),
                        t.getMonth(),
                        t.getYear());

            }
        }
    }

    public Trip getTripByNumber(int numberFlight) {
        for (Trip y:shows){
            if (y.getTrip_number()==numberFlight){
                return y;
            }
        }
        return null;
    }
}
 /*  public Trip Invention(){
 Random random = new Random();
        int i1=0,i2=0,number=0,number2=0,number3=0,day=0,month=0,set=0;
        i1=random.nextInt(10);
        i2=random.nextInt(10);
        while (i2==i1){i2=random.nextInt(10);}
        number = ThreadLocalRandom.current().nextInt(5000, 10000);
        number2= ThreadLocalRandom.current().nextInt(10000, 15000);
        number3=ThreadLocalRandom.current().nextInt(15000, 20000);
        while (number>number2){
            number2= ThreadLocalRandom.current().nextInt(10000, 15000);
        }
        while (number2>number3){
            number3=ThreadLocalRandom.current().nextInt(15000, 20000);
        }
        day=ThreadLocalRandom.current().nextInt(1, 31);;
        month=ThreadLocalRandom.current().nextInt(1, 13);;
        set=ThreadLocalRandom.current().nextInt(100, 1000);
        ++index;
        return new Trip(index,cities[i1],cities[i2],new int[]{number, number2, number3},set,day,month,2026);
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

    }
   */
 /*  String[] cities = {
            "CAIRO","DUBAI","PARIS","LONDON",
            "JEDDAH","NEW YORK","ROME",
            "BERLIN","DOHA","RIYADH"
    };
   */
/*
    public void change_shows(int number_flight, int count_tick) {
        for (Trip t : shows) {
            if (t.getTrip_number() == number_flight) {
                int k = t.getAvailable();
                k = k - count_tick;
                if (k < 0) {
                    k = 0;

                }
                t.setAvailable(k);
            }
        }
    }
     */