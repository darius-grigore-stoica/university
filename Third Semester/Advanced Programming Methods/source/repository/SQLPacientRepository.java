package repository;

import domain.Pacient;
import exceptions.DuplicateElementException;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class SQLPacientRepository extends MemoryRepository<Pacient> {

    private String dbLocation;

    private Connection connection;

    public SQLPacientRepository(String dbLocation) {
        super();
        this.dbLocation = "jdbc:sqlite:" + dbLocation;
        openConnection();
        createTable();
        loadDataFromDB();
        //initData();
    }

    private void initData() {
        int id = 100;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            id++;
            String name = generateRandomName();
            String surname = generateRandomSurname();
            int age = random.nextInt(100); // Adjust the range as needed

            Pacient p = new Pacient(id, name, surname, age);
            try {
                super.add(p);
                try(final Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate("INSERT INTO pacienti VALUES (" + p.getId() + ", '" + p.getNume() + "', '" + p.getPrenume() + "', " + p.getVarsta() + ")");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (DuplicateElementException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static String generateRandomName() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hank", "Ivy", "Jack"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }


    static String generateRandomSurname() {
        String[] surnames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
        Random random = new Random();
        return surnames[random.nextInt(surnames.length)];
    }

    private void loadDataFromDB() {
        this.entities.addAll(getAll());
    }

    @Override
    public ArrayList<Pacient> getAll() {
        ArrayList<Pacient> pacienti = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pacienti")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pacient p = new Pacient(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getInt("age"));
                pacienti.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacienti;
    }

    @Override
    public void add(Pacient p) throws DuplicateElementException {
        super.add(p);
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO pacienti VALUES (" + p.getId() + ", '" + p.getNume() + "', '" + p.getPrenume() + "', " + p.getVarsta() + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int pos, Pacient p) {
        super.update(pos, p);
        try (final Statement stms = connection.createStatement()) {
            stms.executeUpdate("UPDATE pacienti SET name = '" + p.getNume() + "', surname = '" + p.getPrenume() + "', age = " + p.getVarsta() + " WHERE id = " + p.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        super.delete(id);
        try (final Statement stms = connection.createStatement()) {
            stms.executeUpdate("DELETE FROM pacienti WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pacienti (id int, name varchar(50), surname varchar(50), age int)");
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
