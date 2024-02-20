package Four;

import java.util.ArrayList;
import java.util.List;

class binTreeNode {
    int data;
    binTreeNode left, right;

    public binTreeNode(int data) {//constructor to initialize tree, left and right are null at first.
        this.data = data;
        this.left = this.right = null;
    }
}

public class Four_b {
    public static List<Integer> closestxdatas(binTreeNode root, double k, int x) {//static method to retrn result
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, k, x, result);
        return result;
    }

    private static void inorderTraversal(binTreeNode root, double k, int x, List<Integer> result) {//doing inorder traversal so that we can visit the nodes of bst in sorted order.
        if (root == null) {
            return ;
        }
        
        inorderTraversal(root.left, k, x, result);//going to the left subtree

        if (result.size() < x) {//the number of closet data is not filled
            result.add(root.data);//add the current node
        } else {
            if (Math.abs(k - root.data) < Math.abs(k - result.get(0))) {//if the number of closest data is equal to the number of data in result list, then add the current node instead of the farthest node in the result list.
                result.remove(0);//farthest node is removed
                result.add(root.data);//current node is added
            } else {
                return;
            }
        }

        inorderTraversal(root.right, k, x, result);//calling the inorder traversal for the right subtree.
    }

    public static void main(String[] args) {
        binTreeNode root = new binTreeNode(4);
        root.left = new binTreeNode(2);
        root.right = new binTreeNode(5);
        root.left.left = new binTreeNode(1);
        root.left.right = new binTreeNode(3);

        double k = 3.8;
        int x = 2;

        List<Integer> closestDatas = closestxdatas(root, k, x);
        System.out.println(closestDatas);
    }
}
