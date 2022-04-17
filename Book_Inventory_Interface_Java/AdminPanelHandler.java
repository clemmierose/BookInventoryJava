import java.io.OutputStream;
import java.io.*;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.sql.SQLException;


public class AdminPanelHandler implements HttpHandler{
  public void handle (HttpExchange he) throws IOException {
    String response = "Welcome to Process Handler";
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    BookDAO books = new BookDAO();
    try{
      
    ArrayList<Book> allBOOKS = books.getAllBooks();
   
    out.write(
      "<html>" +
      "<head> <title>Book Inventory Admin</title> "+
      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
         "<style>" +
         "header {text-align: center;background-color: #CCDFEE;}" +
         "a:link, a:visited {margin:15px; background-color: white;color: black;border: 2px solid #5EA4D7; padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;align-right;}" +
         "button {margin:15px; background-color: white;color: black;border: 2px solid #5EA4D7; padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;align-right;}" +

         "a:hover, a:active {background-color: #0682E1; color: white;}" +
         ".table {margin: auto;background: #F7FAFC;width: 66%;}" +
      "</style>" +
      "<body>" +
      "<header><h1> Collectors Books Admin Panel</h1></header>"+
      "<hr>" +
      "<div class=\"buttons\">" +
      "<a href=\"/searchBook\" class=\"searchButton\"><img src=\"https://img.icons8.com/fluent-systems-filled/24/000000/login-rounded-right.png\"/> Book search</a>" +
      "<a href=\"/newBook\" class=\"addButton\"><img src=\"https://img.icons8.com/fluent-systems-filled/24/000000/login-rounded-right.png\"/> Add Book </a>" +
      "   <br>" +
      "   <br>" +
      "</div>" +
      "<table class=\"table\">" +
      "<thead>" +
      "  <tr>" +
      "    <th>ID</th>" +
      "    <th>Title</th>" +
      "    <th>Author</th>" +
      "    <th>Year</th>" +
      "    <th>Edition</th>" +
      "    <th>Publisher</th>" +
      "    <th>ISBN</th>"+
      "    <th>Cover</th>" +
      "    <th>Condition</th>" +
      "    <th>Price</th>" +
      "    <th>Notes</th>" +
      "    <th>Delete</th>" +
      "    <th>Update</th>" +
      "  </tr>" +
      "</thead>" +
      "<tbody>");

      for (Book b : allBOOKS){
        out.write(
      "  <tr>"       +
      "    <td>"+ b.getID() + "</td>" +
      "    <td>"+ b.getTitle() + "</td>" +
      "    <td>"+ b.getAuthor() + "</td>" +
      "    <td>"+ b.getYear() + "</td>" +
      "    <td>"+ b.getEdition() + "</td>" +
      "    <td>"+ b.getPublisher() + "</td>" +
      "    <td>"+ b.getIsbn() + "</td>" +
      "    <td>"+ b.getCover() + "</td>" +
      "    <td>"+ b.getCondition() + "</td>" +
      "    <td>"+ b.getPrice() + "</td>" +
      "    <td>"+ b.getNotes() + "</td>" +
      "    <td> <a href=\"/delete?id=" + b.getID() + "\"> delete </a> </td>" +
     // "    <td> <a href=\"/update?updateID=" + b.getID() + "\"> update </a> </td>" +
     // "     <td><form action=\"/delete\" method=\"post\"><button name=\"deleteID\" value=\""+ b.getID() + "\" type=\"submit\">Delete</button></from></td>" + 
     "     <td><form action=\"/update\" method=\"post\"><button name=\"updateID\" value=\""+ b.getID() + "\" type=\"submit\">Update</button></from></td>" + 
      "  </tr>" 
        );
      }

 out.write(
      "</tbody>" +
      "</table>" + 
      "<a href=\"/\" class=\"button\"> Log Out </a" +
      "</body>" +
      "</html>");
       }catch(SQLException se){
      System.out.println(se.getMessage());
    }
    out.close();
    
    
  }
}