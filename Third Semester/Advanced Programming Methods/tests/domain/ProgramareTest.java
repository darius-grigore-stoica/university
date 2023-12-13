package domain;

import domain.Programare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramareTest {

    private Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");
    @Test
    void getId() {
        assert p.getId() == 1;
    }

    @Test
    void getNumePacient() {
        assert p.getNumePacient().equals("Popescu");
    }

    @Test
    void getData() {
        assert p.getData().equals("12.12.2020");
    }

    @Test
    void getOra() {
        assert p.getOra().equals("12:00");
    }

    @Test
    void getScop_programre() {
        assert p.getScop_programre().equals("Consultatie");
    }
}