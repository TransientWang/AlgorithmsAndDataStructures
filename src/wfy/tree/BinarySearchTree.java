package wfy.tree;



import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {
        private AnyType emelent;
        private BinaryNode<AnyType> left;
        private BinaryNode<AnyType> right;

        public BinaryNode(AnyType emelent) {
            this(emelent, null, null);
        }

        public BinaryNode(AnyType emelent, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.emelent = emelent;
            this.left = left;
            this.right = right;
        }
    }


    public BinaryNode<AnyType> root;

    public BinarySearchTree() {
        this.root = null;
    }


    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.emelent);
        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;

    }


    public AnyType findMin() throws UnderflowException {
        if (isEmpty()) throw new UnderflowException();


        return findMin(root).emelent;

    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (root == null)
            return null;

        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    public AnyType findMax() throws UnderflowException {
        if (isEmpty()) throw new UnderflowException();


        return findMax(root).emelent;

    }

    public BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null){
            while (t.right != null)
                t = t.right;

        }
        return t;
    }

    public void insert(AnyType x) {

        root = insert(x, root);
    }


    public BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {

        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.emelent);
        System.out.println("结果" + compareResult);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else ;
        return t;

    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;
        int compareResult =x.compareTo(t.emelent);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.emelent = findMin(t.right).emelent;
            t.right = remove(t.emelent, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;

        return t;

    }

    public void printTree() {
        System.out.println("开始遍历");
        printTree(root);
    }

    private void printTree(BinaryNode<AnyType> t) {

        if (t != null) {
            printTree(t.left);
            System.out.println(t.emelent + " ");
            printTree(t.right);
        }
    }


}
