package wfy.tree;

public class TreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer>  searchTree = new BinarySearchTree<>();
        searchTree.insert(1);
        searchTree.insert(3);
        searchTree.insert(7);
        searchTree.insert(2);
        searchTree.insert(4);
        searchTree.printTree();
        searchTree.remove(33);
        searchTree.printTree();

        try {
            System.out.println("最大");
            System.out.println(searchTree.findMax());
        } catch (UnderflowException e) {
            e.printStackTrace();
        }
    }
}
