import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class RootHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
    String response = "Welcome to OOP";
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    BookDAO books = new BookDAO();
    try{
    ArrayList<Book> allBOOKS = books.getAllBooks();
   
    out.write(
      "<html>" +
      "<head> <title>Book Inventory</title> "+
      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "<style>" +
      "header {text-align: center;background-color: #CCDFEE;}" +
      "a:link, a:visited {background-color: white;color: black;border: 2px solid #5EA4D7; padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;align-right;}" +
      "a:hover, a:active {background-color: #0682E1; color: white;}" +
      ".table {margin: auto;background: #F7FAFC;width: 66%;}" +
      "h2 {text-align: center;}"+
      "</style>" +
      "</head>"+
      "<body>" +
      "<header><h1> Welcome to Collectors Books </h1></header>"+
      "<hr>" +
      "<h2> Available books: </h2>" +
      "<div class=\"container\">" +
      "<a href=\"/login\" class=\"button\"><img src=\"https://img.icons8.com/fluent-systems-filled/24/000000/login-rounded-right.png\"/> Log In</a>" +
      "</div>" +
      "<hr>"+
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
      "  </tr>" 
        );
      }
      out.write(
      "</tbody>" +
      "</table>" +
      "</body>" +
      "</html>");
     }catch(SQLException se){
      System.out.println(se.getMessage());
    }
    out.close();


  }
}