package domain;

import java.io.FileWriter;

public class PacientFactory implements IEntityFactory<Pacient> {
    @Override
    public Pacient createEntity(String line) {
        String[] tokens = line.split(",");
        return new Pacient(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Integer.parseInt(tokens[3]));
    }

    @Override
    public String saveEntity(Pacient pacient) {
        try {
            return (pacient.getId() + "," + pacient.getNume() + "," + pacient.getPrenume() + "," + pacient.getVarsta() + "\n");} catch (Exception e) {e.printStackTrace();}return null;
    }
}


