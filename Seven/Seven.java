package Seven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {
    private Map<Integer, List<Integer>> adjacencyList; // Map user ID to their connections

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addUser(int userId) {
        // Add a new user to the graph
        adjacencyList.put(userId, new ArrayList<>());
    }

    public void addConnection(int user1, int user2) {
        // Add an edge (connection) between user1 and user2
        adjacencyList.get(user1).add(user2);
        adjacencyList.get(user2).add(user1); // For an undirected graph
    }

    public List<Integer> getConnections(int userId) {
        return adjacencyList.getOrDefault(userId, new ArrayList<>());
    }

}

public class Seven{
    public static void main(String[] args) {
        Graph socialGraph = new Graph();

        // Add users
        socialGraph.addUser(0);
        socialGraph.addUser(1);
        socialGraph.addUser(2);

        // Add connections
        socialGraph.addConnection(0, 1);
        socialGraph.addConnection(1, 2);
        socialGraph.addConnection(0, 2);

        // Get connections for a user
        int userToCheck = 2;
        List<Integer> connections = socialGraph.getConnections(userToCheck);
        System.out.println("Connections for User " + userToCheck + ": " + connections);
    }
}