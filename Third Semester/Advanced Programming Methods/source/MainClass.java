import GUI.JavaFXMain;
import UI.UI;
import domain.IEntityFactory;
import domain.Pacient;
import domain.PacientFactory;
import domain.Programare;
import exceptions.DuplicateElementException;
import repository.*;
import service.ServicePacient;
import service.ServiceProgramare;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class MainClass {
    public static void main(String[] args) {
        IRepository<Pacient> repoPacient = null;
        IRepository<Programare> repoProgramari = null;
        PacientFactory ec = new PacientFactory();

        Settings setari = Settings.getInstance();
        switch (setari.getRepoPacientType()) {
            case "Memory" -> repoPacient = new MemoryRepository<>();

            case "Text" -> {
                try {
                    repoPacient = new TextFileRepository<>(setari.getRepoPacientFile(), ec);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (DuplicateElementException e) {
                    throw new RuntimeException(e);
                }
            }

            case "DB" -> repoPacient = new SQLPacientRepository("myDB.sqlite");
        }

        switch (setari.getRepoAppointmentType()) {
            case "Memory" -> repoProgramari = new MemoryRepository<>();

            case "Binary" -> {
                try {
                    repoProgramari = new BinaryFileRepository<>(setari.getRepoAppointmentFile());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            case "DB" -> repoProgramari = new SQLProgramareRepository("myDB.sqlite");
        }

        ServicePacient servicePacient = new ServicePacient(repoPacient);
        ServiceProgramare serviceProgramare = new ServiceProgramare(repoProgramari);
        String interfata = setari.getInterfaceType();

        switch (interfata) {
            case "GUI" -> JavaFXMain.main(args);
            case "Console" -> {
                UI ui = new UI(servicePacient, serviceProgramare);
                ui.run_menu();
            }
        }
    }
}
