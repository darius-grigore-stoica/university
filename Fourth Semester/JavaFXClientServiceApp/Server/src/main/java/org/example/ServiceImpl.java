package org.example;

import org.example.interfaces.IChildRepository;
import org.example.interfaces.ICompetitionRepository;
import org.example.interfaces.IEnrollmentRepository;
import org.example.interfaces.IUserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl implements IServices {

    private final IUserRepository userRepo;
    private final IChildRepository childRepo;
    private final ICompetitionRepository competitionRepo;
    private final IEnrollmentRepository enrollmentRepo;

    private Map<String, IObserver> loggedClients;

    public ServiceImpl(IUserRepository userRepo, IChildRepository childRepo, ICompetitionRepository competitionRepo, IEnrollmentRepository enrollmentRepo) {
        this.userRepo = userRepo;
        this.childRepo = childRepo;
        this.competitionRepo = competitionRepo;
        this.enrollmentRepo = enrollmentRepo;
        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void login(User u, IObserver client) throws ServiceException {

        System.out.println("Login request..." + u.getUsername() + " " + u.getPassword());
        boolean valid = userRepo.login(u.getUsername(), u.getPassword());
        if (valid) {
            if (loggedClients.get(u.getUsername()) != null)
                throw new ServiceException("User already logged in.");
            loggedClients.put(u.getUsername(), client);
        } else {
            throw new ServiceException("Authentication failed.");
        }
    }

    @Override
    public synchronized void logout(User u, IObserver client) throws ServiceException {
        IObserver localClient = loggedClients.remove(u.getUsername());
        if (localClient == null)
            throw new ServiceException("User " + u.getUsername() + " is not logged in.");
        else System.out.println("Logout request..." + u.getUsername() + " " + u.getPassword());
    }

    @Override
    public synchronized void enroll(Child child, Competition competition, IObserver client) throws ServiceException {
        enrollmentRepo.enrollChildToCompetition(child, competition);
        notifiyEnrollment(new Enrollment(child.getEntityID(), competition.getEntityID()), client);
    }

    @Override
    public synchronized Child[] search(Competition competition, AgeGroup ageGroup, IObserver client) throws ServiceException {
        try {
            List<Child> children = (List<Child>) enrollmentRepo.findChildByCompetitionAndAge(competition, ageGroup);
            Child[] childrenArray = new Child[children.size()];
            childrenArray = children.toArray(childrenArray);
            return childrenArray;
        } catch (Exception e) {
            throw new ServiceException("Error searching children " + e);
        }
    }

    @Override
    public Competition[] getAllCompetitions(User u, IObserver client) throws ServiceException {
        try {
            List<Competition> competitions = (List<Competition>) competitionRepo.getAll();
            Competition[] competitionsArray = new Competition[competitions.size()];
            competitionsArray = competitions.toArray(competitionsArray);
            return competitionsArray;
        } catch (Exception e) {
            throw new ServiceException("Error getting competitions " + e);
        }

    }

    @Override
    public synchronized Child[] getAllChildren(User u, IObserver client) throws ServiceException {
        try {
            List<Child> children = (List<Child>) childRepo.getAll();
            Child[] childrenArray = new Child[children.size()];
            childrenArray = children.toArray(childrenArray);
            return childrenArray;
        } catch (Exception e) {
            throw new ServiceException("Error getting children " + e);
        }
    }

    @Override
    public synchronized Enrollment[] getAllEnrollments(User u, IObserver client) throws ServiceException {
        try {
            List<Enrollment> enrollments = (List<Enrollment>) enrollmentRepo.getAll();
            Enrollment[] enrollmentsArray = new Enrollment[enrollments.size()];
            enrollmentsArray = enrollments.toArray(enrollmentsArray);
            return enrollmentsArray;
        } catch (Exception e) {
            throw new ServiceException("Error getting enrollments " + e);
        }
    }

    public void notifiyEnrollment(Enrollment e, IObserver obs) {
        int defaultNumberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(defaultNumberOfThreads);
        for (var c : loggedClients.keySet()) {
            executorService.execute(() -> {
                if(loggedClients.get(c) != obs) {
                    var client = loggedClients.get(c);
                    try {
                        client.updateEnrollmentsNotify(e);
                    } catch (ServiceException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        executorService.shutdown();

    }
}
