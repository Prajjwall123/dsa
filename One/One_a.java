package One;

public class One_a {
    public static int minimunCostToDecorate(int[][] costs) {
        
        if (costs == null || costs[0].length == 0) //if the costs matrix is empty, return zero
            return 0;

       
        int k = costs[0].length; //  column=number of themes
        int n = costs.length; // row=number of venues

        int[][] min_cost_array = new int[n][k]; //making a new array to store minimum costs

        
        for (int i = 0; i < n; i++) { //looping over rows(venues)
            for (int j = 0; j < k; j++) { // looping over columns(themes)
                min_cost_array[i][j] = costs[i][j];

                
                if (i > 0) { // If not the first venue
                    int previousMincosts = Integer.MAX_VALUE; // initializing the minimum cost as the maximum possible integer so that we can reduce it later
                    for (int x = 0; x < k; x++) { // Iterate over themes of the previous venue
                        if (x != j) { // Exclude the current theme
                            previousMincosts = Math.min(previousMincosts, min_cost_array[i - 1][x]); // Update the minimum cost
                        }
                    }
                    min_cost_array[i][j] += previousMincosts; // Update the current cost by adding the minimum cost of the previous venue with a different theme
                }
            }
        }

        int mincosts = Integer.MAX_VALUE; // Initialize the minimum cost
        for (int j = 0; j < k; j++) { // Iterate over themes of the last venue
            mincosts = Math.min(mincosts, min_cost_array[n - 1][j]); // Find the minimum cost among all themes
        }

        return mincosts; // Return the minimum cost of decorating all venues
    }

    public static void main(String[] args) {
        int[][] costs = {{2, 3, 1}, {4, 6, 8}, {3, 2, 5}};
        System.out.println(minimunCostToDecorate(costs));
    }
}