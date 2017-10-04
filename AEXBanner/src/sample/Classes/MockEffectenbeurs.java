package sample.Classes;

import java.util.List;
import java.util.Random;

public class MockEffectenbeurs implements IEffectenbeurs {
    List<IFonds> fonds;

    public MockEffectenbeurs() {
        IFonds test = new Fonds("Test", 10);
        
        fonds.add(test);
    }

    @Override
    public List<IFonds> getKoersen() {
        Random rnd = new Random();

        for (IFonds fond: fonds) {
            fond.setKoers(fond.getKoers() * (rnd.nextInt(100)));
        }

        return fonds;
    }
}
