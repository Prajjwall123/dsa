package Two;
import java.util.ArrayList;
import java.util.List;

public class Two_b {
    
    public static List<Integer> peoplesecretKnower(int n, int[][] intervals, int firstPerson) {
        // Making a boolean array to track if each person knows the secret or not
        boolean[] secretKnower = new boolean[n];
        
        // Marking the first person as knowing the secret
        secretKnower[firstPerson] = true;

        // Iterating through each interval
        for (int[] interval : intervals) {
            // Iterating through the range of the current interval
            for (int i = interval[0]; i <= interval[1]; i++) {//
                if (secretKnower[i]) {
                    for (int j = interval[0]; j <= interval[1]; j++) {
                        secretKnower[j] = true;
                    }
                    break;
                }
            }
        }

        // Creating a list to store the indices of people who eventually know the secret
        List<Integer> answer = new ArrayList<>();

        // Iterating through all people
        for (int i = 0; i < n; i++) {
            // If the person at index "i" knows the secret, add their index to the answer list
            if (secretKnower[i]) {
                answer.add(i);
            }
        }

        // Returning the list of people who know the secret at the end
        return answer;
    }

    public static void main(String[] args) {
        int n = 5; // Total number of people
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}}; // Secret-sharing intervals represented as a 2D array
        int firstPerson = 0; // Index of the first person who initially possesses the secret

        // Call the method to find the list of people who eventually know the secret
        List<Integer> answer = peoplesecretKnower(n, intervals, firstPerson);

        // Print the list of people who eventually know the secret
        System.out.println(answer);
    }
}
