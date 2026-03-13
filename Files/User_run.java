import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class User_run {
    private String name_user;
    private int password_user;
    private Manger manger;
    Scanner input = new Scanner(System.in);
    private BookingService booking_one;
    int op=-1;
    Login_System login =new Login_System();

    public User_run(BookingService booking,Manger manger) throws IOException {
        this.manger = manger;
        booking_one = booking;
    }

    public void run_user() throws IOException, SQLException {
        String name_passenger;
        while (true) {
            if( login.login_user(op)==1){
                name_passenger=login.getName();
                break;
            }
            else {
                System.out.println("wrong login\nEnter 1 for login again or 2 for register");
                int y;
                y=input.nextInt();
                if (y==2){
                    login.input_register_user();
                }
            }
        }
        while (true) {
            System.out.println("1.show");
            System.out.println("2.book");
            System.out.println("3.cancle");
            System.out.println("4.View My Booking");
            System.out.println("5.Edit Booking");
            System.out.println("0.exit");
            int n;
            n = input.nextInt();
            if (n == 1) {
                System.out.println("choose \n 1.Search Flight by Destination \n 2.All Flights\n3.Search by Price Range\n4.Most 3 flight");
                int w;
                w = input.nextInt();
                if (w == 1) {
                    String e1, e2;
                    System.out.println("Enter from your country and to any country ");
                    e1 = input.next();
                    e1 = e1.toUpperCase();
                    e2 = input.next();
                    e2 = e2.toUpperCase();
                    System.out.println("Enter\n1.If you want a specific date \n2.or not ");
                    int o;
                    o = input.nextInt();
                    int a = 0, b = 0, c = 0;
                    if (o == 1) {
                        System.out.println("Enter Day:");
                        a = input.nextInt();
                        System.out.println("Enter Month:");
                        b = input.nextInt();
                        System.out.println("Enter Year:");
                        c = input.nextInt();
                    }
                    booking_one.show(w, e1, e2, o, a, b, c);
                } else if (w == 2) {
                    System.out.println("Enter\n1.If you want a specific date \n2.Or Not\n3.Sort by Price\n4.Sort by Date\n5.Sort by Available Seats ");
                    int choose;
                    choose = input.nextInt();
                    int aa = 0, bb = 0, cc = 0;
                    if (choose == 1) {
                        System.out.println("Enter Day:");
                        aa = input.nextInt();
                        System.out.println("Enter Month:");
                        bb = input.nextInt();
                        System.out.println("Enter Year:");
                        cc = input.nextInt();
                    }
                    if (choose == 1 || choose == 2) {
                        booking_one.show_all_no_names(aa, bb, cc, choose);
                    }
                    if (choose == 3) {
                        System.out.println("Choose the class you want to sort\n1. Economy\n2. Business\n3. First");
                        int cho;
                        cho = input.nextInt();
                        manger.display_sort_price(cho);
                    } else if (choose == 4) {
                        manger.display_sort_day_month();
                    } else if (choose == 5) {
                        manger.display_sort_available();
                    }
                } else if (w == 3) {
                    System.out.println("Enter range price to Search");
                    int less, most, grade;
                    System.out.println("At least:");
                    less = input.nextInt();
                    System.out.println("At most:");
                    most = input.nextInt();
                    System.out.println("Enter Choose the class you will search\n1. Economy\n2. Business\n3. First");
                    grade = input.nextInt();
                    booking_one.by_Range(less, most, grade);
                } else {
                    booking_one.most_3_booked();
                }
            } else if (n == 2) {
                int number_flight, count_tickets;
                System.out.println("Enter the flight number and the number of tickets you want:");
                number_flight = input.nextInt();
                count_tickets = input.nextInt();
                while (!manger.check_flight(number_flight)) {
                    System.out.println("please enter validation number for number_flight");
                    int d;
                    d = input.nextInt();
                    number_flight = d;
                }
                while (!manger.check_tik(number_flight, count_tickets)) {
                    System.out.println("please enter validation number for count tickets maximum limit is " + manger.get_ave(number_flight));
                    int ne;
                    ne = input.nextInt();
                    count_tickets = ne;
                }
                System.out.println("Enter Choose the class you will be riding\n1. Economy\n2. Business\n3. First");
                int grade;
                grade = input.nextInt();
                booking_one.display_book(name_passenger, number_flight, count_tickets, grade);
                System.out.println("Enter \n 1. For confirmation book  \n 2. for cancel book ");
                int choose;
                choose = input.nextInt();
                booking_one.display_book1(choose, name_passenger, number_flight, count_tickets, grade);
            } else if (n == 3) {
                System.out.println("Please Enter your Id and number flight for cancel ");
                int i;
                i=input.nextInt();
                String a = name_passenger;
                int number_flight = input.nextInt();
                booking_one.cancel_book(a,number_flight,i);
            } else if (n == 4) {
                String name;
                name = name_passenger;
                booking_one.show_my(name);
            } else if (n == 5) {
                System.out.println("Enter Id:");
                int Id, ticket;
                Id = input.nextInt();
                System.out.println("Enter choose\n1.change ticket\n2.change class");
                int i;
                i = input.nextInt();
                if (i == 1) {
                    System.out.println("Enter tick:");
                    ticket = input.nextInt();
                    booking_one.Edit(Id, ticket);
                } else {
                    System.out.println("Enter grade:");
                    ticket = input.nextInt();
                    booking_one.Edit_Id(Id, ticket);
                }
            } else if (n == 0) {
                booking_one.trips();
                break;
            }
        }
    }
}
