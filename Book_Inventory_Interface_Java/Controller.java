import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class Controller { 
   public static void main(String[] args) throws SQLException {
    Scanner in = new Scanner(System.in);
    BookDAO books = new BookDAO();
    String selection ;

    do {

      System.out.println("--------------------");
      System.out.println("Book Inventory");
      System.out.println("Choose from these options: ");
      System.out.println("--------------------");

      System.out.println("1 - Retrieve all books");
      System.out.println("2 -  Search for a book");
      System.out.println("3 -  Insert a new book into database");
      System.out.println("4 -  Update existing book");
      System.out.println("5 -  Delete a book from database");

      System.out.println("6 - Exit");
      System.out.println("Enter choice > ");

      selection = in.nextLine();

      switch (selection) {

      case "1":
        System.out.println("List of all books");
        ArrayList<Book> allBooks = books.getAllBooks();
        for (int i = 0; i < allBooks.size(); i++) {
          System.out.println(allBooks.get(i));
        }
        System.out.println();
        break;

      case "2":
        System.out.println("Search for a book");
        System.out.println("\nEnter book ID to get details");
        int ID = Integer.parseInt(in.nextLine());
        System.out.println(books.getBook(ID));
        System.out.println();
        break;

      case "3":
        System.out.println("Add a new book");
        Book book = createBook();
        books.addBook(book);
        System.out.println();
        break;

      case "4":
        System.out.println("Update a book");
        System.out.println("\nEnter book ID to update ");
        int uID = Integer.parseInt(in.nextLine());
        System.out.println(books.getBook(uID));
        Book updatedBook = updateBook(books.getBook(uID));
        books.updateBook(updatedBook);
        break;

      case "5":
        System.out.println("Delete a book");
        System.out.println("\nEnter ID of a book to delete");
        int dID = Integer.parseInt(in.nextLine());
        books.deleteBook(dID);
        break;

      case "6":
        System.out.println("Exiting");
        break;

      default:
        System.out.println("Invalid Selection");
      }

    } while (!selection.equals("6"));

  }
  private static  Book createBook() {

    int id;
    String title;
    String author;
    int year;
    int edition;
    String publisher;
    String isbn;
    String cover;
    String condition;
    int price;
    String notes;

    Scanner in = new Scanner(System.in);

    System.out.println("Please enter book ID");
    id = Integer.parseInt(in.nextLine());

    System.out.println("Please enter a Title");
    title = in.nextLine();

    System.out.println("Please enter Author");
    author = in.nextLine();

    System.out.println("Please enter year");
    year = Integer.parseInt(in.nextLine());;
    

    System.out.println("Please enter edition");
    edition = Integer.parseInt(in.nextLine());

    System.out.println("Please enter a Publisher");
    publisher = in.nextLine();

    System.out.println("Please enter Isbn");
    isbn = in.nextLine();

    System.out.println("Please enter Cover");
    cover = in.nextLine();

    System.out.println("Please enter Condition");
    condition = in.nextLine();

    System.out.println("Please enter price");
    price = Integer.parseInt(in.nextLine());

    System.out.println("Please enter Notes");
    notes = in.nextLine();

    return new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);
  }

  private static Book updateBook(Book book) {
    int id;
    String title;
    String author;
    int year;
    int edition;
    String publisher;
    String isbn;
    String cover;
    String condition;
    int price;
    String notes;

    Scanner in = new Scanner(System.in);
    System.out.println("Updating Book with ID:" + book.getID());

    System.out.println("Please enter Title");
    title = in.nextLine();
    if (title.equals(""))
      title = book.getTitle();

    System.out.println("Please enter new Author");
    author = in.nextLine();
    if (author.equals(""))
      author = book.getAuthor();

    System.out.println("Please enter year");
    String strYear = in.nextLine();
    if (strYear.equals(""))
      year = book.getYear();
    else
      year = Integer.parseInt(strYear);

    System.out.println("Please enter edition");
    String strEdition = in.nextLine();
    if (strEdition.equals(""))
      edition = book.getEdition();
    else
      edition = Integer.parseInt(strEdition);

    System.out.println("Please enter new Publisher");
    publisher = in.nextLine();
    if (publisher.equals(""))
      publisher = book.getPublisher();

    System.out.println("Please enter new Isbn");
    isbn = in.nextLine();
    if (isbn.equals(""))
      isbn = book.getIsbn();

    System.out.println("Please enter a new Cover");
    cover = in.nextLine();
    if (cover.equals(""))
      cover = book.getCover();

    System.out.println("Please enter a new Condition");
    condition = in.nextLine();
    if (condition.equals(""))
      condition = book.getCondition();

    System.out.println("Please enter a new price");
    String strPrice = in.nextLine();
    if (strPrice.equals(""))
      price = book.getPrice();
      else 
      price = Integer.parseInt(strPrice);

    System.out.println("Please enter new Notes");
    notes = in.nextLine();
    if (notes.equals(""))
      notes = book.getNotes();

    return new Book(book.getID(), title, author, year, edition, publisher, isbn, cover, condition, price, notes);
  }


}

