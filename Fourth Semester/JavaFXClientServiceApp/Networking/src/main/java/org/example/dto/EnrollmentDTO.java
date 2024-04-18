package org.example.dto;

import java.io.Serializable;

public class EnrollmentDTO implements Serializable {
    private Integer childID;
    private Integer competitionID;
    private Integer enrollmentID;

    public EnrollmentDTO(Integer childID, Integer competitionID) {
        this.childID = childID;
        this.competitionID = competitionID;
    }

    public Integer getChildID() {
        return childID;
    }

    public void setChildID(Integer childID) {
        this.childID = childID;
    }

    public Integer getCompetitionID() {
        return competitionID;
    }

    public void setCompetitionID(Integer competitionID) {
        this.competitionID = competitionID;
    }

    public Integer getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(Integer enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    @Override
    public String toString() {
        return "EnrollmentDTO{" +
                "childID=" + childID +
                ", competitionID=" + competitionID +
                ", enrollmentID=" + enrollmentID +
                '}';
    }
}
