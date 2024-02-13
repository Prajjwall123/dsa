/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package social;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class Controller {
    
    public static int findUserIdByUsername(String username) {
        int userId = -1; // Default value if user is not found or if an error occurs
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection (you need to implement this)
            connection = DbConnection.dbConnect(); // Example method for establishing connection

            // Prepare SQL query
            String query = "SELECT userid FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            // Execute query
            resultSet = statement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                userId = resultSet.getInt("userid");
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle or log the exception as needed
            }
        }

        return userId;
    }
    
public void followUser(int followerId, int followeeId) {
    Connection conn = DbConnection.dbConnect();
    
    try {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO follows (follower_id, followee_id) VALUES (?, ?)");
        pstmt.setInt(1, followerId);
        pstmt.setInt(2, followeeId);
        pstmt.executeUpdate();
        conn.close();
        JOptionPane.showMessageDialog(null, followerId + "\t you now follow \t" + followeeId);

        
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



// Method to fetch users followed by a particular user
public List<Integer> getUsersFollowed(int userId) {
    List<Integer> usersFollowed = new ArrayList<>();
    Connection conn = DbConnection.dbConnect();

    try {
        PreparedStatement pstmt = conn.prepareStatement("SELECT followee_id FROM follows WHERE follower_id = ?");
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int followeeId = rs.getInt("followee_id");
            usersFollowed.add(followeeId);
        }
        conn.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return usersFollowed;
}

public void showFollowedUsers(String username) {
int ourUser = findUserIdByUsername(username);

// Fetch user IDs followed by the specified user
List<Integer> usersFollowed = getUsersFollowed(ourUser);

// Create a string containing all the usernames
StringBuilder usernamesString = new StringBuilder();
for (Integer userId : usersFollowed) {
    String the_user = getUsernameByUserId(userId);
    usernamesString.append(the_user).append(", ");
}

// Remove the trailing comma and space if the string is not empty
if (usernamesString.length() > 0) {
    usernamesString.delete(usernamesString.length() - 2, usernamesString.length());
}

// Display the usernames
JOptionPane.showMessageDialog(null, "Users followed by " + username + ":\n" + usernamesString.toString());
}

        // Method to fetch usernames of users based on their user IDs
public String getUsernameByUserId(int userId) {
    String followed_users = null;
    Connection conn = DbConnection.dbConnect();
    
    try {
        // Prepare SQL query
        String query = "SELECT username FROM users WHERE userid = ?";
        
        // Execute query
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        
        // Process the result set
        if (rs.next()) {
            followed_users = rs.getString("username");
        }
        conn.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    
    return followed_users;
}
    
}
