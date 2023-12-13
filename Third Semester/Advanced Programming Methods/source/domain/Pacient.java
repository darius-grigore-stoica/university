package domain;

public class Pacient extends Entity{
    private String nume;
    private String prenume;
    private int varsta;

    public Pacient(int id, String nume, String prenume, int varsta) {
        super(id);
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    @Override
    public String toString() {return "PACIENT: " + "id = " + id + ", name = " + nume +", surname = " + prenume + ", age = " + varsta;}
}
