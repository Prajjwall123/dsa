package One;

import java.util.Arrays;

public class One_b {
    public static int timeToBuild(int[] engineCostList, int splitCost) {
        int[] cost = new int[engineCostList.length + 1]; // minimum time to build all engines in engineCostList

        Arrays.fill(cost, Integer.MAX_VALUE); //at first, filling infinity in cost array in all the positions
        cost[0] = 0; // seting minimum time to build engine 0 as 0

        // Looping through all the engines
        for (int i = 1; i <= engineCostList.length; i++) {
            cost[i] = engineCostList[i - 1] + splitCost;
            // Looping through each possible split points
            for (int j = 1; j < i; j++) {
                cost[i] = Math.min(cost[i], cost[j] + cost[i - j]); //updating the minimum cost
            }
        }
        return cost[engineCostList.length]; //total time to build teh engines will be the length og the list.
    }

    public static void main(String[] args) {
        int[] engines = {1,2,3};
        System.out.println("Answer:"+timeToBuild(engines, 1));
    }
}