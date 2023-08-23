
import java.sql.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
    /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // this must contain the absolute path and filename of the DB we want to connect to
            //1.Project Structure: add reference to sqlite driver
            //2. Define connection String jdbc:subprotocol
            String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
            // create a connection to the database
            //3. get connection
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void readCategories() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
            //Wie ist ein connection-String aufgebaut - DriverName:Filename
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            Statement stmt = conn.createStatement();

            //RS is a pointer to a virtual table
            //points to, after opening, the dataset -1
            String query = "SELECT UrlaubsID, Schlagwort, UrlaubskategorieID FROM Urlaube";
            ResultSet rs = stmt.executeQuery( query );

            while ( rs.next() ) {
                //printf placeholders: %s format specifier for String, %d is for integers(decimal), %n is for line separator
                System.out.printf("Schlagwort: %s die UrlabsID ist:  %d %n",
                        rs.getString("Schlagwort"),
                        rs.getInt("UrlaubsID"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void printMetadata() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
            //Wie ist ein connection-String aufgebaut - DriverName:Filename
            // create a connection to the database
            conn = DriverManager.getConnection(url);


            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Tabelle gefunden: " + tableName);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    /*
    public static void displayReviewsOrderByRatings() {
        //1. establish connection
        //2. create resultset
        //3. Output the ratings incl. keyword from the table URLAUBE with printf


        Connection conn = null;
        try {
            // Database parameters
            String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
            // Create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to Campus02JDBC database  has been established.");

            Statement stmt = conn.createStatement();

            // Query to retrieve Bewertungen ordered by Bewertungen
            String query = "SELECT Schlagwort, Bewertungen FROM Urlaube ORDER BY Bewertungen DESC";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Bewertungen ordered by Bewertungen:");

            while (rs.next()) {
                System.out.printf("Schlagwort: %s, Bewertungen: %f %n",
                        rs.getString("Schlagwort"),
                        rs.getFloat("Bewertungen"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

     */

    public static void displayReviewsOrderByRatings() {
        Connection conn = null;
        try {
            // Database parameters
            String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
            // Create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            Statement stmt = conn.createStatement();

            // Query to retrieve Schlagwort and Bewertungen from the Urlaube table
            String query = "SELECT Schlagwort, Bewertungen FROM Urlaube ORDER BY Bewertungen DESC";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Bewertungen ordered by Bewertungen:");

            while (rs.next()) {
                System.out.printf("Schlagwort: %s, Bewertungen: %f %n",
                        rs.getString("Schlagwort"),
                        rs.getFloat("Bewertungen"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}