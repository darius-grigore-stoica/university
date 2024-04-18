package org.example;

import java.util.List;

public interface IServices {
    void login(User u, IObserver client) throws ServiceException;

    void logout(User u, IObserver client) throws ServiceException;

    void enroll(Child child, Competition competition, IObserver client) throws ServiceException;
    Child[] search(Competition competition, AgeGroup ageGroup, IObserver client) throws ServiceException;

    Competition[] getAllCompetitions(User u, IObserver client) throws ServiceException;

    Child[] getAllChildren(User u, IObserver client) throws ServiceException;

    Enrollment[] getAllEnrollments(User u, IObserver client) throws ServiceException;

}
