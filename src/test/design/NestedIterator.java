package test.design;

import java.util.*;

/**
 * @author wangfy
 * @Description 扁平化嵌套列表迭代器
 * 给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的项或者为一个整数，或者是另一个列表。
 * 直接将列表里的数字，放到一个stack中。
 * 用双端队列，因为Stack类是由Vector实现的性能不好。
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


    private ArrayDeque<Integer> stack = new ArrayDeque<>();
    private Iterator<Integer> integerIterator;
    private int sum = 0;
    private int cur = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        add(nestedList);
        sum = stack.size();
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
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        return stack.pollFirst();
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


        public boolean isInteger() {
            return val != null;
        }


        public Integer getInteger() {
            return val;
        }


        public List<NestedInteger> getList() {
            return nestedIntegerList;
        }
    }
}
