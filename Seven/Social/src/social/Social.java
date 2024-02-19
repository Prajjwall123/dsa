package social;
public class Social {
    public static void main(String[] args) {
        GraphSystem gs= GraphSystem.loadGraphFromFile("saver.txt");
        System.out.println((gs.getFollowers(0)));
    }
}
