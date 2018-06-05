package tree.binary_search_tree;


public class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree(int[] input){
        createBinarySearchTree(input);
    }

    private void createBinarySearchTree(int[] input) {
        if (input!=null){
            for (int i=0;i<input.length;i++){
                insert(new Node(input[i]));
            }
        }
    }

    public void insert(Node node) {
        Node y = null;
        Node x = root;
        while (x!=null){
            y = x;
            if (node.data<x.data){
                x = x.left;
            }else{
                x = x.right;
            }
            node.parent = y;

        }
        if (y==null){
            root = node;
        }else if (node.data<y.data){
            y.left = node;
        }else{
            y.right = node;
        }
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

    public void remove(Node node){
        Node tmp =null;
       if (node.left==null){
           //left tree is null
           //replace node with right tree
           transplant(node,node.right);
       }else if (node.right==null){
           //right tree is null
           transplant(node,node.left);
       }else{
           //have both left and right tree
           //find node's successor!
           tmp = getMinimum(node.right);
           if (tmp.parent!=node){
                //if the successor is not right child
               transplant(tmp,tmp.right);
               tmp.right = node.right;
               tmp.right.parent = tmp;
           }else{
               //simply replace node with successor
               transplant(node,tmp);
               tmp.left = node.left;
               tmp.left.parent = tmp;
           }
       }
    }

    public void transplant(Node u,Node v){
        if (u.parent==null){
            this.root = v;
        }else if (u==u.parent.left){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }
        if (v!=null){
            v.parent = u.parent;
        }
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

    public Node getMinimum(Node node){
        while (node.left!=null){
            node = node.left;
        }
        return node;
    }

    public Node getMaximum(Node node){
        while (node.right!=null){
            node = node.right;
        }
        return node;
    }

    public Node getPredecessor(Node node){
        Node tmp = null;
        if (node!=null){
            //have left tree,find biggest in left tree
            if (node.left!=null){
                tmp = getMaximum(node.left);
            }else{
                //don't have left tree. follow tree up until find a node which is its' parent's right node
                tmp = node.parent;
                while (tmp!=null && node==tmp.left){
                    node = tmp;
                    tmp = tmp.parent;
                }

            }
        }
        return tmp;
    }

    public Node getSuccessor(Node node){
        Node tmp = null;
        if (node!=null){
            //have right tree,find smallest in right tree
            if (node.right!=null){
                tmp = getMinimum(node.right);
            }else{
                //don't have right tree. follow tree up until find a node which is its' parent's left node
                tmp = node.parent;
                while (tmp!=null && node==tmp.right){
                    node = tmp;
                    tmp = tmp.parent;
                }

            }
        }
        return tmp;
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
