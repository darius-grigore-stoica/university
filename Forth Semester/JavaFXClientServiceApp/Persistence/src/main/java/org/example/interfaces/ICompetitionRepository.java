package org.example.interfaces;


import org.example.Competition;
import org.example.IRepository;

import java.util.ArrayList;

public interface ICompetitionRepository extends IRepository<Competition> {
    ArrayList<String> getAgeGroups();
}
