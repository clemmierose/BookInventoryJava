import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;


class Main {
  static final private int PORT = 8080;

  public static void main(String[] args) throws IOException {
    System.out.println("Hello world!");

    HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
    server.createContext("/", new RootHandler() ); 

    server.createContext("/login", new LogHandler() );
    //server.createContext("/logaction", new LogProcessHandler() );
    server.createContext("/adminPanel", new AdminPanelHandler());

    server.createContext("/delete", new DeleteHandler() );

    server.createContext("/update", new UpdateHandler() ); 
    server.createContext("/updateProcess", new UpdateProcessHandler() );

    server.createContext("/newBook", new NewBookHandler() );
    server.createContext("/newBookProcess", new NewBookProcessHandler() );

    server.createContext("/searchBook", new SearchBookHandler() );
    server.createContext("/searchBookProcess", new SearchBookProcessHandler() );

    
    server.setExecutor(null);
    server.start();
    System.out.println("The server is listening on port " + PORT);
  }


} 