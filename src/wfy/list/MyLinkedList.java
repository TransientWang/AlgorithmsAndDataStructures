package wfy.list;



import java.util.Iterator;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize = 0;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;


    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public void doClear() {

        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);

        beginMarker.next = endMarker;


        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        addBefore( getNode(idx, 0, size()), x);
    }

    public AnyType get(int idx) {

        return getNode(idx).data;
    }


    private void addBefore(Node<AnyType> p, AnyType x) {

        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p = null;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();

        if (idx < size() / 2) {
            System.out.println(111);
            p = beginMarker.next;

            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }

        return p;
    }

    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }


    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }
    }



    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public void remove() {

            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }

        @Override
        public boolean hasNext() {

            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

    }
}
