package tree.binary_search_tree;


public class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree(int[] input){
        createBinarySearchTree(input);
    }

    private void createBinarySearchTree(int[] input) {
        if (input!=null){
            for (int i=0;i<input.length;i++){
                root = insert(input[i],root);
            }
        }
    }

    public Node insert(int target, Node node) {
        if (search(target,node) == null){
            //can't find any node the same,insert this new node
            if (node==null){
                //root node
                return new Node(target);
            }else{
                if (target<node.data){
                    //recursively insert into left tree
                    node.left = insert(target,node.left);
                }else{
                    //recursively insert into right tree
                    node.right = insert(target,node.right);
                }
            }
        }
        return node;
    }

    public Node search(int target,Node root){
        Node result = null;
        if (root!=null){
            if (target==root.data){
                result = root;
                return result;
            }else if (target<root.data){
                //search in left tree
                result = search(target,root.left);
            }else{
                result = search(target,root.right);
            }

        }
        return result;
    }

    public Node remove(int target,Node node){
        Node tmp = null;
        if (node!=null){
            if (target<node.data){
                //delete from left tree
                node.left = remove(target,node.left);
            }else if (target>node.data){
                //delete from right tree
                node.right = remove(target,node.right);
            }else if(node.left!=null&&node.right!=null){
                //find target and its' left and right tree are not empty

                //find its' right tree's min node.that is,the successor
                tmp = node.right;
                while (tmp.left!=null){
                    tmp = tmp.left;
                }

                node.data = tmp.data;

                //recursively delete the original node that has replace the current node
                node.right = remove(node.data,node.right);

            }else{
                if (node.left==null){
                    node = node.right;
                }else{
                    node = node.left;
                }
            }
        }
        return node;
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void printTree(Node node) {
        if (node != null) {
            System.out.print(node.data);
            if (node.left != null || node.right != null) {
                System.out.print("(");
                printTree(node.left);
                System.out.print(",");
                printTree(node.right);
                System.out.print(")");
            }
        }
    }
}
