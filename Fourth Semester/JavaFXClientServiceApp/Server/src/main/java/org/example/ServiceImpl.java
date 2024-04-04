package org.example;

import org.example.interfaces.IChildRepository;
import org.example.interfaces.ICompetitionRepository;
import org.example.interfaces.IEnrollmentRepository;
import org.example.interfaces.IUserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceImpl implements IService{

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
    public synchronized boolean login(String username, String password) throws ServiceException {
        boolean valid = userRepo.login(username, password);
        if(valid) {
            if(loggedClients.get(username) != null)
                throw new ServiceException("User already logged in.");
            loggedClients.put(username, null);
            return true;
        } else {
            throw new ServiceException("Authentication failed.");
        }
    }

    @Override
    public void logout(String username) throws ServiceException {
        IObserver localClient = loggedClients.remove(username);
        if (localClient == null) {
            throw new ServiceException("User " + username + " is not logged in.");
        }
    }

    @Override
    public void enroll(Child child, Competition competition) {

    }

    @Override
    public List<Child> search(Competition competition, AgeGroup ageGroup) {
        return null;
    }

    @Override
    public List<Competition> getAllCompetitions() throws ServiceException {
        try {
           return (List<Competition>) competitionRepo.getAll();
        } catch (Exception e) {
            throw new ServiceException("Error getting competitions: " + e.getMessage());
        }
    }

    @Override
    public List<Child> getAllChildren() throws ServiceException {
        try{
            return (List<Child>) childRepo.getAll();
        } catch (Exception e) {
            throw new ServiceException("Error getting children: " + e.getMessage());
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() throws ServiceException {
        try {
            return (List<Enrollment>) enrollmentRepo.getAll();
        } catch (Exception e) {
            throw new ServiceException("Error getting enrollments: " + e.getMessage());
        }
    }

    public synchronized void logout(String username, IObserver client) throws ServiceException {
        IObserver localClient = loggedClients.remove(username);
        if (localClient == null)
            throw new ServiceException("User " + username + " is not logged in.");
    }
}
