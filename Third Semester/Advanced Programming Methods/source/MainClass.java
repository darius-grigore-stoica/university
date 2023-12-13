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
import java.util.Properties;

public class MainClass{
    public static void main(String[] args) {
        Properties properties = new Properties();
        InputStream read_prop;
        try {
            read_prop = new FileInputStream("settings.properties");
            properties.load(read_prop);
            IRepository<Pacient> repoPacient = null;
            IRepository<Programare> repoProgramari = null;
            String pacient = properties.getProperty("Repository_Pacient");
            switch (pacient) {
                case "Text" -> {
                    IEntityFactory<Pacient> PacientFactory = new PacientFactory();
                    try {
                        repoPacient = new TextFileRepository<>(properties.getProperty("Pacienti"), PacientFactory);
                    } catch (DuplicateElementException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "Memory" -> repoPacient = new MemoryRepository<>();
            }
            String programari = properties.getProperty("Repository_Programari");
            switch (programari) {
                case "Binary" -> {
                    try {
                        repoProgramari = new BinaryFileRepository<>(properties.getProperty("Programari"));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "Memory" -> repoPacient = new MemoryRepository<>();
            }

            ServicePacient servicePacient = new ServicePacient(repoPacient);
            ServiceProgramare serviceProgramare = new ServiceProgramare(repoProgramari);

            UI ui = new UI(servicePacient, serviceProgramare);
            ui.run_menu();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
