package tree;

import java.util.LinkedList;

/**
 * Title: 二叉树(非线性结构)的构建及相关操作
 * Description:
 * 以广义表形式的字符串构建二叉树：'()'前表示根结点，括号中左右子树用逗号隔开，逗号不能省略
 * 二叉树的层次/广序遍历算法
 * 二叉树的前序、中序、后序遍历的递归和非递归算法(对每个节点而言，三种遍历方式都需要遍历该结点三次，三者唯一区别在于该结点的访问时机)
 * 根据二叉树的前序、中序或中序、后序遍历结果构建二叉树
 * 二叉树的高度s
 * 二叉树的结点总数
 * 根据树的根结点复制一颗二叉树
 * 获取二叉树的根结点，孩子节点
 * 打印二叉树
 * 判断两颗二叉树是否相等
 *
 */
public class BinaryTree<E> {

    //root node
    private Node<E> root;

    /**
     * Default constructor
     */
    public BinaryTree(){
        super();
    }

    /**
     * Copy a tree according to the given root node
     * @param node
     */
    public BinaryTree(Node<E> node){
        this.root = copy(node);
    }

    /**
     * Copy a tree according to the given preOrderStr
     * @param preOrderStr
     */
    public BinaryTree(char[] preOrderStr){
        root = createTreeByPreOrderStr(preOrderStr,null);
    }

    /**
     * Copy a tree according to pre-in order String or
     * in-post order String
     * @param s1
     * @param s2
     * @param isPreIn
     */
    public BinaryTree(String s1,String s2,boolean isPreIn){
        if (isPreIn){
            root = createBinaryTreeByPreAndIn(s1,s2);
        }else{
            root = createBinaryTreeByInAndPost(s1,s2);
        }
    }

    /**
     * Construct a tree using expression.
     * @param exp
     */
    public void createBinaryTree(String exp){
        LinkedList<Node<E>> stack = new LinkedList<>(); //helper stack
        Node<E> node = null; //new node
        Node<E> temp = null; //use to push into stack
        Node<E> parent = null; //parent node
        boolean flag = false; //true means insert to parent's left child,false means insert into right child.

        for (int i=0;i<exp.length();i++){
            char c = exp.charAt(i);
            switch (c){
                case '(':
                    //current node has child,push into stack to set child
                    stack.push(temp);
                    flag = true;
                    break;
                case ')':
                    //set child at the stack top done,pop it
                    stack.pop();
                    break;
                case ',':
                    //current node has no child,don't have to set child node
                    flag = false;
                    break;
                default:
                    //build a new node
                    node = new Node(c);
                    break;
            }

            //if tree doesn't exist,create root node
            if (root == null){
                root = node;
            }

            //link child for the stack top node
            if (!stack.isEmpty()){
                if (node!=null){
                    //already jump '(',')'and','
                    parent = stack.peek();
                    if (flag){
                        parent.left = node;
                    }else{
                        parent.right = node;
                    }
                }
            }

            temp = node; //for pushing in stack
            node = null;//set it null after it was linked in

        }

    }

    public static Node<Character> createBinaryTree(String exp, Node<Character> root){
        LinkedList<Node<Character>> stack = new LinkedList<>(); //helper stack
        Node<Character> node = null; //new node
        Node<Character> temp = null; //use to push into stack
        Node<Character> parent = null; //parent node
        boolean flag = false; //true means insert to parent's left child,false means insert into right child.

        for (int i=0;i<exp.length();i++){
            char c = exp.charAt(i);
            switch (c){
                case '(':
                    //current node has child,push into stack to set child
                    stack.push(temp);
                    flag = true;
                    break;
                case ')':
                    //set child at the stack top done,pop it
                    stack.pop();
                    break;
                case ',':
                    //current node has no child,don't have to set child node
                    flag = false;
                    break;
                default:
                    //build a new node
                    node = new Node<>(c);
                    break;
            }

            //if tree doesn't exist,create root node
            if (root == null){
                root = node;
            }

            //link child for the stack top node
            if (!stack.isEmpty()){
                if (node!=null){
                    //already jump '(',')'and','
                    parent = stack.peek();
                    if (flag){
                        parent.left = node;
                    }else{
                        parent.right = node;
                    }
                }
            }

            temp = node; //for pushing in stack
            node = null;//set it null after it was linked in

        }
        return root;

    }

