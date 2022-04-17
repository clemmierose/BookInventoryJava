/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

  public UserDAO() {
  }

  private static Connection getDBConnection() {
    Connection dbConnection = null;
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      String dbURL = "jdbc:sqlite:books.db";
      dbConnection = DriverManager.getConnection(dbURL);
      return dbConnection;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return dbConnection;
  }

//Queries

  public User getUser(int ID) throws SQLException {
      User temp = null;
      Connection dbConnection = null;
      Statement statement = null;
      ResultSet result = null;

      String query = "SELECT * FROM users WHERE ID=" + user_id + ";";
      try {
        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();
        //System.out.println("DBQuery: " + query);
        result = statement.executeQuery(query);

        while (result.next()) {
           
          int id = result.getInt("ID");
					String username = result.getString("Username");
					String password = result.getString("Password");

          temp = new User(id, username, password);
        }
      } finally {
        if (result != null) {
          result.close();
        }
        if (statement != null) {
          statement.close();
        }
        if (dbConnection != null) {
          dbConnection.close();
        }
      }
      return temp;
    }

  }
  */

