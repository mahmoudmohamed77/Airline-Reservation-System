import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Admin_run {
    Admin Adm = new Admin();
    Login_System login =new Login_System();
    private Manger manger;
    Scanner input = new Scanner(System.in);
    private BookingService booking_one;
    int bo=-1;

    public Admin_run(BookingService booking,Manger manger) throws IOException {
        this.manger = manger;
        booking_one = booking;
    }
    public void Admin_start() throws IOException, SQLException {
        while (true) {
           if( login.login_admin(bo)==1){
               break;
           }
           else {
               System.out.println("wrong login\nEnter 1 for login again or 2 for register");
               int y;
               y=input.nextInt();
               if (y==2){
                   login.input_register_admin();
               }
           }
        }
        while (true) {
            System.out.println("1.Add");
            System.out.println("2.veiw");
            System.out.println("3.cancle");
            System.out.println("4.Show Total Revenue");
            System.out.println("5.View All Bookings");
            System.out.println("6.Edit flight");
            System.out.println("7.Delet Account");
            System.out.println("0.exit");
            int n2;
            n2 = input.nextInt();
            if (n2 == 1) {
                int number_add;
                number_add = input.nextInt();
                int nm, pr4, pr5, pr6, day, month, year, ax;
                String fom, t;
                for (int i = 0; i < number_add; i++) {
                    System.out.println("number and ");
                    nm = input.nextInt();fom = input.next();fom = fom.toUpperCase();t = input.next();t = t.toUpperCase();pr4 = input.nextInt();pr5 = input.nextInt();pr6 = input.nextInt();ax = input.nextInt();day = input.nextInt();month = input.nextInt();year = input.nextInt();
                    Adm.edit.add(new Trip(nm, fom, t, new int[]{pr4, pr5, pr6}, ax, day, month, year));
                }
                Adm.ad_flight();
                manger.refreshTrips();
            } else if (n2 == 0) {
                break;
            } else if (n2 == 2) {
                manger.display();
            } else if (n2 == 4) {
                System.out.println("Enter 1.Total Revenue\n 2.Revenue by ever flight");
                int r;
                r = input.nextInt();
                if (r == 1) {
                    System.out.println("Total Revenue =" + booking_one.revenue());
                } else {
                    booking_one.revenue_by_one();
                }
            } else if (n2 == 5) {
                booking_one.show_all();
            }
            else if (n2 == 3) {
                System.out.println("Enter choose\n1.delet flight\n2.Cancel a passenger's flight book ");
                int q;
                q=input.nextInt();
                if(q==1){
                    System.out.println("Enter number flight for delete");
                    int number_delete;
                    number_delete=input.nextInt();
                    booking_one.delete_flight(number_delete);
                    manger.refreshTrips();
                }

               else if (q==2) {
                    System.out.println("Enter passenger 's id and number flight for delete");
                    int number_delete, id;
                    id = input.nextInt();
                    number_delete = input.nextInt();
                    booking_one.cancel_book_by_admin(id, number_delete);
                }
            }
            else if (n2 == 6) {
                System.out.println("enter 1.seats\n2.price for class\n3.date");
                int tos;
                tos=input.nextInt();
                if (tos==1){
                    System.out.println("enter seats and number");
                    int seats,number;
                    seats=input.nextInt();
                    number=input.nextInt();
                    booking_one.Edit_flight_seats(seats,number);
                    manger.refreshTrips();
                }
                else if (tos==2){
                    System.out.println("enter price and class and number");
                    int price,number,clas;
                    price=input.nextInt();
                    clas=input.nextInt();
                    number=input.nextInt();
                    booking_one.Edit_flight_price(price,number,clas);
                    manger.refreshTrips();
                }
                else {
                    System.out.println("enter date and number");
                    int day,month,year,number;
                    day=input.nextInt();
                    month=input.nextInt();
                    year=input.nextInt();
                    number=input.nextInt();
                    booking_one.Edit_flight_date(day,month,year,number);
                    manger.refreshTrips();
                }
            }
            else if (n2 == 7){
                login.print();
                System.out.println("Enter name Account");
                String name;
                name=input.next();
                login.delete_acc(name);
            }
        }
    }
}
