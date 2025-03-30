package org.ganesh;

import java.sql.*;
import java.util.*;

public class Students {
    String URL;
    String username;
    String password;
    Connection connectionObject;

    Students() throws Exception {
        this.URL = "jdbc:postgresql://localhost:5432/students";
        this.username = "postgres";
        this.password = "1234";
        this.connectionObject = DriverManager.getConnection(URL, username, password);
    }

    public void InsertDetails() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the student : ");
        String name = sc.nextLine();
        System.out.println("Enter the roll number of the student : ");
        int rollno = sc.nextInt();
        System.out.println("Enter the age of the student : ");
        int age = sc.nextInt();

        String query = "INSERT INTO student_details VALUES (?, ?, ?)";
        PreparedStatement statementObject = connectionObject.prepareStatement(query);
        statementObject.setInt(1, rollno);
        statementObject.setString(2, name);
        statementObject.setInt(3, age);

        statementObject.executeUpdate();

        System.out.println("Data inserted successfully!");

        statementObject.close();
        sc.close();
    }

    public void ShowDetails() throws Exception {
        String query = "SELECT * FROM student_details";
        Statement statementObject = connectionObject.createStatement();
        ResultSet resultObject = statementObject.executeQuery(query);

        while(resultObject.next()){
            System.out.println("Roll number : " + resultObject.getInt("rollno"));
            System.out.println("Name : " + resultObject.getString("name"));
            System.out.println("Age : " + resultObject.getInt("age"));
        }

        resultObject.close();
        statementObject.close();
    }

    public void SearchStudent() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the roll number of the student : ");
        int rollno = sc.nextInt();

        String query = "SELECT * FROM student_details WHERE rollno = ?";
        PreparedStatement statementObject = connectionObject.prepareStatement(query);
        statementObject.setInt(1, rollno);

        ResultSet resultObject = statementObject.executeQuery();
        if (resultObject.next()) {
            System.out.println("Roll number : " + resultObject.getInt("rollno"));
            System.out.println("Name : " + resultObject.getString("name"));
            System.out.println("Age : " + resultObject.getInt("age"));
        } else {
            System.out.println("Student not found!");
        }

        resultObject.close();
        statementObject.close();
        sc.close();
    }
}
