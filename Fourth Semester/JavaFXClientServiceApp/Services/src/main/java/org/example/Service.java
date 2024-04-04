package org.example;

import org.example.interfaces.IChildRepository;
import org.example.interfaces.ICompetitionRepository;
import org.example.interfaces.IEnrollmentRepository;
import org.example.interfaces.IUserRepository;

import java.util.List;

public class Service implements IService {

    private IUserRepository userRepo;
    private IChildRepository childRepo;
    private ICompetitionRepository competitionRepo;
    private IEnrollmentRepository enrollmentRepo;

    public Service(IUserRepository userRepo, IChildRepository childRepo, ICompetitionRepository competitionRepo, IEnrollmentRepository enrollmentRepo) {
        this.userRepo = userRepo;
        this.childRepo = childRepo;
        this.competitionRepo = competitionRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public boolean login(String username, String password) {
        return userRepo.login(username, password);
    }

    @Override
    public void logout(String username) throws ServiceException{};

    public void enroll(Child child, Competition competition) {
        enrollmentRepo.enrollChildToCompetition(child, competition);
    }

    public List<Child> search(Competition competition, AgeGroup ageGroup) {
        return (List<Child>) enrollmentRepo.findChildByCompetitionAndAge(competition, ageGroup);
    }

    public List<Competition> getAllCompetitions() {
        return (List<Competition>) competitionRepo.getAll();
    }

    public List<Child> getAllChildren() {
        return (List<Child>) childRepo.getAll();
    }

    public List<Enrollment> getAllEnrollments() {
        return (List<Enrollment>) enrollmentRepo.getAll();
    }
}