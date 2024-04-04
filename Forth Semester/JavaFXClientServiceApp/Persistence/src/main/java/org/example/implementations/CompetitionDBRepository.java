package org.example.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.AgeGroup;
import org.example.Competition;
import org.example.interfaces.ICompetitionRepository;
import org.example.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CompetitionDBRepository implements ICompetitionRepository {
    private JdbcUtils jdbcUtils;

    private static final Logger logger = LogManager.getLogger();

    public CompetitionDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ", props);
        jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public void add(Competition entity) {
        logger.traceEntry("saving task {}", entity);
        Connection conn = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("insert into competition(length, minAge, maxAge) values (?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getLenght());
            preparedStatement.setInt(2, entity.getAgeGroup().getMinAge());
            preparedStatement.setInt(3, entity.getAgeGroup().getMaxAge());
            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Competition entity) {
        logger.traceEntry("removing task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("delete from competition where competitionID = ?")) {
            preStmt.setInt(1, entity.getEntityID());
            preStmt.executeUpdate();
            logger.traceExit("removed task {}");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public void update(Competition entity) {
        logger.traceEntry("updating task {}", entity);
        var conn = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("update competition set length = ?, minAge = ?, maxAge = ? where competitionID = ?")) {
            preStmt.setInt(1, entity.getLenght());
            preStmt.setInt(2, entity.getAgeGroup().getMinAge());
            preStmt.setInt(3, entity.getAgeGroup().getMaxAge());
            preStmt.setInt(4, entity.getEntityID());
            preStmt.executeUpdate();
            logger.trace("Updated task {}", entity);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public Competition find(Integer id) {
        return null;
    }

    @Override
    public Iterable<Competition> getAll() {
        logger.traceEntry("Fetching all competitions");
        Connection conn = jdbcUtils.getConnection();
        List<Competition> competitions = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from competition")) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("competitionID");
                    int length = rs.getInt("length");
                    int minAge = rs.getInt("minAge");
                    Competition c;
                    if (minAge == AgeGroup.NOVICI.getMinAge()) {
                        c = new Competition(length, AgeGroup.NOVICI);
                        c.setEntityID(id);
                    } else if (minAge == AgeGroup.JUNIORI.getMinAge()) {
                        c = new Competition(length, AgeGroup.JUNIORI);
                        c.setEntityID(id);
                    } else {
                        c = new Competition(length, AgeGroup.SENIORI);
                        c.setEntityID(id);
                    }
                    competitions.add(c);
                    logger.trace("Found competition {}", c);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.traceExit("Found {} competitions", competitions.size());
        return competitions;
    }

    @Override
    public ArrayList<String> getAgeGroups() {
        logger.traceEntry("Fetching all age groups");
        Connection conn = jdbcUtils.getConnection();
        ArrayList<String> ageGroups = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select distinct minAge from competition")) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int minAge = rs.getInt("minAge");
                    if (minAge == AgeGroup.NOVICI.getMinAge()) {
                        ageGroups.add("Novici");
                    } else if (minAge == AgeGroup.JUNIORI.getMinAge()) {
                        ageGroups.add("Juniori");
                    } else {
                        ageGroups.add("Seniori");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.traceExit("Found {} age groups", ageGroups.size());
        return ageGroups;
    }
}
