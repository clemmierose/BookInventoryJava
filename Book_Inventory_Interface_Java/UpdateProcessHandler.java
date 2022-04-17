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

public class UpdateProcessHandler implements HttpHandler{
  public void handle(HttpExchange he)throws IOException {

    System.out.println("In UpdateProcessHandler");
    int uID = UpdateHandler.uID;
    System.out.println(uID);

    BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));

    String line;
    String request = "";
    while ( (line = in.readLine()) !=null) {
    request = request + line;
    }

    System.out.println(request);
    System.out.println("______");

    HashMap<String,String> map = Util.requestStringToMap(request);

    System.out.println(map);
    System.out.println("Hello");

   // int uID = UpdateHandler.uID;
   // int id = Integer.parseInt(map.get("uID"));
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

    System.out.println("Going to make updateBook...");
    System.out.println("_____");

    BookDAO books = new BookDAO();
    Book updatedBook = new Book(uID, title, author, year, edition, publisher, isbn, cover, condition, price, notes);

    System.out.println(updatedBook);
    System.out.println("Updating book...");

    try{
      System.out.println(books.getBook(uID));
      //Book updatedBook = updateBook(books.getBook(uID));
      books.updateBook(updatedBook);
    }catch(SQLException se){
      System.out.println(se.getMessage());
    }

    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

    System.out.println(request);

     he.sendResponseHeaders(200, 0);

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
      "<header><h1> Updated book: </h1></header>"+
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
      "  </tr>" +
      "</thead>" +
      "<tbody>"+

      "  <tr>"  +
      "    <td>"+ updatedBook.getID() + "</td>" +
      "    <td>"+ updatedBook.getTitle() + "</td>" +
      "    <td>"+ updatedBook.getAuthor() + "</td>" +
      "    <td>"+ updatedBook.getYear() + "</td>" +
      "    <td>"+ updatedBook.getEdition() + "</td>" +
      "    <td>"+ updatedBook.getPublisher() + "</td>" +
      "    <td>"+ updatedBook.getIsbn() + "</td>" +
      "    <td>"+ updatedBook.getCover() + "</td>" +
      "    <td>"+ updatedBook.getCondition() + "</td>" +
      "    <td>"+ updatedBook.getPrice() + "</td>" +
      "    <td>"+ updatedBook.getNotes() + "</td>" +
      "  </tr>" 
    +
      "</tbody>" +
      "</table>" + 
      "<a href=\"/adminPanel\" class=\"button\"> Admin panel </a" +
      "</body>" +
      "</html>");
      out.close();

  }
}