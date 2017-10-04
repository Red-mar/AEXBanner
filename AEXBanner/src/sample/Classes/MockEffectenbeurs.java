package sample.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockEffectenbeurs implements IEffectenbeurs {
    List<IFonds> fonds;

    public MockEffectenbeurs() {
        IFonds test = new Fonds("Test", 10);
        IFonds bedrijf_a = new Fonds("Bedrijf A", 100);

        fonds = new ArrayList<>();
        fonds.add(test);
        fonds.add(bedrijf_a);
    }

    @Override
    public List<IFonds> getKoersen() {
        Random rnd = new Random();

        for (IFonds fond: fonds) {
            fond.setKoers(fond.getKoers() * (rnd.nextDouble()));
        }

        return fonds;
    }
}
