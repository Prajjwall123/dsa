package Two;

public class Two_a {

    public static int minanswerToEqualizedress(int[] sewingMachines) {
        int totaldress = 0;//we will initialize it later.
        int number_of_machines = sewingMachines.length;//we will take the size of the array.

        //for loop to calculate the total number of dresses
        for (int dress : sewingMachines) {
            totaldress += dress;
        }

        // Checking wheather the total dress can be evenly distributed among the machines or not.
        if (totaldress % number_of_machines != 0) {
            return -1; // we can not divide the dresses in the machines equally.
        }

        int averagedressPerMachine = totaldress / number_of_machines;
        int answer = 0;
        int temp = 0;

        for (int dress : sewingMachines) {
            temp += dress - averagedressPerMachine;
            answer = Math.max(answer, Math.abs(temp));
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] sewingMachines = { 1,0,5};
        System.out.println(minanswerToEqualizedress(sewingMachines));
    }
}