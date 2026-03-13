public class Trip extends DBManager {
   private int trip_number;
    private String from;
    private String to;
    private int[] price;
    public String[] grads={"Economy", "Business", "First"};
    private int available;
    private int day;
    private int month;
    private int year;
    public Trip(int number){
        this.trip_number=number;
    }
    public Trip(int num,String in,String out,int [] cost,int avb,int da,int monh ,int yar){
        trip_number=num;
        from=in;
        to=out;
        price=cost;
        available=avb;
        day=da;
        month=monh;
        year=yar;}
    public Trip(){}
    @Override
    public String toString() {
        return "trip=" + trip_number
                +",from="+from+"->"+to
                +  ", price=" + price
                + ", seats=" + available
                + ", date: "+day+"/"+month+"/"+year  ;
    }
    public int getTrip_number() {
        return trip_number;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public int[] getPrice() {
       return price ;
    }
    public void setPrice(int[] price) {
        this.price = price;
    }
    public int getAvailable() {
        return available;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;}
    public void setAvailable(int available) {
        this.available = available;
    }

    public String[] getGrads() {
        return grads;
    }
}
