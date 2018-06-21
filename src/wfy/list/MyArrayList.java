package wfy.list;



import java.util.Iterator;


public class MyArrayList<AnyType> implements Iterable<AnyType> {
    private static final int DEFAULT_CAPACITY = 10;

    private int thesize;

    private AnyType[] theItems;

    public MyArrayList() {
        doclear();
    }

    public void clear() {
        doclear();
    }

    public void doclear() {
        thesize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return thesize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public AnyType get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];

    }

    public AnyType set(int idx, AnyType newVal) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity) {

        if (newCapacity < thesize)
            return;

        AnyType[] old = theItems;

        theItems = (AnyType[]) new Object[newCapacity];

        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }


    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = thesize; i > idx; i--)
            theItems[i] = theItems[i - 1];

        theItems[idx] = x;
        thesize++;
    }

    public AnyType remove(int idx) {
        AnyType removeItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++)
            theItems[i] = theItems[i + 1];

        thesize--;
        return removeItem;
    }


    @Override

    public Iterator<AnyType> iterator() {
        return new ArrayListIterAtor();
    }

    private class ArrayListIterAtor implements java.util.Iterator<AnyType>{
        private int corrent = 0;


        @Override
        public boolean hasNext() {
            return corrent < size();
        }

        @Override
        public AnyType next() {

            if (!hasNext())
                throw new java.util.NoSuchElementException();

            return theItems[corrent++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--corrent);
        }
    }




}
