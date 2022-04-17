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

public class UpdateHandler implements HttpHandler {

  public static int uID;

  public void handle(HttpExchange he) throws IOException{
    
    System.out.println("UpdateHandler called");
   // he.sendResponseHeaders(200, 0);
    //BufferedWriter out = new BufferedWriter(
   // new OutputStreamWriter(he.getResponseBody() ));

System.out.println("Waiting for a book to update..");

BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));

    String line;
    String request = "";
    while ( (line = in.readLine()) !=null) {
    request = request + line;
    }

System.out.println("request is " + request);

HashMap <String,String> map = Util.requestStringToMap
(request);

uID = Integer.parseInt(map.get("updateID"));

BookDAO books = new BookDAO();

System.out.println(map);

 /*Map <String,String> parms = Util.requestStringToMap
    (he.getRequestURI().getQuery());

    // print the params for debugging 
  System.out.println(parms);

    //get ID number
  int uID = Integer.parseInt(parms.get("updateID"));

   BookDAO books = new BookDAO();*/

he.sendResponseHeaders(200,0);

System.out.println(request + "this is request: ");
    
  
BufferedWriter out = new BufferedWriter (new OutputStreamWriter(he.getResponseBody() ));


     out.write(
    "<html>" +
      "<head> <title>New Book Form</title> "+
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
      "<body>" +
      "<h1> Update a book </h1>"+
      "<div class=\"container\">" +
  "<form action=\"/updateProcess\" method=\"post\">" +

   "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Book ID\"> ID:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"integer\" name=\"ID\" id=\"ID\" title=\"ID\" value=\"" + uID +"\">"+ "<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Title\"> Title:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"text\" name=\"title\" id=\"Title\" title=\"Title\" >"+ "<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Author\"> Author:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"text\" name=\"author\" id=\"Author\" title=\"Author\">"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Year\"> Year:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"integer\" name=\"year\" id=\"Year\" title=\"Year\">"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Edition\"> Edition:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"integer\" name=\"edition\" id=\"Edition\" title=\"Edition\">"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Publisher\"> Publisher:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"text\" name=\"publisher\" id=\"Publisher\" title=\"Publisher\">"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"ISBN\"> ISBN:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"text\" name=\"isbn\" id=\"ISBN\" title=\"ISBN\">"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Cover\"> Cover:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"text\" name=\"cover\" id=\"Cover\" title=\"Cover\" >"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Condition\"> Condition:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"text\" name=\"condition\" id=\"Condition\" title=\"Condition\" >"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Price\"> Price:</label>" +
  "</div>" +
  "<div class=\"col-75\">" +
  "<input type=\"integer\" name=\"price\" id=\"Price\" title=\"Price\" >"+"<br><br>" +
  "</div>" + 
  "</div>" +

  "<div class=\"row\">" +
  "<div class=\"col-25\">" +
  "<label for=\"Notes\"> Notes:</label>" +
  "</div>" +
  "<div class=\"col-90\">" +
  "<input type=\"text\" name=\"notes\" id=\"Notes\" title=\"Notes\">"+"<br><br>" +
   "</div>" + 
  "</div>" +
   
   "<div class=\"row\">" +
  "<input type=\"submit\" value=\"Submit\">" +
  "</div>"+
 "</form>" +
"</div>"+
"</body>"+
"</html>");

  out.close();
  }
}