import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static int isWorking()
    {
        if(getConnection()==null)
        {
            return 0;
        }

        return 1;
    }

    public static Connection getConnection()
    {
        Connection conn;

        String connectionUrl;

        connectionUrl = "jdbc:sqlserver://localhost:1433;database=Library ;user=sa1;password=pass;encrypt=true;trustServerCertificate=true";

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionUrl);

            if(conn==null)
            {
                System.out.println("Failed - conn is null");
                return null;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
        return conn;
    }

    public static List<Genre> GetAllActiveGenres()
    {
        Connection conn = getConnection();

        if(conn==null)
        {
            return null;
        }

        List<Genre> Genres = new ArrayList<Genre>();

        try
        {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("spGenreSelectAllActive");

            while (rs.next())
            {
                int genreId = rs.getInt("GenreId");
                String name = rs.getString("Name");
                boolean active = rs.getBoolean("Active");
                Genre genre=new Genre(genreId, name, active);
                Genres.add(genre);
                System.out.println(genre);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

        return Genres;
    }

    public static List<Author> GetAllActiveAuthors()
    {
        Connection conn = getConnection();

        if(conn==null)
        {
            return null;
        }

        List<Author> Authors = new ArrayList<Author>();

        try
        {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("spAuthorSelectAllActive");

            while (rs.next())
            {
                int authorId = rs.getInt("AuthorId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String nationality= rs.getString("Nationality");
                int genreId = rs.getInt("GenreId");
                boolean active = rs.getBoolean("Active");
                Author author=new Author(authorId, firstName, lastName, nationality, genreId, active);
                Authors.add(author);
                System.out.println(author);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

        return Authors;
    }

    public static List<Book> GetAllActiveBooks()
    {
        Connection conn = getConnection();

        if(conn==null)
        {
            return null;
        }

        List<Book> Books = new ArrayList<Book>();

        try
        {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("spBookSelectAllActive");

            while (rs.next())
            {
                int bookId = rs.getInt("BookId");
                String title = rs.getString("Title");
                int authorId = rs.getInt("AuthorId");
                String publisher= rs.getString("Publisher");
                int genreId = rs.getInt("GenreId");
                int publicationYear= rs.getInt("PublicationYear");
                boolean active = rs.getBoolean("Active");
                Book book=new Book(bookId, title, authorId, publisher, genreId, publicationYear, active);
                Books.add(book);
                System.out.println(book);
            }
        }

        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

        return Books;
    }

    public static List<Loan> GetAllActiveLoans() {
        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        List<Loan> loans = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("spLoanSelectAllActive");

            while (rs.next()) {
                int loanId = rs.getInt("LoanId");
                int bookId = rs.getInt("BookId");
                int readerId = rs.getInt("ReaderId");
                java.sql.Date loanDate = rs.getDate("LoanDate");
                java.sql.Date returnDate = rs.getDate("ReturnDate");
                boolean active = rs.getBoolean("Active");

                Loan loan = new Loan(loanId, bookId, readerId, loanDate, returnDate, active);
                loans.add(loan);
                System.out.println(loan);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return loans;
    }

    public static List<Reader> GetAllActiveReaders() {
        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        List<Reader> readers = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("spReaderSelectAllActive");

            while (rs.next()) {
                int readerId = rs.getInt("ReaderId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String CNP = rs.getString("CNP");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                boolean active = rs.getBoolean("Active");

                Reader reader = new Reader(readerId, firstName, lastName, CNP, phone, email, active);
                readers.add(reader);
                System.out.println(reader);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return readers;
    }




    //GENRE_______________________________________________________________________________



    public static void addGenre(String name) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call AddGenre(?)}");
            cstmt.setString(1, name);
            cstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateGenre(int genreId, String name) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call UpdateGenre(?, ?)}");
            cstmt.setInt(1, genreId);
            cstmt.setString(2, name);

            cstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static boolean deleteGenre(int genreId) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call DeleteGenre(?)}");
            cstmt.setInt(1, genreId);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }





    //AUTHOR_______________________________________________________________________________




    public static void addAuthor(String firstName, String lastName, String nationality, int genreId) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call AddAuthor(?, ?, ?, ?)}");
            cstmt.setString(1, firstName);
            cstmt.setString(2, lastName);
            cstmt.setString(3, nationality);
            cstmt.setInt(4, genreId);
            cstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static boolean updateAuthor(int authorId, String firstName, String lastName, String nationality, int genreId) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call UpdateAuthor(?, ?, ?, ?, ?)}");
            cstmt.setInt(1, authorId);
            cstmt.setString(2, firstName);
            cstmt.setString(3, lastName);
            cstmt.setString(4, nationality);
            cstmt.setInt(5, genreId);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }



    public static boolean deleteAuthor(int authorId) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call DeleteAuthor(?)}");
            cstmt.setInt(1, authorId);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }





    //BOOK_______________________________________________________________________________



    public static void addBook(String title, int authorId, String publisher, int genreId, int publicationYear) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call AddBook(?, ?, ?, ?, ?)}");
            cstmt.setString(1, title);
            cstmt.setInt(2, authorId);
            cstmt.setString(3, publisher);
            cstmt.setInt(4, genreId);
            cstmt.setInt(5, publicationYear);
            cstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static boolean updateBook(int bookId, String title, int authorId, String publisher, int genreId, int publicationYear) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call UpdateBook(?, ?, ?, ?, ?, ?)}");
            cstmt.setInt(1, bookId);
            cstmt.setString(2, title);
            cstmt.setInt(3, authorId);
            cstmt.setString(4, publisher);
            cstmt.setInt(5, genreId);
            cstmt.setInt(6, publicationYear);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    public static boolean deleteBook(int bookId) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call DeleteBook(?)}");
            cstmt.setInt(1, bookId);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }






    //LOAN_______________________________________________________________________________



    public static void addLoan(int bookId, int readerId, java.sql.Date loanDate, java.sql.Date returnDate) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call AddLoan(?, ?, ?, ?)}");
            cstmt.setInt(1, bookId);
            cstmt.setInt(2, readerId);
            cstmt.setDate(3, loanDate);
            cstmt.setDate(4, returnDate);
            cstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static boolean updateLoan(int loanId, int bookId, int readerId, java.sql.Date loanDate, java.sql.Date returnDate) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call UpdateLoan(?, ?, ?, ?, ?)}");
            cstmt.setInt(1, loanId);
            cstmt.setInt(2, bookId);
            cstmt.setInt(3, readerId);
            cstmt.setDate(4, loanDate);
            cstmt.setDate(5, returnDate);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    public static boolean deleteLoan(int loanId) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call DeleteLoan(?)}");
            cstmt.setInt(1, loanId);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }






    //READER_______________________________________________________________________________




    public static void addReader(String firstName, String lastName, String CNP, String phone, String email) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call AddReader(?, ?, ?, ?, ?)}");
            cstmt.setString(1, firstName);
            cstmt.setString(2, lastName);
            cstmt.setString(3, CNP);
            cstmt.setString(4, phone);
            cstmt.setString(5, email);
            cstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static boolean updateReader(int readerId, String firstName, String lastName, String CNP, String phone, String email) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call UpdateReader(?, ?, ?, ?, ?, ?)}");
            cstmt.setInt(1, readerId);
            cstmt.setString(2, firstName);
            cstmt.setString(3, lastName);
            cstmt.setString(4, CNP);
            cstmt.setString(5, phone);
            cstmt.setString(6, email);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    public static boolean deleteReader(int readerId) {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database");
            return false;
        }

        try {
            CallableStatement cstmt = conn.prepareCall("{call DeleteReader(?)}");
            cstmt.setInt(1, readerId);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}