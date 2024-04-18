package org.example;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.example.interfaces.IChildRepository;
import org.example.interfaces.ICompetitionRepository;
import org.example.interfaces.IEnrollmentRepository;
import org.example.interfaces.IUserRepository;

import org.example.implementations.ChildDBRepository;
import org.example.implementations.CompetitionDBRepository;
import org.example.implementations.EnrollmentDBRepository;
import org.example.implementations.UserDBRepository;
import org.example.utils.AbstractServer;
import org.example.utils.ConcurrentServer;


import java.rmi.ServerException;
import java.util.Properties;

public class StartServer {

    private static final Logger logger = LogManager.getLogger();
    private static final int PORT = 55556;

    public static void main(String[] args) {
        Properties serverProps = new Properties();

        try {
            serverProps.load(StartServer.class.getResourceAsStream("/server.properties"));
            serverProps.list(System.out);
        } catch (Exception e) {
            logger.error(e);
            System.out.println("Error loading server properties " + e);
        }

        IUserRepository userRepo = new UserDBRepository(serverProps);
        IChildRepository childRepo = new ChildDBRepository(serverProps);
        ICompetitionRepository competitionRepo = new CompetitionDBRepository(serverProps);
        IEnrollmentRepository enrollmentRepo = new EnrollmentDBRepository(serverProps);

        IServices service = new ServiceImpl(userRepo, childRepo, competitionRepo, enrollmentRepo);

        int serverPort = PORT;
        try {
            serverPort = Integer.parseInt(serverProps.getProperty("server.port"));
        } catch (NumberFormatException ex) {
            System.out.println("Server port is invalid. Using default port " + PORT);
        }

        AbstractServer server = new ConcurrentServer(serverPort, service);

        try {
            server.start();
        } finally {
            try {
                server.stop();
            } catch (ServerException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
