import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class SearchBookHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200,0);
    
    System.out.println("Searching for a book...");
    System.out.println("_________");
    
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));

    out.write(
      "<html>" +

      "<head> <title>Book Inventory</title> "+
      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +

     "<style>" +
      "h1 {text-align: center;}" +
      "button {margin:15px;}" +
      "nput[type=text], select, textarea{width: 100%;padding: 12px;border: 1px solid #ccc;border-radius: 4px;box-sizing: border-box;resize: vertical;}" +
      "label {padding: 12px 12px 12px 0;display: inline-block;}" +
      "input[type=submit] {background-color: #2371AB;color: white;padding: 12px 20px;border: none; border-radius: 4px;cursor: pointer;float: right;}" +
      ".container {border-radius: 10px;background-color: #E0EBF4;padding: 50px;}" +
      ".col-25 {float: left;width: 25%;margin-top: 6px;}" +
      ".col-75 {float: left;width: 75%;margin-top: 6px;}"+
      ".row:after {content: \"\"; display: table; clear: both;}" +
      "</style>" +
      "</head>" +

      "<body>" +
      "<h1> Collectors Books </h1>"+
      "<hr>" +

      "<div class=\"container\">" +
      "<form method=\"post\" action =\"/searchBookProcess\">" +
      "<div class=\"row\">" +
      "<div class=\"col-25\">" +
        "<label for=\"Book Search\">Book ID</label>" + "</div>"+
      "<div class=\"col-75\">" + 
      "<input type=\"integer\" id=\"ID\" name=\"ID\" title=\"ID\" placeholder=\"Book ID...\">" +
      "</div>" +
      "</div>"+
      "<div class=\"row\">" +
        "<input type=\"submit\" value=\"Submit\" class=\"button\"></a>" + "</div>" +
      "</form>" +
      "</div>"+
      "</body>"+
      "</html>");

    out.close();

    
  }
}
