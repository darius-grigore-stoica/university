package repository;

import domain.Programare;
import exceptions.DuplicateElementException;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SQLProgramareRepository extends MemoryRepository<Programare> {

    private String dbLocation;

    private Connection connection;

    public SQLProgramareRepository(String dbLocation) {
        super();
        this.dbLocation = "jdbc:sqlite:" + dbLocation;
        openConnection();
        createTable();
        loadDataFromDB();
        //initData();
    }

    private void initData() {
        int id = 100;
        for (int i = 0; i < 100; i++) {
            id++;
            String name = generateRandomName();
            String data = generateRandomData();
            String ora = generateRandomOra();;

            Programare p = new Programare(id, name, data, ora, "");
            try {
                super.add(p);
                try (final Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate("INSERT INTO programari VALUES (" + p.getId() + ", '" + p.getNumePacient() + "', '" + p.getData() + "', '" + p.getOra() + "', '" + " " + "')");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (DuplicateElementException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String generateRandomOra() {
        StringBuilder ora = new StringBuilder();
        ora.append(generateRandomHour());
        ora.append(":");
        ora.append(Arrays.toString(generateRandomMinute()));
        return ora.toString();
    }

    private String[] generateRandomMinute() {
        String[] minutes = {"05", "15", "25", "30", "40", "50"};
        Random random = new Random();
        return new String[]{minutes[random.nextInt(minutes.length)]};
    }

    private char[] generateRandomHour() {
        char[] hours = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random random = new Random();
        return new char[]{hours[random.nextInt(hours.length)]};
    }

    private String generateRandomData() {
        StringBuilder date = new StringBuilder();
        date.append(generateRandomDay());
        date.append("/");
        date.append(generateRandomMonth());
        date.append("/");
        date.append(generateRandomYear());
        return date.toString();
    }

    private char[] generateRandomYear() {
        char[] years = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random random = new Random();
        return new char[]{years[random.nextInt(years.length)]};
    }

    private char[] generateRandomMonth() {
        char[] months = {'0', '1', '2', '3', '4', '5', '6', '7', '8'};
        Random random = new Random();
        return new char[]{months[random.nextInt(months.length)]};
    }

    private char[] generateRandomDay() {
        char[] days = {'0', '1', '2', '3'};
        Random random = new Random();
        return new char[]{days[random.nextInt(days.length)]};
    }

    static String generateRandomName() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hank", "Ivy", "Jack"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    private void loadDataFromDB() {
        this.entities.addAll(getAll());
    }

    @Override
    public ArrayList<Programare> getAll() {
        ArrayList<Programare> programare = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM programari")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Programare p = new Programare(rs.getInt("id"), rs.getString("pacient_name"), rs.getString("data"), rs.getString("ora"), rs.getString("scop_programare"));
                programare.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return programare;
    }

    @Override
    public void add(Programare p) throws DuplicateElementException {
        super.add(p);
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO programari VALUES (" + p.getId() + ", '" + p.getNumePacient() + "', '" + p.getData() + "', '" + p.getOra() + "', '" + p.getScop_programre() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int pos, Programare e) throws IllegalArgumentException {
        super.update(pos, e);
        try (final Statement stms = connection.createStatement()) {
            stms.executeUpdate("UPDATE programari SET pacient_name = '" + e.getNumePacient() + "', data = '" + e.getData() + "', ora = '" + e.getOra() + "', scop_programare = '" + e.getScop_programre() + "' WHERE id = " + e.getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(int id) {
        super.delete(id);
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM programari WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS programari (id int, pacient_name nvarchat(50), data nvarchar(50), ora nvarchar(50), scop_programare nvarchar(50))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(dbLocation);

            if (connection == null || connection.isClosed())
                connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating DB connection", e);
        }
    }
}
