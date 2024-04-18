package org.example.dto;

import org.example.*;

public class DTOUtils {
    public static EnrollmentDTO getEnrollmentDTO(Enrollment e) {
        return new EnrollmentDTO(e.getChildID(), e.getCompetitionID());
    }

    public static Enrollment getEnrollmentFromDTO(EnrollmentDTO enrollmentDTO) {
        return new Enrollment(enrollmentDTO.getChildID(), enrollmentDTO.getCompetitionID());
    }

    public static Enrollment[] getEnrollmentsFromDTO(EnrollmentDTO[] enrollmentDTO) {
        Enrollment[] enrollments = new Enrollment[enrollmentDTO.length];
        for (int i = 0; i < enrollmentDTO.length; i++) {
            enrollments[i] = getEnrollmentFromDTO(enrollmentDTO[i]);
        }
        return enrollments;
    }

    public static UserDTO getUserDTO(User u) {
        return new UserDTO(u.getUsername(), u.getPassword());
    }

    public static User getUserFromDTO(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword());
    }

    public static ChildDTO getChildDTO(Child child) {
        return new ChildDTO(child.getName(), child.getAge());
    }

    public static Child getChildFromDTO(ChildDTO childDTO) {
        return new Child(childDTO.getName(), childDTO.getAge());
    }

    public static Child[] getChildrenFromDTO(ChildDTO[] childDTO) {
        Child[] children = new Child[childDTO.length];
        for (int i = 0; i < childDTO.length; i++) {
            children[i] = getChildFromDTO(childDTO[i]);
        }
        return children;
    }
    public static CompetitionDTO getCompetitionDTO(Competition competition) {
        return new CompetitionDTO(competition.getLenght(), competition.getAgeGroup());
    }

    public static AgeGroupDTO getAgeGroupDTO(AgeGroup ageGroup) {
        return new AgeGroupDTO(ageGroup.getMinAge(), ageGroup.getMaxAge());
    }

    public static AgeGroup getAgeGroupFromDTO(AgeGroupDTO ageGroupDTO) {
        if(ageGroupDTO.getMaxAge() == 10)
            return AgeGroup.NOVICI;
        if(ageGroupDTO.getMaxAge() == 15)
            return AgeGroup.JUNIORI;
        return AgeGroup.SENIORI;

    }

    public static Competition getCompetitionFromDTO(CompetitionDTO competitionDTO) {
        return new Competition(competitionDTO.getLenght(), competitionDTO.getAgeGroup());
    }

    public static Competition[] getCompetitionFromDTO(CompetitionDTO[] competitionDTO) {
        Competition[] competitions = new Competition[competitionDTO.length];
        for (int i = 0; i < competitionDTO.length; i++) {
            competitions[i] = getCompetitionFromDTO(competitionDTO[i]);
        }
        return competitions;
    }
}