package Reiziger;

import java.sql.Date;

public class Reiziger {

    private int id;
    private String naam;
    private Date gbdatum;

    public Reiziger(int i, String n, Date gbd){
        this.id = i;
        this.naam = n;
        this.gbdatum = gbd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setGbdatum(Date gbdatum) {
        this.gbdatum = gbdatum;
    }

    public Date getGbdatum() {
        return gbdatum;
    }

    @Override
    public String toString() {
        return "Reiziger: " + this.naam + ", " + this.gbdatum.toString() + ", ID: " + this.id;
    }
}
