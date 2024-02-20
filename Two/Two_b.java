package Two;

import java.util.ArrayList;
import java.util.List;

// Class for managing secret sharing among peoples
public class Two_b {
    
    // Method to determine peoples who eventually know the secret
    public static List<Integer> findSecretKnowers(int totalPeople, int[][] sharingIntervals, int initialPerson) {
        // Boolean array to track if each person knows the secret or not
        boolean[] secretKnowers = new boolean[totalPeople];
        
        // Marking the initial person as knowing the secret
        secretKnowers[initialPerson] = true;

        // Iterating through each sharing interval
        for (int[] interval : sharingIntervals) {
            // Iterating through the range of the current interval
            for (int i = interval[0]; i <= interval[1]; i++) {
                if (secretKnowers[i]) {
                    for (int j = interval[0]; j <= interval[1]; j++) {
                        secretKnowers[j] = true;
                    }
                    break;
                }
            }
        }

        // List to store indices of people who eventually know the secret
        List<Integer> knowersList = new ArrayList<>();

        // Iterating through all peoples
        for (int i = 0; i < totalPeople; i++) {
            // If the people at index "i" knows the secret, add their index to the list
            if (secretKnowers[i]) {
                knowersList.add(i);
            }
        }

        return knowersList;
    }

    public static void main(String[] args) {
        int totalPeople = 5;
        int[][] sharingIntervals = {{0, 2}, {1, 3}, {2, 4}};
        int initialPerson = 0; 

        
        List<Integer> result = findSecretKnowers(totalPeople, sharingIntervals, initialPerson);

        
        System.out.println(result);
    }
}
