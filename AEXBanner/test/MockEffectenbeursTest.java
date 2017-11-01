import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Classes.IFonds;
import sample.Classes.MockEffectenbeurs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MockEffectenbeursTest {
    MockEffectenbeurs effectenbeurs;

    @BeforeEach
    void setUp() {
        try {
            effectenbeurs = new MockEffectenbeurs();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getKoersen() throws Exception {
        ArrayList<IFonds> fonds = (ArrayList<IFonds>) effectenbeurs.getKoersen();
        assertEquals(fonds.get(0).getNaam(), "Test");
        assertEquals(fonds.get(1).getNaam(), "Bedrijf A");
    }

}