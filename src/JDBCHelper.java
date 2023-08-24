import models.Bewertungen;
import models.Urlaubskategorien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCHelper {
    private Connection connection;

    public JDBCHelper(String url) {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printTableMetadata() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[] {"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Tabelle gefunden: " + tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void printAllBewertungen() {
        try {
            ResultSet rs = executeQuery("SELECT BewertungsId, Punkte, Kommentar FROM Bewertungen");
            while (rs.next()) {
                System.out.printf("ID %d Punkte %d Kommentar %s %n",
                        rs.getInt("BewertungsID"),
                        rs.getInt("Punkte"),
                        rs.getString("Kommentar")
                );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAlleKommentare() {
        try {
            //alle Kommentare aus der Tabelle KommentareZuBewertungen sollen ausgegeben werden
            ResultSet rs = executeQuery("SELECT KommentarID, " +
                    "       BewertungsID, " +
                    "       Kommentare, " +
                    "       Person " +
                    "  FROM KommentareZuBewertungen;");


            while (rs.next()) {
                System.out.printf("KommentarID %d, BewertungsID %d, Kommentar: %s %n",
                        rs.getInt("KommentarID"),
                        rs.getInt("BewertungsID"),
                        rs.getString("Kommentare")
                );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printKategorien() {
        try {
            ResultSet rs = executeQuery("SELECT Kategorie FROM Urlaubskategorien");
            while (rs.next()) {
                System.out.printf("Kategorie %s %n",
                        rs.getString("Kategorie")
                );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAllRatingsHigherThan(int minRating) {

        try {
            ResultSet rs = executeQuery("SELECT BewertungsId, " +
                    "       UrlaubsId, " +
                    "       Punkte, " +
                    "       Kommentar " +
                    "  FROM Bewertungen " +
                    " WHERE Punkte > " + minRating);
            while (rs.next()) {
                System.out.printf("UrlaubsID: %d BewertungID: %d Punkte: %d %n",
                        rs.getInt("UrlaubsId"),
                        rs.getInt("BewertungsId"),
                        rs.getInt("Punkte"));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printAllRatingsHigherThan2(int minRating) {
        try {
            ResultSet rs = executeQuery("SELECT b.BewertungsId, " +
                    "       b.UrlaubsId, " +
                    "       b.Punkte, " +
                    "       b.Kommentar, " +
                    "       u.Schlagwort " +
                    "  FROM Bewertungen b " +
                    "       INNER JOIN Urlaube u ON b.UrlaubsId = u.UrlaubsID " +
                    " WHERE b.Punkte > " + minRating);
            while (rs.next()) {
                System.out.printf("UrlaubsID: %d Category: %s BewertungID: %d Punkte: %d %n",
                        rs.getInt("UrlaubsId"),
                        rs.getString("Schlagwort"),
                        rs.getInt("BewertungsId"),
                        rs.getInt("Punkte"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //USING PREPARED STATEMENTS

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
    public void displayUrlaubMitID(String query, int id) {
        try {
            PreparedStatement  statement = prepareStatement(query);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                System.out.printf("Schlagwort %s", rs.getString("Schlagwort"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public void printCommentsToReviews(String person) {
        String query = "SELECT KommentarID, BewertungsID, Kommentare, Person " +
                "FROM KommentareZuBewertungen " +
                "WHERE Person = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, person);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("KommentarID %d, BewertungsID %d, Person: %s Kommentar: %s %n",
                            rs.getInt("KommentarID"),
                            rs.getInt("BewertungsID"),
                            rs.getString("Person"),
                            rs.getString("Kommentare")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCategory(String category) {
        String insertString="INSERT INTO Urlaubskategorien (Kategorie) VALUES (?)";
        try
        {
            PreparedStatement pStmt = connection.prepareStatement(insertString);
            pStmt.setString(1,category);

            int affectedRows = pStmt.executeUpdate();

            System.out.printf("Successfully updated %d dataset/s", affectedRows);
        }
        catch(SQLException ex){

        }
    }

    //method to insert category using OOP
    public void insertCategory2(Urlaubskategorien category) {
        String insertString="INSERT INTO Urlaubskategorien (Kategorie) VALUES (?)";
        try
        {
            PreparedStatement pStmt = connection.prepareStatement(insertString);
            pStmt.setString(1, category.getKategorie());

            int affectedRows = pStmt.executeUpdate();

            System.out.printf("Successfully updated %d dataset/s", affectedRows);
        }
        catch(SQLException ex){

        }
    }

    public void updateCategory(int id, String updatedCategory) {
        String updateString = "UPDATE Urlaubskategorien SET Kategorie = ? WHERE ID = ?";
        try
        {
            PreparedStatement pStmt = connection.prepareStatement(updateString);
            pStmt.setString(1,updatedCategory);
            pStmt.setInt(2,id);

            int affectedRows = pStmt.executeUpdate();

            if (affectedRows==1) {
                System.out.printf("Successfully updated category on %d dataset/s", affectedRows);
            }
            if (affectedRows==0) {
                System.out.printf("There is NO dataset affected", id);
            }

        }
        catch(SQLException ex){
        }
    }

    //2. eine Methode im JDBC-Helper um ein Objekt vom Typ Bewertung hinzufügen zu können
    public void insertReview(Bewertungen review) {
        String insertString = "INSERT INTO Bewertungen (UrlaubsId, Punkte, Kommentar) VALUES (?, ?, ?)";
        try {
            PreparedStatement pStmt = connection.prepareStatement(insertString);
            pStmt.setInt(1, review.getUrlaubsId());
            pStmt.setInt(2, review.getPunkte());
            pStmt.setString(3, review.getKommentar());

            int affectedRows = pStmt.executeUpdate();

            System.out.printf("Successfully inserted %d dataset/s%n", affectedRows);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Bewertungen> getAllReviews() {
        List<Bewertungen> reviews = new ArrayList<>();
        try {
            ResultSet rs = executeQuery("SELECT BewertungsId, UrlaubsId, Punkte, Kommentar FROM Bewertungen");
            while (rs.next()) {
                int bewertungsId = rs.getInt("BewertungsId");
                int urlaubsId = rs.getInt("UrlaubsId");
                int punkte = rs.getInt("Punkte");
                String kommentar = rs.getString("Kommentar");

                // Make sure that Bewertungen has a constructor that takes these parameters
                Bewertungen review = new Bewertungen(bewertungsId, urlaubsId, punkte, kommentar);
                reviews.add(review);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

}