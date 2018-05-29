package wfy.list;

import java.util.Iterator;

public class test {

    public static void main(String[] args) {
//        MyArrayList<Integer> arrayList = new MyArrayList<>();
//
//        arrayList.add(10);
//        arrayList.add(5);
//        arrayList.add(8);
//        arrayList.add(3,15);
//
//        System.out.println("长度"+ arrayList.get(0));
//
//        Iterator<Integer> iterator = arrayList.iterator();
//
//        while (iterator.hasNext()){
//            System.out.print(iterator.next()+" 、");
//        }

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(1);
        linkedList.add(1,3);


        System.out.println(linkedList.isEmpty());
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());

        }

    }
}
