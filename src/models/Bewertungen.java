package models;

//1. Aufgabe - Klasse Bewertung mit Properties erstellen, BewertungsId, UrlaubsId, Punkte, Kommentar
public class Bewertungen {
    private int BewertungsId;
    private int UrlaubsId;
    private int Punkte;
    private String Kommentar;

    public Bewertungen() {
    }

    public Bewertungen(int bewertungsId, int urlaubsId, int punkte, String kommentar) {
        BewertungsId = bewertungsId;
        UrlaubsId = urlaubsId;
        Punkte = punkte;
        Kommentar = kommentar;
    }



    public int getBewertungsId() {
        return BewertungsId;
    }

    public void setBewertungsId(int bewertungsId) {
        BewertungsId = bewertungsId;
    }

    public int getUrlaubsId() {
        return UrlaubsId;
    }

    public void setUrlaubsId(int urlaubsId) {
        UrlaubsId = urlaubsId;
    }

    public int getPunkte() {
        return Punkte;
    }

    public void setPunkte(int punkte) {
        Punkte = punkte;
    }

    public String getKommentar() {
        return Kommentar;
    }

    public void setKommentar(String kommentar) {
        Kommentar = kommentar;
    }

    @Override
    public String toString() {
        return String.format("BewertungsID: %d, UrlaubsId: %d Punkte: %d, Kommentar: %s",
                BewertungsId, UrlaubsId, Punkte, Kommentar);
    }
}
