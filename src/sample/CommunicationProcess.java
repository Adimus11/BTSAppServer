package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Michał Treter on 6/11/17.
 */
public class CommunicationProcess implements Runnable {

    Socket currentSocket;

    public BinarySearchTree<Integer> treeI;
    public BinarySearchTree<String> treeS;
    public BinarySearchTree<Double> treeD;
    public BufferedReader clientInput;
    public PrintWriter dataToClient;

    public CommunicationProcess(Socket currentSocket){
        this.currentSocket = currentSocket;

        treeI = new BinarySearchTree<Integer>();
        treeS = new BinarySearchTree<String>();
        treeD = new BinarySearchTree<Double>();

        try {
            InputStreamReader input = new InputStreamReader(currentSocket.getInputStream());
            clientInput = new BufferedReader(input);
            dataToClient = new PrintWriter(currentSocket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        while(true) {
            String input = null;
            try {
                input = clientInput.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] dataDivided;

            while (input != null) {
                System.out.println(input);

                try {
                    dataDivided = input.split(":");

                    if (dataDivided[1].equals("A")) {
                        addToTree(dataDivided[0], dataDivided[2]);
                    } else if (dataDivided[1].equals("D")) {
                        deleteFromTree(dataDivided[0], dataDivided[2]);
                    } else if (dataDivided[1].equals("S")) {
                        searchInTree(dataDivided[0], dataDivided[2]);
                    } else if (dataDivided[1].equals("P")) {
                        printTree(dataDivided[0]);
                    } else if (dataDivided[1].equals("R")) {
                        resetTree(dataDivided[0]);
                    }

                    input = null;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("error");
                }

            }
        }

    }

    /**
     * Method responsible for reseting tree
     * @param type type of tree
     */
    private void resetTree(String type){
        if(type.equals("S")){
            treeS = new BinarySearchTree<String>();
            printTree("S");
        }

        if(type.equals("I")){
            treeI = new BinarySearchTree<Integer>();
            printTree("I");
        }

        if(type.equals("D")){
            treeD = new BinarySearchTree<Double>();
            printTree("D");
        }
    }

    private void addToTree(String type, String value){
        if(type.equals("I")){
            treeI.insert(Integer.parseInt(value));
            printTree("I");
        }
        else if(type.equals("D")){
            treeD.insert(Double.parseDouble(value));
            printTree("D");
        }
        else  if(type.equals("S")){
            treeS.insert(value);
            printTree("S");

        }
    }

    private void deleteFromTree(String type, String value){
        if(type.equals("I")){
            treeI.delete(Integer.parseInt(value));
            printTree("I");
        }
        else if(type.equals("D")){
            treeD.delete(Double.parseDouble(value));
            printTree("D");
        }
        else  if(type.equals("S")){
            treeS.delete(value);
            printTree("S");

        }
    }

    private void searchInTree(String type, String value){
        if(type.equals("I")){
            boolean found = treeI.search(Integer.parseInt(value));
            if(found){
                dataToClient.println("S1");
            }
            else{
                dataToClient.println("S0");
            }
        }
        else if(type.equals("D")){
            boolean found = treeD.search(Double.parseDouble(value));
            if(found){
                dataToClient.println("S1");
            }
            else{
                dataToClient.println("S0");
            }
        }
        else  if(type.equals("S")){
            boolean found = treeS.search(value);
            if(found){
                dataToClient.println("S1");
            }
            else{
                dataToClient.println("S0");
            }

        }
    }

    private void printTree(String type){
        if(type.equals("I")){
            treeI.draw(dataToClient);
        }
        else if(type.equals("D")){
            treeD.draw(dataToClient);
        }
        else  if(type.equals("S")){
            treeS.draw(dataToClient);

        }
    }
}
