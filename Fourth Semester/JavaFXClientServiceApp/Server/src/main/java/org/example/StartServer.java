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


import java.util.Properties;

public class StartServer {

    private static final Logger logger = LogManager.getLogger();
    private static final int PORT = 1234;

    public static void main(String[] args) {
        logger.traceEntry("Starting server on port {}", PORT);
        Properties serverProps = new Properties();

        try{
            serverProps.load(StartServer.class.getResourceAsStream("/server.properties"));
        }catch (Exception e){
            logger.error(e);
            System.out.println("Error loading server properties "+e);
        }

        IUserRepository userRepo = new UserDBRepository(serverProps);
        IChildRepository childRepo = new ChildDBRepository(serverProps);
        ICompetitionRepository competitionRepo = new CompetitionDBRepository(serverProps);
        IEnrollmentRepository enrollmentRepo = new EnrollmentDBRepository(serverProps);

        IService service = new ServiceImpl(userRepo, childRepo, competitionRepo, enrollmentRepo);

        int serverPort = PORT;
        try{
            serverPort = Integer.parseInt(serverProps.getProperty("server.port"));
        }catch (NumberFormatException ex){
            logger.error(ex);
            System.out.println("Server port is invalid. Using default port " + PORT);
        }

        logger.trace("Server started on port {}", serverPort);

        AbstractServer server = new ConcurrentServer(serverPort, service);

        try{
            server.start();
        }catch (ServerException e){
            logger.error(e);
            System.out.println("Error starting server "+e);
        }finally {
            try{
                server.stop();
            }catch (ServerException e){
                logger.error(e);
                System.out.println("Error stopping server "+e);
            }
        }
    }
}
