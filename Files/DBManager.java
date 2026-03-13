import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/airline";
        String user = "root";
        String password = "root123";
        return DriverManager.getConnection(url, user, password);
    }
    ArrayList<Trip> final_trips = new ArrayList<>();


    public void saveBookings(ArrayList<Booking> bookings) throws SQLException, IOException {

        Connection conn = connect();


        String sql = "INSERT INTO booking_new (name_passenger, flight_number, count_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        for (Booking b : bookings) {
            ps.setString(1, b.getName_passenger());
            ps.setInt(2, b.getNumber_flight());
            ps.setInt(3, b.getCount_tickets());
            ps.setInt(4, b.getTotal_cost());
            ps.setString(5, b.getBookingDate());


            ps.executeUpdate();


            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                b.setBookingId1(generatedId);
            }
            rs.close();
        }


        ps.close();
        conn.close();
    }
    public int jok()throws SQLException {
            int lastId = 0;
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(booking_id) AS last_id FROM booking_new");

            if (rs.next()) {
                lastId = rs.getInt("last_id");
            }

            rs.close();
            stmt.close();
            conn.close();
            return lastId;
        }

    public ArrayList<Booking> loadBookings() throws SQLException {
        ArrayList<Booking> bookings = new ArrayList<>();
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM booking_new");

        while (rs.next()) {
            Booking b = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("name_passenger"),
                    rs.getInt("flight_number"),
                    rs.getInt("count_tickets"),
                    rs.getInt("total_cost"),
                    rs.getString("booking_date")
            );
            bookings.add(b);
        }

        rs.close();
        stmt.close();
        conn.close();
        return bookings;
    }
    public void update_bookingColumn(int  booking_id , String column, int value) throws SQLException {
        Connection conn = connect();
        String sql = "UPDATE booking_new SET " + column + " = ? WHERE  booking_id  = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, value);
        ps.setInt(2,  booking_id );
        ps.executeUpdate();
        ps.close();
        conn.close();
    }




    public ArrayList<Trip> loadTrips() throws SQLException {
        ArrayList<Trip> trips = new ArrayList<>();
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM flights");

        while (rs.next()) {
            int[] prices = {
                    rs.getInt("price_economy"),
                    rs.getInt("price_business"),
                    rs.getInt("price_first")
            };
            Trip t = new Trip(
                    rs.getInt("trip_number"),
                    rs.getString("from_city"),
                    rs.getString("to_city"),
                    prices,
                    rs.getInt("available"),
                    rs.getInt("day"),
                    rs.getInt("month"),
                    rs.getInt("year")
            );
            trips.add(t);
        }

        rs.close();
        stmt.close();
        conn.close();
        return trips;
    }

    public void save_trips(ArrayList<Trip> tps) throws SQLException {
        Connection conn = connect();
        String sql = "INSERT INTO flights (trip_number, from_city, to_city, price_economy, price_business, price_first, available, day, month, year) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "from_city=VALUES(from_city), to_city=VALUES(to_city), price_economy=VALUES(price_economy), price_business=VALUES(price_business), " +
                "price_first=VALUES(price_first), available=VALUES(available), day=VALUES(day), month=VALUES(month), year=VALUES(year)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (Trip b : tps) {
            ps.setInt(1, b.getTrip_number());
            ps.setString(2, b.getFrom());
            ps.setString(3, b.getTo());
            ps.setInt(4, b.getPrice()[0]);
            ps.setInt(5, b.getPrice()[1]);
            ps.setInt(6, b.getPrice()[2]);
            ps.setInt(7, b.getAvailable());
            ps.setInt(8, b.getDay());
            ps.setInt(9, b.getMonth());
            ps.setInt(10, b.getYear());
            ps.executeUpdate();
        }

        ps.close();
        conn.close();
    }

    public void edit_trips(ArrayList<Trip> edit) throws SQLException {
        Connection conn = connect();
        String sql = "INSERT INTO flights (trip_number, from_city, to_city, price_economy, price_business, price_first, available, day, month, year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (Trip b : edit) {
            ps.setInt(1, b.getTrip_number());
            ps.setString(2, b.getFrom());
            ps.setString(3, b.getTo());
            ps.setInt(4, b.getPrice()[0]);
            ps.setInt(5, b.getPrice()[1]);
            ps.setInt(6, b.getPrice()[2]);
            ps.setInt(7, b.getAvailable());
            ps.setInt(8, b.getDay());
            ps.setInt(9, b.getMonth());
            ps.setInt(10, b.getYear());
            ps.executeUpdate();
        }

        ps.close();
        conn.close();
    }


    public void save_login(String name, int pass, int role) throws SQLException {
        Connection conn = connect();
        String sql = "INSERT INTO login_system (username, password, role) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, pass);
        ps.setInt(3, role);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void save_login_all(ArrayList<Login_System> use, ArrayList<Login_System> ad) throws SQLException {
        Connection conn = connect();
        String sql = "INSERT INTO login_system (username, password, role) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (Login_System l : use) {
            ps.setString(1, l.getName());
            ps.setInt(2, l.getPassword());
            ps.setInt(3, l.getRole());
            ps.executeUpdate();
        }

        for (Login_System l : ad) {
            ps.setString(1, l.getName());
            ps.setInt(2, l.getPassword());
            ps.setInt(3, l.getRole());
            ps.executeUpdate();
        }

        ps.close();
        conn.close();
    }

    public ArrayList<Login_System> load_login_admin() throws SQLException {
        ArrayList<Login_System> admin = new ArrayList<>();
        Connection conn = connect();
        String sql = "SELECT * FROM login_system WHERE role = 1";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Login_System l = new Login_System(
                    rs.getString("username"),
                    rs.getInt("password"),
                    rs.getInt("role")
            );
            admin.add(l);
        }

        rs.close();
        stmt.close();
        conn.close();
        return admin;
    }

    public ArrayList<Login_System> load_login_user() throws SQLException {
        ArrayList<Login_System> user = new ArrayList<>();
        Connection conn = connect();
        String sql = "SELECT * FROM login_system WHERE role = 2";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Login_System l = new Login_System(
                    rs.getString("username"),
                    rs.getInt("password"),
                    rs.getInt("role")
            );
            user.add(l);
        }

        rs.close();
        stmt.close();
        conn.close();
        return user;
    }
    public void deleteTrip(int tripNumber) throws SQLException {
        Connection conn = connect();
        String sql = "DELETE FROM flights WHERE trip_number = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tripNumber);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    public void deletebook(int id) throws SQLException {
        Connection conn = connect();
        String sql = "DELETE FROM booking_new WHERE booking_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    public void saveBooking_one(Booking b) throws SQLException, IOException {

        Connection conn = connect();

        String sql = "INSERT INTO booking_new (name_passenger, flight_number, count_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, b.getName_passenger());
        ps.setInt(2, b.getNumber_flight());
        ps.setInt(3, b.getCount_tickets());
        ps.setInt(4, b.getTotal_cost());
        ps.setString(5, b.getBookingDate());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int generatedId = rs.getInt(1);
            b.setBookingId1(generatedId);
        }
        rs.close();

        ps.close();
        conn.close();
    }

    public void updateTripColumn(int tripNumber, String column, int value) throws SQLException {
        Connection conn = connect();
        String sql = "UPDATE flights SET " + column + " = ? WHERE trip_number = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, value);
        ps.setInt(2, tripNumber);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}