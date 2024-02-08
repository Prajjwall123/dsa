package Three;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;;

public class Three_a{
    public static void main(String[] args) {
        System.out.println("Score Tracker Algorithm\nCalculates the median of assignment scores");
        ScoreTracker scoreTracker = new ScoreTracker();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        double median1 = scoreTracker.getMedianScore();
        System.out.println("The median is: " + median1);
        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        double median2 = scoreTracker.getMedianScore();
        System.out.println("The median is: "+ median2);

    }
}

class ScoreTracker{
    List<Double> scores;//making an arraylist so that we can store the scores(if we make an array we will have to give it a fixed size)
    ScoreTracker(){//constructor initializes the arraylist for us to store data
        scores= new ArrayList<>();
    }
    void addScore(double score){
        scores.add(score);
        Collections.sort(scores);
    }
    double getMedianScore(){
        //if there are no scores
        if(scores.size()==0){
            System.out.println("there are no scores to calculate the median of scores.");
            return 0.0;
        }
        else if(scores.size()%2==0){
            //if the number of scores is even,
            //median is (n/2 and n/2-1) index scores' average
            int n=scores.size();
            int a= n/2;
            int b=(n/2)-1;
            double median=(scores.get(a)+scores.get(b))/2.0;
            return median;
        }
        else{
            //number of scores is odd
            int n= scores.size();
            return scores.get(n/2);
        }
    }
}
