/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package studentmanagementsystem;

/**
 *
 * @author syed.shaziya
 */
import java.sql.*;
import java.util.Scanner;
public class StudentManagementSystem {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args)throws Exception {
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabasemanagement_system","root","rjss@_123456");
       Scanner sc=new Scanner(System.in);
      while(true){
          System.out.println("\n=== Student Data Management System ===");
          System.out.println("1. Add Student");
          System.out.println("2. View All Students");
          System.out.println("3.Update Student");
          System.out.println("4.Delete Student");
          System.out.println("5.Search Student (by ID or Name)");
          System.out.println("6.Exit");
          System.out.print("Enter your Choice:");
          int choice=sc.nextInt();
          sc.nextLine();
          
          
          switch(choice){
              case 1://Add Student
                  System.out.print("Enter ID:");
                  int id=sc.nextInt();
                  sc.nextLine();
                  System.out.print("Enter Name:");
                  String name=sc.nextLine();
                  System.out.print("Enter Department:");
                  String dept=sc.nextLine();
                  System.out.print("Enter Marks:");
                  int marks=sc.nextInt();
                  
                  
                  PreparedStatement insert=con.prepareStatement("INSERT INTO studentdetails VALUES(?, ?, ?, ?)");
                  insert.setInt(1,id);
                  insert.setString(2, name);
                  insert.setString(3, dept);
                  insert.setInt(4, marks);
                  insert.executeUpdate();
                  System.out.println("Student added Successfully.");
                  break;
              case 2://View All Details
                  Statement stmt=con.createStatement();
                  ResultSet rs=stmt.executeQuery("SELECT * FROM studentdetails");
                  System.out.print("\n Student Records:");
                  while(rs.next()){
                      System.out.println("ID:"+rs.getInt("id")+ 
                              " ,Name:"+rs.getString("name")+
                              ", Dept:"+rs.getString("department")+
                              ", Marks:"+rs.getInt("marks"));
                  }
        
              case 3://Update Student details
                  System.out.print("Enter ID to update:");
                  int uid=sc.nextInt();
                  sc.nextLine();
                  System.out.print("Enter new Name:");
                  String newName=sc.nextLine();
                  System.out.print("Enter new Department:");
                  String newDept=sc.nextLine();
                  System.out.print("Enter new Marks:");
                  int newMarks=sc.nextInt();
                  
                  
                  PreparedStatement update=con.prepareStatement("UPDATE studentdetails SET name=?, department=?, marks=? WHERE id=?");
                  update.setString(1, newName);
                  update.setString(2, newDept);
                  update.setInt(3, newMarks);
                  update.setInt(4, uid);
                  int rows=update.executeUpdate();
                  if(rows>0)
                      System.out.println("Student Updated Successfully.");
                  else
                      System.out.println("Student not found.");
                  
                 break;
              case 4://Delete Student Details
                  System.out.print("Enter ID to delete:");
                  int did=sc.nextInt();
                  PreparedStatement delete=con.prepareStatement("DELETE FROM studentdetails WHERE id=?");
                  delete.setInt(1, did);
                  int del=delete.executeUpdate();
                  if(del>0)
                      System.out.println("Student deleted Successfully.");
                  else
                      System.out.println("Student not found.");
                  break;
                  
              
              case 5://Search Student by ID or name
                  sc.nextLine();
                  System.out.print("Search by (1-ID/2-Name)");
                  int searchChoice=sc.nextInt();
                  sc.nextLine();
                  
                  if(searchChoice == 1){
                      System.out.print("Enter ID:");
                      int sid=sc.nextInt();
                      
                      PreparedStatement searchId=con.prepareStatement("SELECT * FROM studentdetails WHERE id=?");
                      searchId.setInt(1, sid);
                      ResultSet sidRs=searchId.executeQuery();
                  if(sidRs.next()){
                      System.out.println("\nstudentfound:");
                      System.out.println("ID:"+sidRs.getInt("id"));
                      System.out.println("Name:"+sidRs.getString("name"));
                      System.out.println("Department:"+sidRs.getString("department"));
                      System.out.println("Marks:"+sidRs.getInt("marks"));
                  }
                  else{
                      System.out.println("Student not found.");
                  }
                  
                  }
                  else if(searchChoice == 2){
                      System.out.print("Enter Name:");
                      String sname=sc.nextLine();
                      PreparedStatement searchName=con.prepareStatement("SELECT * FROM studentdetails WHERE name LIKE?");
                      searchName.setString(1,"%" + sname + "%");
                      ResultSet snameRs=searchName.executeQuery();
                      boolean found = false;
                      while(snameRs.next()){
                          found=true;
                          System.out.println("\nStudent Found:");
                          System.out.println("ID:"+snameRs.getInt("id"));
                          System.out.println("Name:"+snameRs.getString("name"));
                          System.out.println("Department:"+snameRs.getString("department"));
                          System.out.println("Marks:"+snameRs.getInt("marks"));
                          if(!found)
                              System.out.println("Student not found.");
                          else
                              System.out.println("Invalid Search Option.");
                          break;
                
                          
                      }
                    
                  }
           case 6://Exit
               System.out.println("Exiting.......");
               con.close();
               System.exit(0);
           default:
               System.out.println("Invalid choice.");
                  
          }
      }
    
    }
    
}
