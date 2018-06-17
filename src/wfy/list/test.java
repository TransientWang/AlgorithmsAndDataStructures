package wfy.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

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

//        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
//        linkedList.add(1);
//        linkedList.add(1,3);


//        System.out.println(linkedList.isEmpty());
//        Iterator<Integer> iterator = linkedList.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//
//        }


        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {

                return 1;
            }
        };

        System.out.println(threadLocal.get());

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(1,1);
        hashMap.put(null,null);
        hashMap.put(null,2);
        hashMap.forEach((key,value) -> System.out.println("key:"+key+ " " + "value:"+value));




    }
}
