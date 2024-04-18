package org.example.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.*;
import org.example.interfaces.IEnrollmentRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;

public class EnrollmentDBRepository implements IEnrollmentRepository {
    private JdbcUtils jdbcUtils;

    private static final Logger logger = LogManager.getLogger();

    public EnrollmentDBRepository(Properties props) {
        logger.traceEntry("Initializing EnrollmentRepository with properties: {}", props);
        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public void add(Enrollment entity) {
        logger.traceEntry("saving task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("insert into enrollment (childID, competitionID) values (?, ?)")) {
            preStmt.setInt(1, entity.getChildID());
            preStmt.setInt(2, entity.getCompetitionID());
            preStmt.executeUpdate();
            logger.traceExit("saved task {}");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public void remove(Enrollment entity) {
        logger.traceEntry("removing task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("delete from enrollment where enrollmentID = ?")) {
            preStmt.setInt(1, entity.getEntityID());
            preStmt.executeUpdate();
            logger.traceExit("removed task {}");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public void update(Enrollment entity) {
        logger.traceEntry("updating task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("update enrollment set childID = ?, competitionID = ? where enrollmentID = ?")) {
            preStmt.setInt(1, entity.getChildID());
            preStmt.setInt(2, entity.getCompetitionID());
            preStmt.setInt(3, entity.getEntityID());
            preStmt.executeUpdate();
            logger.traceExit("updated task {}");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public Enrollment find(Integer id) {
        var conn = jdbcUtils.getConnection();
        logger.traceEntry("finding task with id {}", id);
        try (PreparedStatement preStmt = conn.prepareStatement("select * from enrollment where enrollmentID = ?")) {
            preStmt.setInt(1, id);
            var resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                var childID = resultSet.getInt("childID");
                var competitionID = resultSet.getInt("competitionID");
                logger.traceExit("found task {}");
                return new Enrollment(childID, competitionID);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        logger.traceExit("no task found with id {}", id);
        return null;
    }

    @Override
    public Iterable<Enrollment> getAll() {
        var conn = jdbcUtils.getConnection();
        logger.traceEntry("Fetching all enrollments");
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        try (PreparedStatement preStmt = conn.prepareStatement("select * from enrollment")) {
            var resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                var childID = resultSet.getInt("childID");
                var competitionID = resultSet.getInt("competitionID");
                Enrollment enrollment = new Enrollment(childID, competitionID);
                enrollment.setEntityID(resultSet.getInt("enrollmentID"));
                enrollments.add(enrollment);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return enrollments;
    }

    @Override
    public void enrollChildToCompetition(Child child, Competition competition) throws InputMismatchException {
        var connection = jdbcUtils.getConnection();
        if (countEnrollmentsOfChild(child) >= 2)
            throw new InputMismatchException("Child is already enrolled in 2 competitions");
        try {
            String insertEnrollmentQuery = "INSERT INTO Enrollment (childID, competitionID) " +
                    "VALUES ((SELECT childID FROM Child WHERE name = ? AND age = ?), " +
                    "(SELECT competition.competitionID FROM Competition WHERE length = ? AND (minAge <= ? AND maxAge >= ?)))";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertEnrollmentQuery)) {
                preparedStatement.setString(1, child.getName());
                preparedStatement.setInt(2, child.getAge());
                preparedStatement.setInt(3, competition.getLenght());
                preparedStatement.setInt(4, competition.getAgeGroup().getMinAge());
                preparedStatement.setInt(5, competition.getAgeGroup().getMaxAge());
                preparedStatement.executeUpdate();
                logger.trace("Enrolled child {} to competition {}", child.getName(), competition.getLenght());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int countEnrollmentsOfChild(Child child) {
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("select count(*) from enrollment where childID = (select childID from child where name = ? and age = ?)")) {
            preStmt.setString(1, child.getName());
            preStmt.setInt(2, child.getAge());
            var resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return 0;
    }

    @Override
    public Iterable<Child> findChildByCompetitionAndAge(Competition competition, AgeGroup ageGroup) {
        var conn = jdbcUtils.getConnection();
        ArrayList<Child> children = new ArrayList<>();
        logger.traceEntry("searching children by competition and age");
        try (PreparedStatement preStmt = conn.prepareStatement("select * from child where childID in (select childID from enrollment where competitionID in (select competitionID from competition where length = ?)) and age between ? and ?")) {
            preStmt.setInt(1, competition.getLenght());
            preStmt.setInt(2, ageGroup.getMinAge());
            preStmt.setInt(3, ageGroup.getMaxAge());
            var resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getInt("childID");
                var name = resultSet.getString("name");
                var age = resultSet.getInt("age");

                Child child = new Child(name, age);
                child.setEntityID(id);
                children.add(child);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return children;
    }
}
