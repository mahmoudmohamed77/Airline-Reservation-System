import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login_System {
 /*   private String name_user;
    private int password_user;
    private String name_admin;
    private int password_admin;

  */
    private String name;
    private int password;
    private int role;
    Scanner input = new Scanner(System.in);
    DBManager file=new DBManager();
    ArrayList<Login_System> admin_login = new ArrayList<>();
    ArrayList<Login_System> user_login = new ArrayList<>();
    public Login_System(){}
    public Login_System(String name_in,int pass,int roe){
        name=name_in;
        password=pass;
        role=roe;
    }
    public void setAdmin( ArrayList<Login_System> login){
        this.admin_login = login;
    }
    public void set_user( ArrayList<Login_System> login){
        user_login.clear();
        user_login = login;
    }
    public void print(){
        for (Login_System login:user_login){
            System.out.println(login.getName()+login.getPassword());
        }
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public boolean check_login_admin(String name , int o) throws IOException, SQLException {
        setAdmin(file.load_login_admin());
        for (Login_System l :admin_login){
            if(l.name.equals(name)&&l.password==o){
                return true;
            }
        }
        return false;
    }
    public boolean check_login_user(String name , int o) throws IOException, SQLException {
        set_user(file.load_login_user());
        for (Login_System l :user_login){
            if(l.name.equals(name)&&l.password==o){
                return true;
            }
        }
        return false;
    }

    public void register_admin(String name , int pass) throws IOException, SQLException {
        Login_System admin=new Login_System(name,pass,1);
        admin_login.add(admin);
        System.out.println("correct register");
        file.save_login(name,pass,1);

    }
    public void register_user(String name , int pass) throws IOException, SQLException {
        Login_System user=new Login_System(name,pass,2);
        user_login.add(user);
        System.out.println("correct register");
        file.save_login(name,pass,2);

    }
    public int login_admin(int ch) throws IOException, SQLException {
            System.out.println("Enter name:");
            String name;
            name=input.next();
            System.out.println("Enter password");
            int pass;
            pass=input.nextInt();
            if (check_login_admin(name,pass)){
                ch=1;
            }
            else {
                ch=0;
            }
        return ch;
    }
    public int login_user(int ch) throws IOException, SQLException {
        System.out.println("Enter name:");
        String name;
        name=input.next();
        System.out.println("Enter password");
        int pass;
        pass=input.nextInt();
        if (check_login_user(name,pass)){
            ch=1;
            setName(name);
            setPassword(pass);

        }
        else {
            ch=0;
        }
        return ch;
    }
    public void input_register_admin() throws IOException, SQLException {
        System.out.println("Enter name:");
        String name;
        name=input.next();
        System.out.println("Enter password");
        int pass;
        pass=input.nextInt();
        if (check_login_admin(name,pass)){
            System.out.println("already register");
        }
        else {
            register_admin(name,pass);
        }
    }
    public void delete_acc(String name) throws IOException, SQLException {
        user_login = file.load_login_user();
        admin_login = file.load_login_admin();
        for (int i = 0; i < user_login.size(); i++) {
                if (user_login.get(i).name.equals(name)){
                    Login_System jk=user_login.get(i);
                 user_login.remove(jk);
                 break;
                }
            }
            file.save_login_all(user_login,admin_login);

    }
    public void input_register_user() throws IOException, SQLException {
        System.out.println("Enter name:");
        String name;
        name=input.next();
        System.out.println("Enter password");
        int pass;
        pass=input.nextInt();
        if (check_login_user(name,pass)){
            System.out.println("already register");
        }
        else {
            register_user(name,pass);
        }
    }
}
