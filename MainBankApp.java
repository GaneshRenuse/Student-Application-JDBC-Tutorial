import java.util.*;
import java.sql.*;
import java.awt.*;

class BankApp {
    String URL;
    String Username;
    String Password;
    Connection cobj;

    BankApp() throws Exception {
        this.URL = "jdbc:mariadb://localhost:3306/bank";
        this.Username = "root";
        this.Password = "";
        this.cobj = DriverManager.getConnection(URL, Username, Password);
    }

    public void showdetails() throws Exception {
        String SelectQuery = "select * from student";
        Statement sobj = cobj.createStatement();
        ResultSet robj = sobj.executeQuery(SelectQuery);

        System.out.println("Rid\tName\tCity\tMarks");
        while (robj.next()) {
            System.out.print(robj.getInt("id") + "\t");
            System.out.print(robj.getString("name") + "\t");
            System.out.print(robj.getString("city") + "\t");
            System.out.print(robj.getInt("marks") + "\t");
            System.out.println(" ");
        }
    }

    public Boolean Authenticate(int accn, int pass) throws Exception {
        String query = "SELECT * FROM accounts WHERE account_no = ? and password = ?";
        PreparedStatement statement = cobj.prepareStatement(query);
        statement.setInt(1, accn);
        statement.setInt(2, pass);

        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public void CreateAccount() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name : ");
        String name = sc.nextLine();
        System.out.println("Enter your age : ");
        int age = sc.nextInt();
        System.out.println("Enter a good 4 digit password for account : ");
        int pword = sc.nextInt();;

        int acn = GenerateAccountNo();
        double balance = 0.00;

        String query = "insert into accounts values(?, ?, ?, ?, ?);";
        PreparedStatement statement = cobj.prepareStatement(query);

        statement.setInt(1, acn);
        statement.setInt(2, pword);
        statement.setString(3, name);
        statement.setInt(4, age);
        statement.setDouble(5, balance);

        int rowsaffected = statement.executeUpdate();

        if (rowsaffected > 0)
        {
            System.out.println("|| Account created successfully || your account number is : " + acn + " and password : " + pword + " ||");
        }

        sc.close();
    }

    public void Mainfunc(int accn, int pass) throws Exception {

    }

    public static int GenerateAccountNo() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }
}

public class MainBankApp {
    public static void main(String[] args) throws Exception {
        BankApp Bobj = new BankApp(); // creating object of BankApp class
        Scanner Reader = new Scanner(System.in);

        System.out.println("- - - Welcome to Bank Application - - -");
        System.out.println("         [Login] || [Register]  ");
        System.out.println("         [Enter 1] || [Enter 2] ");

        int choice = Reader.nextInt();

        switch (choice){
            case 1:
                System.out.println("Enter Account no : ");
                int accn = Reader.nextInt();
                System.out.println("Enter Password : ");
                int pass = Reader.nextInt();
                if (Bobj.Authenticate(accn, pass)){
                    Bobj.Mainfunc(accn, pass);
                }
                else {
                    System.out.println("Wrong Account number or Password | Try again!!!");
                }
                break;
            case 2:
                Bobj.CreateAccount();
                break;
            default:
                System.out.println("Error 404!!");
                break;
        }

        Reader.close();
    }
}
