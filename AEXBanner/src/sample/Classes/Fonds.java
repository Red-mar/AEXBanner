package sample.Classes;

public class Fonds implements IFonds {
    private String naam;
    private double koers;

    public Fonds(String naam, double koers) {
        this.koers = koers;
        this.naam = naam;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }

    @Override
    public void setKoers(double koers) {
        this.koers = koers;
    }
}
