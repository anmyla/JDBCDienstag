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
        System.out.printf("%nAlle Bewertugen mit Helper %n");
        helper.printAllBewertungen();
        System.out.printf("%nAlle Kategorien mit Helper %n");
        helper.printKategorien();
        System.out.printf("%nAlle Kommentare zu Bewertungen mit Helper %n");
        helper.printAlleKommentare();
        helper.closeConnection();
    }
}