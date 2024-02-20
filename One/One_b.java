package One;

import java.util.PriorityQueue;

// Class to manage the team of engine builders
class EngineBuilderTeam {
  private PriorityQueue<Integer> buildQueue;

  // Initialize the engine builder team
  public EngineBuilderTeam() {
    this.buildQueue = new PriorityQueue<>();
  }

  // Add an engine to the build queue
  public void addEngine(int buildingTime) {
    buildQueue.offer(buildingTime);
    adjustPriorityUp();
  }

  // Build an engine and return its building time
  public int buildEngine() {
    if (buildQueue.isEmpty()) {
      return -1; // No engine to build
    }
    int builtEngine = buildQueue.poll();
    adjustPriorityDown();
    return builtEngine;
  }

  // Move an engine up the queue to maintain priority
  private void adjustPriorityUp() {
    int currentIdx = buildQueue.size() - 1;
    while (currentIdx > 0) {
      int parentIdx = (currentIdx - 1) / 2;
      if (buildQueue.peek() < buildQueue.toArray(new Integer[0])[parentIdx]) {
        swapEngines(currentIdx, parentIdx);
        currentIdx = parentIdx;
      } else {
        break;
      }
    }
  }

  // Move an engine down the queue to maintain priority
  private void adjustPriorityDown() {
    int currentIdx = 0;
    while (true) {
      int leftChildIdx = 2 * currentIdx + 1;
      int rightChildIdx = 2 * currentIdx + 2;
      int smallestChildIdx = -1;

      if (leftChildIdx < buildQueue.size() && buildQueue.peek() > buildQueue.toArray(new Integer[0])[leftChildIdx]) {
        smallestChildIdx = leftChildIdx;
      }

      if (rightChildIdx < buildQueue.size()
          && (smallestChildIdx == -1
              || buildQueue.toArray(new Integer[0])[rightChildIdx] < buildQueue.toArray(new Integer[0])[smallestChildIdx])) {
        smallestChildIdx = rightChildIdx;
      }

      if (smallestChildIdx != -1) {
        swapEngines(currentIdx, smallestChildIdx);
        currentIdx = smallestChildIdx;
      } else {
        break;
      }
    }
  }

  // Get the number of engines in the build queue
  public int countEngines() {
    return buildQueue.size();
  }

  // Swap the priority of two engines in the queue
  private void swapEngines(int i, int j) {
    Integer[] array = buildQueue.toArray(new Integer[0]);
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;

    buildQueue.clear();
    for (int engineTime : array) {
      buildQueue.offer(engineTime);
    }
  }
}

// Class for managing engine building process
public class One_b {
  // Calculate the minimum time to build all engines with the given split cost
  public static int calculateOptimalBuildTime(int[] engines, int splitCost) {
    if (engines.length == 0) {
      return 0;
    }

    // Create a team of engine builders
    EngineBuilderTeam buildTeam = new EngineBuilderTeam();

    // Add engines to the build queue
    for (int engineTime : engines) {
      buildTeam.addEngine(engineTime);
    }

    int totalTime = 0;

    // Continue building until only one engine left
    while (buildTeam.countEngines() > 1) {
      // Build the two fastest engines
      int firstEngine = buildTeam.buildEngine();
      int secondEngine = buildTeam.buildEngine();

      // Calculate time for current step with split cost consideration
      int stepTime = Math.min(firstEngine + splitCost, secondEngine);

      // Update total time and add new engine to the build queue
      totalTime += stepTime;
      buildTeam.addEngine(stepTime);
    }

    // Remaining engine build time is the total time required
    return totalTime;
  }

  // Main method for example usage
  public static void main(String[] args) {
    // Example usage
    int[] engines = { 3, 4, 5, 2 };
    int splitCost = 2;
    int result = calculateOptimalBuildTime(engines, splitCost);
    System.out.println("Minimum time to build all engines: " + result);
  }
}
