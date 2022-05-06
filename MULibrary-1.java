import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class MULibrary extends JFrame implements ActionListener {

private JLabel fullNameLabel,fullNameLabel1;
private JTextField isbn,title;
private JPanel centerPanel,northPanel,southPanel ;
private JButton addNewBook, library, arlington ;


public MULibrary () {

fullNameLabel = new JLabel("Title:");
title = new JTextField (10);

centerPanel = new JPanel(); 
centerPanel.add(fullNameLabel);
centerPanel.add(title);
add(centerPanel,BorderLayout.CENTER);

fullNameLabel1 =new JLabel("ISBN:");
isbn = new JTextField (10);
northPanel = new JPanel(); 
northPanel.add(fullNameLabel1);
northPanel.add(isbn);
add(northPanel,BorderLayout.NORTH);


addNewBook= new JButton("Add New Book");
addNewBook.addActionListener(this);

library = new JButton("Search ISBN_MULibrary");
library.addActionListener(this);

arlington=  new JButton("Search ISBN_Arlington");
southPanel = new JPanel();
southPanel.add(addNewBook);
southPanel.add(library);
southPanel.add(arlington);
add(southPanel , BorderLayout.SOUTH);


setVisible(true);// making our GUI visible
setSize(500, 300); // setting the length and breadth of GUI
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void actionPerformed(ActionEvent event) 
{
 if(event.getSource()== addNewBook){
 String x = title.getText();
 String t= isbn.getText();
 String URL = "jdbc:mysql://localhost/mulibrary?user=root&password=";
 
 try{
  Class.forName("com.mysql.jdbc.Driver");
 
  
  Connection con= DriverManager.getConnection(URL);
  String sqlStatement = "insert into book values('"+t+"','"+x+"');";  //to add a values in the database.
  Statement stmt = con.createStatement();
  stmt.execute(sqlStatement);

  }
  catch(ClassNotFoundException ex){
  ex.printStackTrace();
  
  }
  catch(SQLException ex){
  ex.printStackTrace();
  }
   String output = "\n The following book is successfully added to MU Library " + 
     "\nISBN: "+ t +
     "\nTitle: " + x;
     JOptionPane.showMessageDialog(null,output);
  
}
 else if (event.getSource()== library)
 {
String x = title.getText();
String t= isbn.getText();
String URL = "jdbc:mysql://localhost/mulibrary?user=root&password="; // This line of code is used to connect to sql
  try{
 Class.forName("com.mysql.jdbc.Driver"); //connect the mysql.
 Connection con= DriverManager.getConnection(URL);

  String sqlStatement ="Select title from book where isbn ="+ t; // to add a content in 
 // System.out.println(sqlStatement);
  
  Statement stmt = con.createStatement();
  ResultSet result =stmt.executeQuery(sqlStatement);
  
  if(result.next())
  {
  String z = "ISBN: "+ t + "\nTitle:" + result.getString(1)+"\nAvailable at MU Library";
 JOptionPane.showMessageDialog(null,z);
 }
  else{
    String y ="ISBN: " + t + " is not available at MuLibrary";// to check the database.
    JOptionPane.showMessageDialog(null,y);
  }
  }  
  catch(SQLException ex){
    ex.printStackTrace(); 
 }
   catch(ClassNotFoundException ex){
 
   ex.printStackTrace(); 
  }
 }
}

public static void main(String [] args){
MULibrary app = new MULibrary();
}
}// end of class