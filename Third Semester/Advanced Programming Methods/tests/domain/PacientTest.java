package domain;
import domain.Pacient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacientTest {
    private Pacient p = new Pacient(1, "Popescu", "Ion", 20);
    @Test
    void getId() {
        assert p.getId() == 1;
    }

    @Test
    void getNume() {
        assert p.getNume().equals("Popescu");
    }

    @Test
    void getPrenume() {
        assert p.getPrenume().equals("Ion");
    }

    @Test
    void getVarsta() {
        assert p.getVarsta() == 20;
    }

    @Test
    void testToString() {
        assert p.toString().equals("PACIENT: " + "id = " + 1 + ", name = " + "Popescu" +", surname = " + "Ion" + ", age = " + 20);
    }
}