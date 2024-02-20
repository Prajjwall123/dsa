package Three;

import java.util.ArrayList;

class PriorityQueue{
    ArrayList<Integer> minHeap;
    
    PriorityQueue(){
        minHeap=new ArrayList<>();
    }
    void add(int data){

        minHeap.add(data);
        upHeapify(minHeap.size()-1);

    }
    void upHeapify(int i){
        if(i==0){
            return;
        }
        int parentIndex=(i-1)/2;
        if(minHeap.get(i)<minHeap.get(parentIndex)){
            swap(i,parentIndex);
            upHeapify(parentIndex); 
        }
    }

    // method to swap elements at two positions
    void  swap(int a, int b){
        int  tempA=minHeap.get(a);
        int tempB=minHeap.get(b);
        minHeap.set(a,tempB);
        minHeap.set(b,tempA);
     }

    
    int remove(){//remove teh smallest eklement from the priority queue
        if(this.size()==0){
            return -1;
        }
        swap(0, minHeap.size()-1); //swapping the fist and last element
        int val=minHeap.remove(minHeap.size()-1);
        downHeapify(0);
        return val;
    }
    void downHeapify(int parent){
        int min=parent;
        int leftIndex=2*parent+1;
        // if leftchild is least, min is leftchild
        if(leftIndex< minHeap.size() && minHeap.get(leftIndex)<minHeap.get(min)){
            min=leftIndex;
        }
        int rightIndex=2*parent+2;
        // if rightchild is least, min is rightchild
        if(rightIndex< minHeap.size() && minHeap.get(rightIndex)<minHeap.get(min)){
            min=rightIndex;
        }

        if(min !=parent){ //if min is not parent, again swapping the min and parent and call downheapify on min
            swap(parent, min);
            downHeapify(min);
        }
    }

    int peek(){
        if(this.size()==0){//teh min heap is empty
            return -1;
        }
        else{
            return minHeap.get(0);
        }
    }

    int size(){
        return minHeap.size();
    }
}

public class Three_b {

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add(7);
        priorityQueue.add(4);
        priorityQueue.add(9);
        priorityQueue.add(2);
        priorityQueue.add(11);
        priorityQueue.add(6);
        System.out.println("Elements of the priority queue:");
        while (priorityQueue.size() > 0) {
            System.out.println(priorityQueue.remove()); 
        }
    }
}