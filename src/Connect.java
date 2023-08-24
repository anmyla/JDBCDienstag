
import java.sql.*;

public class Connect {

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


    public static void displayReviewsOrderByRatings() {
        Connection conn = null;
        try {
            // Database parameters
            String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
            // Create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            Statement stmt = conn.createStatement();

            //Query to retrieve Schlagwort and BewertungsID using inner join
            //Schlagwort from Urlaube table and BewertungsID from Bewetungen table.

            String query = "SELECT Urlaube.Schlagwort, Bewertungen.Punkte, Bewertungen.Kommentar FROM Urlaube " +
                    "INNER JOIN Bewertungen ON Urlaube.UrlaubsID = Bewertungen.UrlaubsID " +
                    "ORDER BY Punkte DESC";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Holiday Categories by Ratings ");

            while (rs.next()) {
                System.out.printf("Category: %s, Rating: %d, Reviews: %s %n",
                        rs.getString("Schlagwort"),
                        rs.getInt("Punkte"),
                        rs.getString("Kommentar"));
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

    public static void aufgabeNachmittagMittwoch() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            Statement stmt = conn.createStatement();

            String query = "SELECT KommentareZuBewertungen.KommentarID, KommentareZuBewertungen.Kommentare, KommentareZuBewertungen.Person, Bewertungen.BewertungsId, Bewertungen.Kommentar FROM KommentareZuBewertungen " +
                    "INNER JOIN Bewertungen ON KommentareZuBewertungen.KommentarID = Bewertungen.BewertungsId";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Comments on Reviews ");

            while (rs.next()) {
                System.out.printf("KommentarID: %d, BewertungsId: %d, Reviews: %s, Comments: %s, Person: %s %n",
                        rs.getInt("KommentarID"),
                        rs.getInt("BewertungsId"),
                        rs.getString("Kommentar"),
                        rs.getString("Kommentare"),
                rs.getString("Person"));
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