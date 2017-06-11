package sample;

import java.net.ServerSocket;

/**
 * Created by adimus on 6/11/17.
 */
public class CommunicationProcess implements Runnable {

    ServerSocket currentSocket;

    public BinarySearchTree<Integer> treeI;
    public BinarySearchTree<String> treeS;
    public BinarySearchTree<Double> treeD;

    public CommunicationProcess(ServerSocket currentSocket){
        this.currentSocket = currentSocket;

        treeI = new BinarySearchTree<Integer>();
        treeS = new BinarySearchTree<String>();
        treeD = new BinarySearchTree<Double>();

    }

    @Override
    public void run() {

    }

    private void resetTree(String type){
        if(type.equals("S")){
            treeS = new BinarySearchTree<String>();
        }

        if(type.equals("I")){
            treeI = new BinarySearchTree<Integer>();
        }

        if(type.equals("D")){
            treeD = new BinarySearchTree<Double>();
        }
    }
}
