package test.design;

import java.util.*;

/**
 * @author wangfy
 * @Description 扁平化嵌套列表迭代器
 * 给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的项或者为一个整数，或者是另一个列表。
 * @date 2018/12/3
 **/
public class NestedIterator implements Iterator<Integer> {

    public static void main(String[] args) {
        List<NestedInteger> list = new ArrayList<>();
        List<NestedInteger> subListOne = new ArrayList<>();
        subListOne.add(new NestedInteger(2));
        subListOne.add(new NestedInteger(3));
        List<NestedInteger> subListTwo = new ArrayList<>();
        subListTwo.add(new NestedInteger(4));
        subListTwo.add(new NestedInteger(5));
        list.add(new NestedInteger(1));
        list.add(new NestedInteger(subListOne));
        list.add(new NestedInteger(subListTwo));
        Iterator<Integer> integerIterator = new NestedIterator(list);

        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }

    }


    ArrayDeque<Integer> stack = new ArrayDeque<>();
    Iterator<Integer> integerIterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        add(nestedList);
        integerIterator = stack.iterator();
    }

    private void add(List<NestedInteger> list) {
        for (int i = 0; i < list.size(); i++) {
            NestedInteger o = list.get(i);
            if (o.isInteger()) {
                stack.addLast(o.getInteger());
            } else {
                add(o.getList());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return integerIterator.hasNext();
    }

    @Override
    public Integer next() {
        return integerIterator.next();
    }

    public static class NestedInteger {

        public NestedInteger(Integer val) {
            this.val = val;
        }

        public NestedInteger(List<NestedInteger> nestedIntegerList) {
            this.nestedIntegerList = nestedIntegerList;
        }

        private List<NestedInteger> nestedIntegerList;
        private Integer val;

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return val != null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return val;
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return nestedIntegerList;
        }
    }
}
