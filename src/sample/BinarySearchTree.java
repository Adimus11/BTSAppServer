package sample;


import java.io.PrintWriter;

/**
 * Created by Michał Treter on 04.06.2017.
 */
public class BinarySearchTree<T extends Comparable<T>> {
    public Node<T> root = null;

    public BinarySearchTree(){
        root = null;
    }

    public void insert(T v){
        if(root == null){
            root = new Node<T>(v);
        }
        else{
            root = root.add(root, v);
        }
    }

    public void delete(T v){
        if(root != null) {
            root = root.remove(root, v);
        }
    }

    /**
     * Method wchic initializes printing the tree
     * @param output output stream for printing
     */
    public void draw(PrintWriter output){
        if(root != null) {
            root.drawTree(root, 0, output);
            output.println();
        }
        else {
            output.println("Wyczyszczone");
        }
    }

    public boolean search(T v){
        if(root != null){
            return root.find(v);
        }

        return false;
    }


}
