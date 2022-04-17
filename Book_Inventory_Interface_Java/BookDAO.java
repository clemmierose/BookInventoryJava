import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO {

  public BookDAO() {
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

  public ArrayList<Book> getAllBooks() throws SQLException {
			System.out.println("Retriving all books...");
			Connection dbConnection = null; 
			Statement statement = null;
			ResultSet result = null;
			String query = "SELECT * FROM bookCollection;";
			ArrayList<Book> books = new ArrayList<>();
			
			try {
				dbConnection = getDBConnection();
				statement = dbConnection.createStatement();
				//System.out.println("DBQuery = " + query);
				result = statement.executeQuery(query);
				
				while (result.next() ) {
					
					int id = result.getInt("ID");
					String title = result.getString("Title");
					String author = result.getString("Author");
					int year = result.getInt("Year");
					int edition = result.getInt("Edition");
					String publisher = result.getString("Publisher");
					String isbn = result.getString("ISBN");
					String cover = result.getString("Cover");
					String condition = result.getString("Condition");
					int price = result.getInt("Price");
					String notes = result.getString("Notes");
					
					books.add(new Book (id,title,author,year,edition,publisher,isbn,cover,condition,price,notes));
				}
			}catch(Exception e) {
				System.out.println("get all books: " + e);
			}
			finally {
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
			return books;
		}

  public Book getBook(int book_id) throws SQLException {
      Book temp = null;
      Connection dbConnection = null;
      Statement statement = null;
      ResultSet result = null;

      String query = "SELECT * FROM bookCollection WHERE ID=" + book_id + ";";
      try {
        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();
        //System.out.println("DBQuery: " + query);
        result = statement.executeQuery(query);

        while (result.next()) {
           
          int id = result.getInt("ID");
					String title = result.getString("Title");
					String author = result.getString("Author");
					int year = result.getInt("Year");
					int edition = result.getInt("Edition");
					String publisher = result.getString("Publisher");
					String isbn = result.getString("Isbn");
					String cover = result.getString("Cover");
					String condition = result.getString("Condition");
					int price = result.getInt("Price");
					String notes = result.getString("Notes");

          temp = new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);
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

  public Boolean deleteBook(int book_id) throws SQLException {
    System.out.println("Deleting book");
    Connection dbConnection = null;
    Statement statement = null;
    int result = 0;
    String query = "DELETE FROM bookCollection WHERE ID = " + book_id + ";";

    try {
      dbConnection = getDBConnection();
      statement = dbConnection.createStatement();
      //System.out.println(query);
      result = statement.executeUpdate(query);
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
    }
    if (result == 1) {
      return true;
    } else {
      return false;
    }
  }

  public Boolean updateBook(Book book) throws SQLException {
    System.out.println("Updating a book..");
    Connection dbConnection = null;
    Statement statement = null;


    
    String query = "UPDATE bookCollection " + "SET ID = '" + book.getID() + "'," + "Title = '" + book.getTitle() + "'," + "Author = '" + book.getAuthor() + "'," + "Year = '" + book.getYear() + "'," + "Edition = '" + book.getEdition() + "'," + "Publisher = '" + book.getPublisher() + "'," + "ISBN = '" + book.getIsbn() + "'," + "Cover = '" + book.getCover() + "'," + "Condition = '" + book.getCondition() + "'," + "Price = '" + book.getPrice() + "'," + 
    "Notes = '" + book.getNotes() + "'" + " WHERE ID = '" + book.getID() + "';";
    
    try {
      dbConnection = getDBConnection();
      statement = dbConnection.createStatement();
      //System.out.println(query);

      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
    }
    return true;

  }

public boolean addBook(Book in) throws SQLException {
		  Connection dbConnection = null;
		  Statement statement = null;


String update = "INSERT INTO bookCollection (ID, Title, Author, year, edition, Publisher, Isbn, Cover, Condition, price, Notes) VALUES ("+in.getID()+ ",'" + in.getTitle()+"','" + in.getAuthor()+"'," + in.getYear() + "," + in.getEdition() + ",'" + in.getPublisher() + "','" + in.getIsbn() + "','" + in.getCover() + "','" + in.getCondition() + "'," + in.getPrice() + ",'" + in.getNotes() +"');";

	boolean ok = false;
  try {
    dbConnection = getDBConnection();
    statement = dbConnection.createStatement();
    System.out.println(update);

    statement.executeUpdate(update);
    ok = true;
  } catch (SQLException e) {
    System.out.println(e.getMessage());
  } finally {
    if (statement != null) {
      statement.close();
    }
    if (dbConnection != null) {
      dbConnection.close();
    }
  }
return ok;

}
}
