package org.example.dto;

import java.io.Serializable;

public class AgeGroupDTO implements Serializable {
    private final int minAge;
    private final int maxAge;

    public AgeGroupDTO(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public String toString() {
        if (minAge == 5) {
            return "Novici";
        } else if (minAge == 11) {
            return "Juniori";
        } else {
            return "Seniori";
        }
    }
}
