package org.example.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.AgeGroup;
import org.example.Child;
import org.example.Competition;
import org.example.interfaces.IChildRepository;
import org.example.JdbcUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChildDBRepository implements IChildRepository {
    private JdbcUtils jdbcUtils;

    private static final Logger logger = LogManager.getLogger();

    public ChildDBRepository(Properties props) {
        logger.traceEntry("Initializing ChildDBRepository with properties: {} ", props);
        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public void add(Child entity) {
        logger.traceEntry("saving task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (var preparedStatement = conn.prepareStatement("insert into child(name, age) values(?,?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Child entity) {}

    @Override
    public void update(Child entity) {}

    @Override
    public Child find(Integer id) {
        return null;
    }

    @Override
    public Iterable<Child> getAll() {
        List<Child> children = new ArrayList<>();
        var conn = jdbcUtils.getConnection();
        logger.traceEntry("Fetching all children");
        try (var preparedStatement = conn.prepareStatement("select * from child")) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getInt("childID");
                var name = resultSet.getString("name");
                var age = resultSet.getInt("age");
                var child = new Child(name, age);
                child.setEntityID(id);
                children.add(child);
                logger.trace("Found child {}", child);
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        logger.traceExit("Found {} children", children.size());
        return children;
    }

    @Override
    public List<Child> searchByCompetitionAndAgeGroup(Competition competition, AgeGroup ageGroup) {
        List<Child> children = new ArrayList<>();
        var conn = jdbcUtils.getConnection();
        logger.traceEntry("Searching children by competition {} and age group {}", competition, ageGroup);
        try (var preparedStatement = conn.prepareStatement("select childID, name, age from child " +
                "join enrollment e on c.childID = e.childID " +
                "join competition comp on e.competitionID = comp.competitionID " +
                "where comp.length = ? and c.minAge = ? and c.maxAge = ?")) {
            preparedStatement.setInt(1, competition.getLenght());
            preparedStatement.setInt(2, ageGroup.getMinAge());
            preparedStatement.setInt(3, ageGroup.getMaxAge());
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getInt("childID");
                var name = resultSet.getString("name");
                var age = resultSet.getInt("age");
                var child = new Child(name, age);
                child.setEntityID(id);
                children.add(child);
                logger.trace("Found child {}", child);
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return children;
    }
}
