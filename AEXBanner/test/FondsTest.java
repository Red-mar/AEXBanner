import org.junit.Before;
import org.junit.Test;
import sample.Classes.Fonds;

import static org.junit.Assert.*;

public class FondsTest {
    private Fonds ahold;

    @Before
    public void setUp() throws Exception {
        ahold = new Fonds("Ahold", 10);
    }

    @Test
    public void getNaam() throws Exception {
        assertEquals(ahold.getNaam(), "Ahold");
    }

    @Test
    public void getKoers() throws Exception {
        assertEquals((int)ahold.getKoers(), 10);
    }

    @Test
    public void setKoers() throws Exception {
        double newKoers = 11;
        ahold.setKoers(newKoers);

        assertEquals((int)ahold.getKoers(), 11);
    }

}