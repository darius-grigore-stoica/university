import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {


    private static Settings instance;

    private final String repoPacientType;

    private final String repoAppointmentType;

    private final String repoPacientFile;

    private final String repoAppointmentFile;

    private final String interfaceType;

    private Settings(String repoPacientType, String repoPacientFile, String repoAppointmentType, String repoAppointmentFile ,String interfaceType) {
        this.repoPacientType = repoPacientType;
        this.repoPacientFile = repoPacientFile;
        this.repoAppointmentType = repoAppointmentType;
        this.repoAppointmentFile = repoAppointmentFile;
        this.interfaceType = interfaceType;
    }

    public String getRepoPacientType() {
        return repoPacientType;
    }

    public String getRepoAppointmentType() {
        return repoAppointmentType;
    }

    public String getRepoPacientFile() {
        return repoPacientFile;
    }

    public String getRepoAppointmentFile() {
        return repoAppointmentFile;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    private static Properties loadSettings() {
        try (FileReader fr = new FileReader("settings.properties")) {
            Properties settings = new Properties();
            settings.load(fr);
            return settings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Settings getInstance() {
        Properties properties = loadSettings();
        instance = new Settings(properties.getProperty("repo_pacient_type"), properties.getProperty("repo_pacient_file"), properties.getProperty("repo_appointment_type"), properties.getProperty("repo_appointment_file"), properties.getProperty("interface_type"));
        return instance;
    }
}