package Reiziger;

import OVchipkaart.OVchipkaart;

import java.sql.*;
import java.util.ArrayList;

public class Reiziger {

    private int reizigerId;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private ArrayList<OVchipkaart> ovchipkaarten;

    public Reiziger(){
        this.ovchipkaarten = new ArrayList<OVchipkaart>();
    }

    public int getReizigerID() {
        return this.reizigerId;
    }

    public void setReizigerID(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public String getVoorletters() {
        return this.voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return this.tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return this.achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return this.geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public ArrayList<OVchipkaart> getOvchipkaarten() {
        return this.ovchipkaarten;
    }

    public boolean voegOVChipkaartToe(OVchipkaart oVchipkaart){
        if(!this.ovchipkaarten.contains(oVchipkaart)){
            return this.ovchipkaarten.add(oVchipkaart);
        }else{
            return false;
        }
    }

    public boolean verwijderOVChipkaart(OVchipkaart oVchipkaart){
        return this.ovchipkaarten.remove(oVchipkaart);
    }

    public String getNaam(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.voorletters).append(" ");
        if(this.tussenvoegsel != null){
            sb.append(this.tussenvoegsel).append(" ");
        }
        sb.append(this.achternaam);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNaam());
        sb.append("(");
        sb.append(this.getGeboortedatum().toLocalDate().toString());
        sb.append(")");
        if(!this.ovchipkaarten.isEmpty()){
            sb.append(" met OV chipkaarten: ");
            for(OVchipkaart o : this.ovchipkaarten){
                sb.append("\n\t");
                sb.append(o);
            }
        }

        return sb.toString();
    }
}
