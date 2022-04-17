import java.io.OutputStream;
import java.io.*;
import java.io.OutputStreamWriter;

import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

import java.sql.SQLException;


public class SearchBookProcessHandler implements HttpHandler {
  public void handle (HttpExchange he) throws IOException{

BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
   
String line;
String request = "";
while( (line = in.readLine()) != null ){
request = request + line;
}

System.out.println("request is " + request);

HashMap <String,String> map = Util.requestStringToMap(request);

int ID = Integer.parseInt(map.get("ID"));

System.out.println("Going to search for Book...");

BookDAO books = new BookDAO();

System.out.println(map);

he.sendResponseHeaders(200,0);

BufferedWriter out = new BufferedWriter(  
    new OutputStreamWriter(he.getResponseBody() ));

try{
  Book searchedBook = books.getBook(ID);
  if (searchedBook == null) throw new SQLException();
  System.out.println("Search result:");
  System.out.println("_________");
  System.out.println(searchedBook);

  out.write(
    
      "<html>" +
      "<head> <title>Book Inventory Search</title> "+
      "<link rel=\"stylesheet\"                   href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\"  crossorigin=\"anonymous\">" +
      "<style>" +
      "header {text-align: center;background-color: #CCDFEE;}" +
      "a:link, a:visited {margin:15px;background-color: white;color: black;border: 2px solid #5EA4D7; padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;align-right;}" +
      " a:hover, a:active {margin:15px;background-color: #0682E1; color: white;}" +
      " .table {margin: auto;background: #F7FAFC;width: 66%;}" +
      "</style>" +
      "</head>"+
      "<body>" +
      "<h1> Search Result </h1>"+
      "<hr>" +
      "<div class=\"buttons\">" +
      "<a href=\"/searchBook\" class=\"searchButton\"><img src=\"https://img.icons8.com/fluent-systems-filled/24/000000/login-rounded-right.png\"/> Search for different book</a>" +
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
      "  </tr>" +
      "</thead>" +
      "<tbody>"+
      "  <tr>"       +
      "    <td>"+ searchedBook.getID() + "</td>" +
      "    <td>"+ searchedBook.getTitle() + "</td>" +
      "    <td>"+ searchedBook.getAuthor() + "</td>" +
      "    <td>"+ searchedBook.getYear() + "</td>" +
      "    <td>"+ searchedBook.getEdition() + "</td>" +
      "    <td>"+ searchedBook.getPublisher() + "</td>" +
      "    <td>"+ searchedBook.getIsbn() + "</td>" +
      "    <td>"+ searchedBook.getCover() + "</td>" +
      "    <td>"+ searchedBook.getCondition() + "</td>" +
      "    <td>"+ searchedBook.getPrice() + "</td>" +
      "    <td>"+ searchedBook.getNotes() + "</td>" +
      "  </tr>" 
      +
      "</tbody>" +
      "</table>" + 
      "<a href=\"/adminPanel\" class=\"button\"> Admin Panel </a>" +
      "<a href=\"/\" class=\"button\"> Log Out </a>" +
      "</body>" +
      "</html>");
      out.close();
       }catch(SQLException se){
      System.out.println(se.getMessage());
    }
  }
}


/*he.sendResponseHeaders(200,0);
out.write(
      "<html>" +
      "<head> <title>Book not found</title> "+
      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
     "<style>" +
         "header {text-align: center;background-color: #CCDFEE;}" +
         "a:link, a:visited {background-color: white;color: black;border: 2px solid #5EA4D7; padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;align-right;}" +

         "a:hover, a:active {background-color: #0682E1; color: white;}" +
         ".table {margin: auto;background: #F7FAFC;width: 66%;}" +
      "</style>" +
      "</head>" +
      "<body>" +
      "<h1> No book found with this ID, please try again</h1>"+
      "<div class=\"buttons\">" +
      "  <a class=\"searchBookButton\" href=\"/searchBook\"> Search for new Book </a>" +
      "</div>" +
      "</body>" +
      "</html>");
    out.close();*/
    
   // }
 // }*/
