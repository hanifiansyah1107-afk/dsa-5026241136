package lw06.unguided;

import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree {
    Node root;

    public void insert(int data){
        root = insertHelper(root, data);
    }

    private Node insertHelper(Node root, int data){
        if(root==null){
            root = new Node(data);
        }else if(data < root.data){
            root.left = insertHelper(root.left, data);
        }else if(data > root.data){
            root.right = insertHelper(root.right, data);
        }
        return root;
    }

    public boolean search(int target){
        return searchHelper(root, target);
    }

    private boolean searchHelper(Node root, int target){
        if(root == null){
            return false;
        }

        if(target == root.data){
            return true;
        }else if(target < root.data){
            return searchHelper(root.left, target);
        }else{
            return searchHelper(root.right, target);
        }
    }

    public int countNodes(){
        return countNodesHelper(root);
    }

    public int countLeaves(){
        return countLeavesHelper(root);
    }

    public int countNodesHelper(Node root){
        if(root==null){
            return 0;
        }
        return 1 + countNodesHelper(root.left) + countNodesHelper(root.right);
    }

    public int countLeavesHelper(Node root){
        if(root==null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        return countLeavesHelper(root.left) + countLeavesHelper(root.right);
    }

    public void inOrder(){
        inOrderHelper(root);
        System.out.println();
    }

    public void postOrder(){
        postOrderHelper(root);
        System.out.println();
    }

    public void preOrder(){
        preOrderHelper(root);
        System.out.println();
    }

    public void inOrderHelper(Node root){
        if(root==null){
            return;
        }
        inOrderHelper(root.left);
        System.out.print(root.data + " ");
        inOrderHelper(root.right);
    }

    public void postOrderHelper(Node root){
        if(root==null){
            return;
        }
        postOrderHelper(root.left);
        postOrderHelper(root.right);
        System.out.print(root.data + " ");
    }

    public void preOrderHelper(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preOrderHelper(root.left);
        preOrderHelper(root.right);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        String line = sc.nextLine().trim();
        String[] parts = line.split("\\s+");
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < parts.length; i++) {
            tree.insert(Integer.parseInt(parts[i]));
        }
        int target = sc.nextInt();

        System.out.print("Preorder : ");
        tree.preOrder();
        System.out.print("Inorder : ");
        tree.inOrder();
        System.out.print("Postorder : ");
        tree.postOrder();
        System.out.println("Search " + target + " : " + tree.search(target));
        System.out.println("Nodes : " + tree.countNodes());
        System.out.println("Leaves : " + tree.countLeaves());

        sc.close();
    }
}