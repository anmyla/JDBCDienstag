import models.Bewertungen;
import models.Urlaubskategorien;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Connect.connect();
        //Connect.readCategories();
        //Connect.printMetadata();
        //Connect.displayReviewsOrderByRatings();
        Connect.aufgabeNachmittagMittwoch();


        //WITH JDBCHELPER

        String url = "jdbc:sqlite:/Users/myla/Documents/Java Projects/DBP 1/Databases/Campus02JDBC.db";
        System.out.printf("%nUSING JDBC HELPER %n");


        JDBCHelper helper = new JDBCHelper(url);
        System.out.printf("%nAll Reviews with Helper Class %n");
        helper.printAllBewertungen();
        System.out.printf("%nAll categories with Helper Class %n");
        helper.printKategorien();
        System.out.printf("%nAll Comments on reviews mit Helper Class %n");
        helper.printAlleKommentare();
        System.out.printf("%nAll Holidays with Ratings Higher than (minRating): %n");
        helper.printAllRatingsHigherThan(3);
        System.out.printf("%nAll Holidays with Ratings Higher than (minRating) with INNER JOIN%n");
        helper.printAllRatingsHigherThan2(1);
        System.out.printf("%nUSING PREPARED STATEMENTS: %n");
        //String parameter needs SQL query statement
        System.out.printf("%nString query on parameter: Select holiday category with particular ID %n");
        helper.displayUrlaubMitID("SELECT Schlagwort FROM URLAUBE WHERE UrlaubsID=?",1);
        System.out.println();
        System.out.printf("%nUsing Prepared Statement: Print all comments made by a person %n");
        helper.printCommentsToReviews("yourGod34");
        System.out.printf("%nINSERT: executeUpdate %n");

        //commented out following line to prevent it from executing this in the DB whenever i run main class
      //helper.insertCategory("Safari");
        System.out.printf("%nUPDATE: executeUpdate %n");
        helper.updateCategory(6, "Wellness");
        System.out.println();

        System.out.println("\n----------------------Object Oriented Programming------------------------\n");
        //commenting out following codes to prevent it from executing everytime i run main
        /*
        System.out.println("USING OOP: update category");

        Urlaubskategorien kNeu =new Urlaubskategorien();
        kNeu.setKategorie("Bildung");
        helper.insertCategory2(kNeu);

        System.out.println("USING OOP: update reviews");
        Bewertungen bNeu = new Bewertungen();
        bNeu.setUrlaubsId(6);
        bNeu.setPunkte(12);
        bNeu.setKommentar("ziemlich gut");
        helper.insertReview(bNeu);
        */


        System.out.println("USING OOP: Print all reviews:");

        List<Bewertungen> allReviews = helper.getAllReviews();

        for (Bewertungen review : allReviews) {
            // make sure that Bewertungen class has a useful toString method
            System.out.println(review);
        }

        helper.closeConnection();
    }
}