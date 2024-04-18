package org.example.dto;

import org.example.AgeGroup;

import java.io.Serializable;

public class CompetitionDTO implements Serializable {
    private Integer lenght;
    private Integer competitionID;

    private AgeGroup ageGroup;

    public CompetitionDTO(Integer lenght, AgeGroup ageGroup) {
        this.lenght = lenght;
        this.ageGroup = ageGroup;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getCompetitionID() {
        return competitionID;
    }

    public void setCompetitionID(Integer competitionID) {
        this.competitionID = competitionID;
    }

    @Override
    public String toString() {
        return "CompetitionDTO{" +
                "lenght=" + lenght +
                ", competitionID=" + competitionID +
                ", ageGroup=" + ageGroup +
                '}';
    }
}
