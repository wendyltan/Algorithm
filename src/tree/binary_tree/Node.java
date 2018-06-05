package tree.binary_tree;

public class Node<T> {
    T data; //node data
    Node<T> left; //point to left child
    Node<T> right; //point to right child
    boolean isFirst; //use for non-recursive postorder traversal

    public Node(T data){
        this.data = data;
    }
    //override the toString and equals method

    @Override
    public String toString() {
        return data == null?null:data.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node){
            Node<T> temp = (Node<T>)obj;
            if (data.equals(temp.data)){
                return true;
            }
        }
        return false;
    }
}
