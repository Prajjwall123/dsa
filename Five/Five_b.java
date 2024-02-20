package Five;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Five_b {

    public static List<Integer> findNodesWithOneParent(int[][] edges, int targetDevice) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> incomingEdgeCounter = new HashMap<>();
        for (int[] edge : edges) {//loping through each edge in 2d array of edges
            int parentNode = edge[0];
            int childNode = edge[1];
            graph.putIfAbsent(parentNode, new ArrayList<>());//adding new arraylist
            graph.get(parentNode).add(childNode);
            incomingEdgeCounter.put(childNode, incomingEdgeCounter.getOrDefault(childNode, 0) + 1);
        }

        List<Integer> result = new ArrayList<>(); // List to store nodes with only one parent
        dfs(graph, incomingEdgeCounter, targetDevice, targetDevice, result);

        return result;
    }

    // Usng Depth-first search to traverse the graph and find nodes with a single parent
    private static void dfs(Map<Integer, List<Integer>> graph, Map<Integer, Integer> incomingEdgeCounter, int currNode, int targetDevice,
                            List<Integer> result) {
        if (incomingEdgeCounter.getOrDefault(currNode, 0) == 1 && graph.get(targetDevice).contains(currNode)) {
            result.add(currNode);
            addChildren(graph, currNode, result);
        }

        if (graph.containsKey(currNode)) {
            for (int child : graph.get(currNode)) {
                dfs(graph, incomingEdgeCounter, child, targetDevice, result);
            }
        }
    }

    private static void addChildren(Map<Integer, List<Integer>> graph, int node, List<Integer> result) {
        if (graph.containsKey(node)) {
            for (int child : graph.get(node)) {
                result.add(child);
                addChildren(graph, child, result);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 4, 5 }, { 5, 7 } };
        int targetDevice = 4;

        List<Integer> uniqueParents = findNodesWithOneParent(edges, targetDevice);

        for (int node : uniqueParents) {
            System.out.println(node);
        }
    }
}
