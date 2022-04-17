import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.*;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class NewBookProcessHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    System.out.println("On the NewBookProcess..");

    BufferedReader in = new BufferedReader(new InputStreamReader (he.getRequestBody() ));

    String line;
    String request = "";
    while ( (line = in.readLine()) !=null) {
    request = request + line;
    }


  HashMap<String,String> map = Util.requestStringToMap(request);

  System.out.println(map);

    int id = Integer.parseInt(map.get("ID"));
    String title = map.get("title");
    String author = map.get("author");
    int year = Integer.parseInt(map.get("year"));
    int edition = Integer.parseInt(map.get("edition"));
    String publisher = map.get("publisher");
    String isbn = map.get("isbn");
    String cover = map.get("cover");
    String condition = map.get("condition");
    int price = Integer.parseInt(map.get("price"));
    String notes = map.get("notes");

BookDAO books = new BookDAO();
Book newBook = new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);

System.out.println(newBook);
System.out.println(map);
System.out.println("Adding a book..");
System.out.println("_______");

try{
books.addBook(newBook);

System.out.println();
}catch(SQLException se){
  System.out.println(se.getMessage());
}

he.sendResponseHeaders(200,0);
BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody() ));

//System.out.println(request);

out.write(
      "<html>" +
      "<head> <title>Added a New Book</title> "+
      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "<style>" +
      "header {text-align: center;background-color: #CCDFEE;}" +
      "a:link, a:visited {background-color: white;color: black;border: 2px solid #5EA4D7; padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;align-right;}" +

      "a:hover, a:active {background-color: #0682E1; color: white;}" +
      ".table {margin: auto;background: #F7FAFC;width: 66%;}" +
      "</style>" +
      "<body>" +
      "<header><h1> Added a new book </h1></header>"+
      "<hr>" +
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
      "<tbody>"+

      "  <tr>"  +
      "    <td>"+ newBook.getID() + "</td>" +
      "    <td>"+ newBook.getTitle() + "</td>" +
      "    <td>"+ newBook.getAuthor() + "</td>" +
      "    <td>"+ newBook.getYear() + "</td>" +
      "    <td>"+ newBook.getEdition() + "</td>" +
      "    <td>"+ newBook.getPublisher() + "</td>" +
      "    <td>"+ newBook.getIsbn() + "</td>" +
      "    <td>"+ newBook.getCover() + "</td>" +
      "    <td>"+ newBook.getCondition() + "</td>" +
      "    <td>"+ newBook.getPrice() + "</td>" +
      "    <td>"+ newBook.getNotes() + "</td>" +
      "  </tr>" 
    +
      "</tbody>" +
      "</table>" + 
      "<a href=\"/adminPanel\" class=\"button\"> Admin panel </a" +
      "<a href=\"/\" class=\"button\"> Log Out </a" +
      "</body>" +
      "</html>");
      out.close();
    //} 
    //catch (SQLException se){
      //System.out.println(se.getMessage());
    //}

   }
 }
