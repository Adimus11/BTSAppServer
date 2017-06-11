package sample;


/**
 * Created by Adimus on 04.06.2017.
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
            //Node<T> tempRoot;
            //tempRoot = root.add(root, v);
            //root = tempRoot;

            root = root.add(root, v);
        }
    }

    public void delete(T v){
        if(root != null) {
            root = root.remove(root, v);
        }
    }

    public void draw(){
        root.drawTree(root, 0);
        System.out.println();
    }

    public boolean search(T v){
        if(root != null){
            return root.find(v);
        }

        return false;
    }


}
