package org.example.interfaces;


import org.example.*;

import java.util.InputMismatchException;

public interface IEnrollmentRepository extends IRepository<Enrollment> {
    void enrollChildToCompetition(Child child, Competition competition) throws InputMismatchException;

    Iterable <Child> findChildByCompetitionAndAge(Competition competition, AgeGroup ageGroup);
}
