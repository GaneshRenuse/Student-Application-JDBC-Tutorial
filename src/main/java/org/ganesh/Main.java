package org.ganesh;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Students studentObject = new Students();
        System.out.println("Hello, World! Welcome to the Student Management System");

        studentObject.InsertDetails();
        studentObject.ShowDetails();
        studentObject.SearchStudent();
    }
}