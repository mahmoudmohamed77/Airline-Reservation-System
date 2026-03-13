import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("welcome");
        Manger manger = new Manger();
        BookingService booking_one = new BookingService(manger);
        DBManager file = new DBManager();
        booking_one.setBookings(file.loadBookings());
        User_run user = new User_run(booking_one,manger);
        Admin_run admin = new Admin_run(booking_one, manger);
        Login_System login=new Login_System();
        login.setAdmin(file.load_login_admin());
        login.set_user(file.load_login_user());
        while (true) {
            login.setAdmin(file.load_login_admin());
            login.set_user(file.load_login_user());
            System.out.println("1.Ueser\n2.Admin\n3.out");
            int choose;
            choose = input.nextInt();
            if (choose == 1) {
                user.run_user();
            }
            else if (choose == 2) {
                admin.Admin_start();
            }
            else {
                break;
            }
        }
    }
}