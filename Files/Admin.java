import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends Trip {
    ArrayList<Trip> edit = new ArrayList<>();
    private int number;
    public Admin(){
    }

    public void ad_flight() throws IOException, SQLException {
       edit_trips(edit);
     }
}
