package org.example;

public enum AgeGroup {
   NOVICI(5, 10),
   JUNIORI(11, 15),
   SENIORI(16, 18);

   private final int minAge;
   private final int maxAge;

    AgeGroup(int minAge, int maxAge) {
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
