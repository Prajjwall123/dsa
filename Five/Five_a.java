package Five;
import java.util.Arrays;
import java.util.Random;
class AntColony {
    private int[][] cityDistances;
    private int antsNum;
    private double[][] pheromoneMatrix;
    private double[][] probabilities;
    private int numCity;
    private int[] bestTour;
    private int bestTourLength;
    private double evaporationRate;
    private double alpha;
    private double beta;

    // Constructor to initialize the Ant Colony
    public AntColony(int[][] cityDistances, int antsNum, double evaporationRate, double alpha, double beta) {
        this.cityDistances = cityDistances;
        this.antsNum = antsNum;
        this.evaporationRate = evaporationRate;
        this.alpha = alpha;
        this.beta = beta;
        this.numCity = cityDistances.length;
        this.pheromoneMatrix = new double[numCity][numCity];
        this.probabilities = new double[numCity][numCity];
        initializePheromones();
    }
    // Method to initialize the pheromone levels on edges
    private void initializePheromones() {
        double initialPheromone = 1.0 / numCity;
        for (int i = 0; i < numCity; i++) {
            for (int j = 0; j < numCity; j++) {
                if (i != j) {
                    pheromoneMatrix[i][j] = initialPheromone;
                }
            }
        }
    }
    
    // Method to solve the Travelling Salesman Problem using Ant Colony Optimization

    public void solve(int maxIterations) {
        bestTourLength = Integer.MAX_VALUE;
        bestTour = new int[numCity];
        Random random = new Random();

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (int ant = 0; ant < antsNum; ant++) {
                boolean[] visited = new boolean[numCity];
                int[] tour = new int[numCity];
                int currentCity = random.nextInt(numCity);
                tour[0] = currentCity;
                visited[currentCity] = true;

                for (int i = 1; i < numCity; i++) {
                    calculateProbabilities(currentCity, visited);
                    int nextCity = selectNextCity(currentCity);
                    tour[i] = nextCity;
                    visited[nextCity] = true;
                    currentCity = nextCity;
                }

                int tourLength = calculateTourLength(tour);
                if (tourLength < bestTourLength) {
                    bestTourLength = tourLength;
                    bestTour = tour;
                }
            }

            updatePheromones();
        }
    }

    // Method to calculate probabilities of selecting each city as the next destination
    private void calculateProbabilities(int city, boolean[] visited) {
        double total = 0.0;
        for (int i = 0; i < numCity; i++) {
            if (!visited[i]) {
                probabilities[city][i] = Math.pow(pheromoneMatrix[city][i], alpha) *
                        Math.pow(1.0 / cityDistances[city][i], beta);
                total += probabilities[city][i];
            } else {
                probabilities[city][i] = 0.0;
            }
        }

        for (int i = 0; i < numCity; i++) {
            probabilities[city][i] /= total;
        }
    }

    // Method to select the next city based on calculated probabilities
    private int selectNextCity(int city) {
        double[] probabilities = this.probabilities[city];
        double r = Math.random();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numCity; i++) {
            cumulativeProbability += probabilities[i];
            if (r <= cumulativeProbability) {
                return i;
            }
        }
        return -1;
    }
    // Method to update the pheromone levels on edges
    private void updatePheromones() {
        for (int i = 0; i < numCity; i++) {
            for (int j = 0; j < numCity; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate);
            }
        }
        for (int ant = 0; ant < antsNum; ant++) {
            for (int i = 0; i < numCity - 1; i++) {
                int city1 = bestTour[i];
                int city2 = bestTour[i + 1];
                pheromoneMatrix[city1][city2] += (1.0 / bestTourLength);
                pheromoneMatrix[city2][city1] += (1.0 / bestTourLength);
            }
        }
    }
    // Method to calculate the length of a tour
    private int calculateTourLength(int[] tour) {
        int length = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            length += cityDistances[tour[i]][tour[i + 1]];
        }
        length += cityDistances[tour[tour.length - 1]][tour[0]];
        return length;
    }
    public int getBestTourLength() {
        return bestTourLength;
    }

    public int[] getBestTour() {
        return bestTour;
    }
}

public class Five_a {
    public static void main(String[] args) {
        int[][] cityDistances = {
            {0, 12, 25, 18},
            {12, 0, 35, 30},
            {25, 35, 0, 10},
            {18, 30, 10, 0}
        };
        int antsNum = 7;
        double evaporationRate = 0.3;
        double alpha = 1.5;
        double beta = 2.0;

        AntColony colony = new AntColony(cityDistances, antsNum, evaporationRate, alpha, beta);
        
        colony.solve(1000);

        int[] bestTour = colony.getBestTour();
        int bestTourLength = colony.getBestTourLength();

        System.out.println("Best tour is: " + Arrays.toString(bestTour));
        System.out.println("Best tour length is: " + bestTourLength);
    }
}