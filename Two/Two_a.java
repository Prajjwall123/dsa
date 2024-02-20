package Two;

public class Two_a {


    public static int minMoves(int[] machines) {
        int totalDresses = 0;
        int noOfMachine = machines.length;//note teh number of dresses in each machine.

        // calcilate the total number of dresses
        for (int dresses : machines) {
            totalDresses += dresses;
        }

        // to check if we can equally distribute the dresses in machines or not.
        if (totalDresses % noOfMachine != 0) {
            return -1;
        }

        // to check how many dresses we can fit in a machine(now that we know we can distribute them equally)
        int dressesPerMachine = totalDresses / noOfMachine;


        int moves = 0;
        for (int i = 0; i < noOfMachine - 1; i++) {//since we started from 0 we say until noOfMachine-1
            int diff = dressesPerMachine - machines[i];
            if (diff > 0) {
                int shift = Math.min(diff, machines[i + 1]);
                machines[i] += shift;
                machines[i + 1] -= shift;
                moves += shift;
            }
        }

        return moves;
    }
    public static void main(String[] args) {
        int[] dressArray = { 1, 0, 5 };
        int answer=minMoves(dressArray);
        System.out.println(answer);
    }
}