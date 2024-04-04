package org.example;

public class Competition implements Entity<Integer> {
    private Integer lenght;

    private Integer competitionID;

    private AgeGroup ageGroup;

    public Competition(Integer lenght, AgeGroup ageGroup) {
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

    @Override
    public void setEntityID(Integer integer) {
        this.competitionID = integer;
    }

    @Override
    public Integer getEntityID() {
        return this.competitionID;
    }


    @Override
    public String toString() {
        return "Competition = (" +
                "lenght =" + lenght +
                ", ageGroup = " + ageGroup +
                ", competitionID = " + competitionID + ')';
    }
}
