package org.example;

public class Enrollment implements Entity<Integer>{
    private Integer competitionID;
    private Integer childID;
    private Integer enrollmentID;

    public Enrollment(Integer competitionID, Integer childID) {
        this.competitionID = competitionID;
        this.childID = childID;
    }

    @Override
    public void setEntityID(Integer integer) {
        this.enrollmentID = integer;
    }

    public Integer getEntityID() {
        return enrollmentID;
    }

    public Integer getCompetitionID() {
        return competitionID;
    }

    public Integer getChildID() {
        return childID;
    }

    public void setCompetitionID(Integer competitionID) {
        this.competitionID = competitionID;
    }

    public void setChildID(Integer childID) {
        this.childID = childID;
    }

    @Override
    public String toString() {
        return "Enrollment = " +
                "competitionID =" + competitionID +
                ", childID =" + childID +
                ", enrollmentID =" + enrollmentID +
                ')';
    }
}
