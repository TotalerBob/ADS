package praktikum_5;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    public void inorder(Visitor<T> vis) {
        // Check not empty
        if(root == null){
            return;
        }

        if(root.left != null){
            TreeTraversal<T> left = new TreeTraversal<T>(root.left);
            left.inorder(vis);
        }

        vis.visit(root.element);

        if(root.right != null){
            TreeTraversal<T> right = new TreeTraversal<T>(root.right);
            right.inorder(vis);
        }
    }

    public void preorder(Visitor<T> vis) {
        // Check not empty
        if(root == null){
            return;
        }

        vis.visit(root.element);

        if(root.left != null){
            TreeTraversal<T> left = new TreeTraversal<T>(root.left);
            left.preorder(vis);
        }

        if(root.right != null){
            TreeTraversal<T> right = new TreeTraversal<T>(root.right);
            right.preorder(vis);
        }
    }

    public void postorder(Visitor<T> vis) {
        // Check not empty
        if(root == null){
            return;
        }

        if(root.left != null){
            TreeTraversal<T> left = new TreeTraversal<T>(root.left);
            left.postorder(vis);
        }

        if(root.right != null){
            TreeTraversal<T> right = new TreeTraversal<T>(root.right);
            right.postorder(vis);
        }

        vis.visit(root.element);
    }

    public void levelorder(Visitor<T> vis) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

        // Add first
        if(root != null){
            queue.add(root);
        }

        while(!queue.isEmpty()){
            TreeNode<T> node = queue.removeFirst();
            vis.visit(node.element);
            if(node.left != null){
                queue.add(node.left);
            }

            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    @Override
    public void interval(Comparable<T> min, Comparable<T> max, Visitor<T> visitor) {
        // Check not empty
        if(root == null){
            return;
        }

        if(min.compareTo(root.element) <= 0 && max.compareTo(root.element) >= 0){
            visitor.visit(root.element);
        }

        if(root.left != null){
            TreeTraversal<T> left = new TreeTraversal<T>(root.left);
            left.interval(min, max, visitor);
        }

        if(root.right != null){
            TreeTraversal<T> right = new TreeTraversal<T>(root.right);
            right.interval(min, max, visitor);
        }
    }
}