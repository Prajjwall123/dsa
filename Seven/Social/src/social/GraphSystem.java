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
//            adjacencyList.get(user2).add(user1);
             System.out.println(user1+":follows:"+user2);
        } else {
            System.out.println("Users are already connected.");
        }
    }
    
        // Function to check if a user exists
    public boolean userExists(int userId) {
        return adjacencyList.containsKey(userId);
    }
    
            // Check if two users are already connected
        private boolean areConnected(int user1, int user2) {
            List<Integer> connections1 = adjacencyList.getOrDefault(user1, new ArrayList<>());
            List<Integer> connections2 = adjacencyList.getOrDefault(user2, new ArrayList<>());
    
            return connections1.contains(user2) && connections2.contains(user1);
        }

    // Get connections for a given user
    public List<Integer> getFollowing(int userId) {
        return adjacencyList.getOrDefault(userId, new ArrayList<>());
    }
    
// Get connections for a given user (users who follow the specified user)
public List<Integer> getFollowers(int userId) {
    List<Integer> followers = new ArrayList<>();
    for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
        int user = entry.getKey();
        List<Integer> connections = entry.getValue();
        if (connections.contains(userId) && user != userId) {
            followers.add(user);
        }
    }
    return followers;
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
    
    public List<Integer> getMutualFollowers(int userId) {
        List<Integer> mutualFollowers = new ArrayList<>();
    
        // Get the users followed by the given user
        List<Integer> following = getFollowing(userId);
        System.out.println(following);
    
        // Iterate through the users followed by the given user
        for (int followedUser : following) {
            // Get the followers of the followed user
            List<Integer> followersOfFollowedUser = getFollowers(followedUser);
            System.out.println("followers of the follwed user , i.e 1 are:"+followersOfFollowedUser);
            
            // Check if the followers of the followed user also follow the original user (userId)
            for (int follower : followersOfFollowedUser) {
                if (follower != userId && !following.contains(follower) && !mutualFollowers.contains(follower)) {
                    mutualFollowers.add(follower);
                }
            }
        }
        return mutualFollowers;
    }
    
    



    public static void main(String[] args) {
//    GraphSystem gs= new GraphSystem();
//        gs.addUser(0);
//        gs.addUser(1);
//        gs.addUser(2);
//        gs.addUser(3);
//        gs.addConnection(0, 1);
//        gs.addConnection(2, 1);
//        gs.addConnection(3, 1);
//        gs.addConnection(0, 3);
//        System.out.println(gs.getFollowers(1));
//        System.out.println(gs.getMutualFollowers(0));
//         gs.saveGraphToFile("saver.txt");
        
//        System.out.println(gs.getFollowing(1));
    }
}
