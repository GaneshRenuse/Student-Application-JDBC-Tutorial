import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class BankApp
{
    String URL;
    String username;
    String password;
    Connection cobj;

    BankApp() throws Exception {
        this.URL = "jdbc:mariadb://localhost:3306/ppa51";
        this.username = "root";
        this.password = "";
        this.cobj = DriverManager.getConnection(URL, username, password);
    }

    public void showdetails() throws Exception
    {
        String SelectQuery = "select * from student";
        Statement sobj = cobj.createStatement();
        ResultSet robj = sobj.executeQuery(SelectQuery);

        System.out.println("Rid\tName\tCity\tMarks");
        while(robj.next())
        {
            System.out.print(robj.getInt("id") + "\t");
            System.out.print(robj.getString("name") + "\t");
            System.out.print(robj.getString("city") + "\t");
            System.out.print(robj.getInt("marks") + "\t");
            System.out.println(" ");
        }
    }

    public static void main(String[] args) throws Exception
    {
        BankApp ba = new BankApp();
        Scanner sc = new Scanner(System.in);

        System.out.println("- - - Welcome to Bank Application - - -");
        System.out.println("1) Create Account");
        System.out.println("2) Show Account Details");
        System.out.println("3) Check BankBalance");
        System.out.println("4) Withdraw Money");
        System.out.println("5) Deposit Money");
        System.out.println("6) Exit");

        System.out.println("Enter >> ");
        int ch = sc.nextInt();

        switch (ch)
        {
            case 1:
                System.out.println("func1");
                break;
            case 2:
                ba.showdetails();
                break;
            default :
                System.out.println("- - - Error !!! entered wrong input - - -");
                break;
        }
    }
}
