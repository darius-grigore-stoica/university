package org.example;

import java.util.List;

public interface IService {
    boolean login(String username, String password) throws ServiceException;

    void logout(String username) throws ServiceException;

    void enroll(Child child, Competition competition);

    List<Child> search(Competition competition, AgeGroup ageGroup);

    List<Competition> getAllCompetitions() throws ServiceException;

    List<Child> getAllChildren() throws ServiceException;

    List<Enrollment> getAllEnrollments() throws ServiceException;
}
