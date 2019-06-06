package Adres;

import Reiziger.Reiziger;

public class Adres {
    private int adresID;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private Reiziger reiziger;

    public Adres(){}

    public Adres(int adresID, String postcode, String huisnummer, String straat, String woonplaats){
        this.adresID = adresID;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public Adres(int adresID, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger){
        this(adresID, postcode, huisnummer, straat, woonplaats);
        this.reiziger = reiziger;
    }

    public int getAdresID() {
        return this.adresID;
    }

    public void setAdresID(int adresID) {
        this.adresID = adresID;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return this.huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return this.straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return this.woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Reiziger getReiziger() {
        return this.reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }
}
