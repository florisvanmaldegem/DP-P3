package Reiziger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl implements ReizigerDAO{

    private ArrayList<Reiziger> reizigers;

    public ReizigerOracleDaoImpl(){
        this.reizigers = new ArrayList<>();
    }

    @Override
    public ArrayList<Reiziger> findAll() {
        return this.reizigers;
    }

    @Override
    public Reiziger findById(int id) {
        for(Reiziger r : this.reizigers){
            if(r.getId() == id){
                return r;
            }
        }
        return new Reiziger(0, "test", java.sql.Date.valueOf("2000-01-01"));
    }

    @Override
    public List<Reiziger> findByGbDatum(String gbDatum) {
        ArrayList<Reiziger> rl = new ArrayList<>();

        for(Reiziger r : this.reizigers){
            if(r.getGbdatum().equals(java.sql.Date.valueOf(gbDatum))){
                rl.add(r);
            }
        }
        return rl;
    }

    @Override
    public List<Reiziger> findByName(String naam) {
        ArrayList<Reiziger> rl = new ArrayList<>();

        for(Reiziger r : this.reizigers){
            if(r.getNaam().toUpperCase().equals(naam.toUpperCase())){
                rl.add(r);
            }
        }

        return rl;
    }

    @Override
    public Reiziger save(Reiziger reiziger) {
        if(!this.reizigers.contains(reiziger)) {
            for(Reiziger r : this.reizigers){
                if(r.getId() == reiziger.getId()){
                    return reiziger;
                }
            }
            this.reizigers.add(reiziger);
        }

        return reiziger;
    }

    @Override
    public Boolean delete(Reiziger reiziger) {
        if(this.reizigers.contains(reiziger)){
            this.reizigers.remove(reiziger);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Reiziger update(Reiziger reiziger, int id){
        for(Reiziger r : this.reizigers){
            if(r.getId() == id){
                this.delete(reiziger);
                reiziger.setId(id);
                this.reizigers.set(this.reizigers.indexOf(r), reiziger);
                return reiziger;
            }
        }
        return new Reiziger(0,"naam", java.sql.Date.valueOf(LocalDate.now()));
    }

    @Override
    public void closeConnection() {
        this.reizigers = new ArrayList<Reiziger>();
        System.out.println("closed connection");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alle reizigers: ");
        for(Reiziger r : this.reizigers){
            sb.append("\t").append(r.toString());
        }
        return sb.toString();
    }
}
