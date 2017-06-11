package sample;

/**
 * Created by Adimus on 04.06.2017.
 */
public class Node<T extends Comparable<T>> {
    public Node<T> left = null;
    public Node<T> right = null;
    public T value;


    public Node(T v) {
        this.value = v;
    }

    public Node<T> add(Node<T> node, T v){

        if(node == null){
            node = new Node<T>(v);
            return node;
        }

        if(node.value.compareTo(v) > 0){
            node.left = add(node.left, v);
        }
        else{
            if(node.value.compareTo(v) < 0){
                node.right = add(node.right, v);
            }
        }

        return node;
    }

    public Node<T> remove(Node<T> node, T v){

        if(node == null){
            return node;
        }

        if(node.value.compareTo(v) > 0){
            node.left = remove(node.left, v);
        }
        else {
            if(node.value.compareTo(v) < 0){
                node.right = remove(node.right, v);
            }
            else{
                if(node.left == null){
                    return node.right;
                }
                else{
                    if(node.right == null){
                        return node.left;
                    }
                }

                node.value = minValue(node.right);
                node.right = remove(node.right, node.value);
            }
        }

        return node;
    }

    private T minValue(Node<T> node) {
        T min = node.value;
        while(node.left != null){
            min = node.left.value;
            node = node.left;
        }

        return min;
    }

    public boolean find(T v){
        Node<T> currentNode = this;

        while (currentNode != null) {
            if (currentNode.value.compareTo(v) == 0) {
                return true;
            }
            if(currentNode.value.compareTo(v) > 0){
                currentNode = currentNode.left;
            }
            else{
                currentNode = currentNode.right;
            }
        }

        return false;
    }

    public void drawTree(Node<T> root, int level){
        if(root==null)
            return;
        drawTree(root.right, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+root.value);
        }
        else
            System.out.println(root.value);
        drawTree(root.left, level+1);
    }

}
