package domain;

import java.io.Serializable;

public class Programare extends Entity implements Serializable {
    private String numePacient;
    private String data;
    private String ora;
    private String scop_programre;
    public Programare(int id, String numePacient, String data, String ora, String scop_programre) {
        super(id);
        this.numePacient = numePacient;
        this.data = data;
        this.ora = ora;
        this.scop_programre = scop_programre;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }

    public String getScop_programre() {
        return scop_programre;
    }

    @Override
    public String toString() {
        return "PROGRAMARE: " +
                "id = " + id +
                ", name = " + numePacient +
                ", date = " + data +
                ", hour = " + ora +
                ", purpose = " + scop_programre;
    }
}