    /**
     * level traversal
     * @return
     */
    public String levelOrder(){
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> queue = new LinkedList<>();
        if (root!=null){
            queue.add(root);
            while (!queue.isEmpty()){
                Node<E> temp = queue.pop();
                sb.append(temp.data).append(" ");

                //while traversalling current node,insert its' left right child
                if (temp.left!=null){
                    queue.add(temp.left);
                }
                if (temp.right!=null){
                    queue.add(temp.right);
                }
            }
        }
        return sb.toString().trim();
    }

    /**
     * Recursive - PreOrder traversal
     * @param root
     * @return
     */
    public String preOrder(Node<E> root){
        StringBuilder sb = new StringBuilder();
        if (root!=null){
            sb.append(root.data+" ");//traversal current node
            sb.append(preOrder(root.left)); //traversal left tree
            sb.append(preOrder(root.right)); //traversal right tree
        }
        return sb.toString();
    }

    /**
     * NonRecursive(Iterative) - PreOrder traversal
     * root-left-right
     * @return
     */
    public String preOrder(){
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> stack = new LinkedList<>();
        Node<E> node = root;
        while (node!=null||!stack.isEmpty()){
            if (node!=null){
                sb.append(node.data+" ");
                stack.push(node);//current node into stack
                node = node.left; //traverse its' left tree
            }else{
                node = stack.pop();//pop out its' parent
                node = node.right; //then traverse its' right tree
            }
        }
        return sb.toString();

    }

