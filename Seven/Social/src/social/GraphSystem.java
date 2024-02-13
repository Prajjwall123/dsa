package social;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphSystem implements Serializable {
    private Map<Integer, List<Integer>> adjacencyList; // Map user ID to their connections

    public GraphSystem() {
        adjacencyList = new HashMap<>();
    }

    // Add a new user to the graph
    public void addUser(int userId) {
        adjacencyList.put(userId, new ArrayList<>());
    }

    public void addConnection(int user1, int user2) {
        if (!areConnected(user1, user2)) {
            adjacencyList.get(user1).add(user2);
            adjacencyList.get(user2).add(user1); // For an undirected graph
        } else {
            System.out.println("Users are already connected.");
        }
    }
    
            // Check if two users are already connected
        private boolean areConnected(int user1, int user2) {
            List<Integer> connections1 = adjacencyList.getOrDefault(user1, new ArrayList<>());
            List<Integer> connections2 = adjacencyList.getOrDefault(user2, new ArrayList<>());
    
            return connections1.contains(user2) && connections2.contains(user1);
        }

    // Get connections for a given user
    public List<Integer> getConnections(int userId) {
        return adjacencyList.getOrDefault(userId, new ArrayList<>());
    }

    // Save the graph to a file
    public void saveGraphToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Graph saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the graph from a file
    public static GraphSystem loadGraphFromFile(String filename) {
        GraphSystem graph = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            graph = (GraphSystem) in.readObject();
            System.out.println("Graph loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static void main(String[] args) {
        GraphSystem gs= loadGraphFromFile("saver.txt");
//        GraphSystem gs= new GraphSystem();
//         gs.addUser(0);
//         gs.addUser(1);
//         gs.addConnection(0, 1);
//         System.out.println((gs.getConnections(0)));
//         gs.saveGraphToFile("saver.txt");
        
        System.out.println(gs.getConnections(1));
    }
}
