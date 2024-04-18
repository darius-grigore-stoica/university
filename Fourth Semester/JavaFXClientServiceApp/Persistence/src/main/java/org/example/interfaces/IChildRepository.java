package org.example.interfaces;

import org.example.AgeGroup;
import org.example.Child;
import org.example.Competition;
import org.example.IRepository;

import java.util.List;

public interface IChildRepository extends IRepository<Child> {
    List<Child> searchByCompetitionAndAgeGroup(Competition competition, AgeGroup ageGroup);
}