    /**
     * Recursive - InOrder traversal
     * @param root
     * @return
     */
    public String inOrder(Node<E> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(inOrder(root.left)); //traverse left tree
            sb.append(root.data + " "); // traverse current node
            sb.append(inOrder(root.right)); // traverse right tree
        }
        return sb.toString();
    }

    /**
     * NonRecursive(Iterative) - InOrder traversal
     * left-root-right
     * @return
     */
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> stack = new LinkedList<>(); //record back trace
        Node<E> node = root;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node); //current node into stack
                node = node.left; // traverse its' left tree
            } else {
                node = stack.pop(); // pop out its' parent
                sb.append(node.data + " "); // get parrent's data
                node = node.right; //then traverse the right tree
            }
        }
        return sb.toString();
    }

    /**
     * Recursive - PostOrder traversal
     * @param root
     * @return
     */
    public String postOrder(Node<E> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(postOrder(root.left)); // traverse left tree
            sb.append(postOrder(root.right)); // traverse right tree
            sb.append(root.data + " "); // traverse current node
        }
        return sb.toString();
    }

    /**
     * NonRecursive - PostOrder traversal
     * left-right-root
     * @return
     */
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> stack = new LinkedList<>();
        Node<E> node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                node.isFirst = true; // if first time access,mark as true
                stack.push(node); // push current node
                node = node.left; // traverse its' left tree
            } else { //current node empty but stack not empty
                node = stack.pop(); // pop out from stack
                if (node.isFirst) {
                    node.isFirst = false; // second access to this node,change to false
                    stack.push(node); // push it into stack again
                    node = node.right; // traverse the right tree
                } else { //the third time access the node
                    sb.append(node.data + " "); // read node content
                    node = null; // current node and its' left and right tree were already accessed,checkout nodes in stack.
                }
            }
        }
        return sb.toString();
    }


    /**
     * Rebuild tree according to pre and in order string
     * @param pre
     * @param in
     * @return
     */
    private Node<E> createBinaryTreeByPreAndIn(String pre, String in) {
        if (pre.length()>0){
            Node<E> root = new Node(pre.charAt(0)); //the first of the sequence is root
            int index = in.indexOf(pre.charAt(0)); //using in order as record index
            root.left = createBinaryTreeByPreAndIn(pre.substring(1,index+1),
                    in.substring(0,index));
            root.right = createBinaryTreeByPreAndIn(pre.substring(index+1,pre.length()),
                    in.substring(index+1,in.length()));
            return root;
        }
        return null;
    }

    /**
     * Rebuild tree according to in and post order string
     * @param in
     * @param post
     * @return
     */
    private Node<E> createBinaryTreeByInAndPost(String in, String post) {
        if (post.length() > 0) {
            Node<E> root = new Node(post.charAt(post.length() - 1));//the last of the sequence is root
            int index = in.indexOf(post.charAt(post.length() - 1));

            root.left = createBinaryTreeByInAndPost(in.substring(0, index),
                    post.substring(0, index));
            root.right = createBinaryTreeByInAndPost(
                    in.substring(index + 1, in.length()),
                    post.substring(index, post.length() - 1));
            return root;
        }
        return null;
    }


    private int index = 0;

    /**
     * Rebuild tree using pre-order.tree leaf express as '#'
     * @param preOrderStr
     * @param temp
     * @return
     */
    private Node<E> createTreeByPreOrderStr(char[] preOrderStr, Node<E> temp) {
        if (index < preOrderStr.length) {
            char c = preOrderStr[index++];
            if (c != '#') { // 递归终止条件
                Node<E> node = new Node(c);
                node.left = createTreeByPreOrderStr(preOrderStr, node); // build left tree
                node.right = createTreeByPreOrderStr(preOrderStr, node); // build right tree
                return node;
            }
            return null;
        }
        return null;
    }


    /**
     * Copy a tree according to the root node
     * @param root
     * @return
     */
    private Node<E> copy(Node<E> root) {
        if (root == null)
            return null;
        Node<E> node = new Node<E>(null);
        node.data = root.data;
        node.left = copy(root.left);
        node.right = copy(root.right);
        return node;
    }

    public Node<E> getRoot() {
        return root;
    }

    public Node<E> getLeftChild(Node<E> node) {
        return node.left;
    }

    public Node<E> getRightChild(Node<E> node) {
        return node.right;
    }

    /**
     * Count node numbers in a tree
     * Think about post-order
     * @param root
     * @return
     */
    public int size(Node<E> root) {
        if (root != null) {
            return size(root.left) + size(root.right) + 1;
        }
        return 0;
    }

    /**
     * Measure the height of a tree
     * Think about post-order
     * @param root
     * @return
     */
    public int height(Node<E> root) {
        if (root != null) {
            int h1 = height(root.left);
            int h2 = height(root.right);
            return h1 > h2 ? h1 + 1 : h2 + 1;
        }
        return 0;
    }

    /**
     * Print tree as expression.
     * Think about pre-order
     * @param root
     * @return
     */
    public String printBinaryTree(Node<E> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.data);
            if (root.left != null || root.right != null) {
                sb.append('(');
                sb.append(printBinaryTree(root.left));
                sb.append(',');
                sb.append(printBinaryTree(root.right));
                sb.append(')');
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return levelOrder();
    }

    /**
     * Tell whether two tree equals according to root node
     * @param src
     * @param des
     * @return
     */
    private boolean equals0(Node<E> src, Node<E> des) {
        if (src == null && des == null) { // empty tree equals
            return true;
        } else if (src == null || des == null) { // empty and non-empty tree not equal
            return false;
        } else { // tree and left tree and right tree equal?
            return src.equals(des) && equals0(src.left, des.left)
                    && equals0(src.right, des.right);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryTree) { // 对方是否也是一颗二叉树
            BinaryTree<E> tree = (BinaryTree<E>) obj;
            return equals0(this.root, tree.root);
        }
        return false;
    }


}
