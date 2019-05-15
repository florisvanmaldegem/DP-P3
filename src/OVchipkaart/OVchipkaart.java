package OVchipkaart;

import Reiziger.Reiziger;

import java.sql.*;

public class OVchipkaart {

    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private float saldo;
    private Reiziger eigenaar;

    public OVchipkaart(){

    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Reiziger getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Reiziger eigenaar) {
        this.eigenaar = eigenaar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Kaartnr: ").append(this.kaartNummer);
        sb.append("; Saldo: €").append(this.saldo);
        sb.append("; Klasse: ").append(this.klasse);
        sb.append("; Geldig tot: ").append(this.geldigTot.toLocalDate());
        return sb.toString();
    }
}
