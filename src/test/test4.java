package com.example.demo;

import java.util.*;

public class test4 {
    static int sum=0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        String a = null;
        String[] c = null;
        if (scanner.hasNextLine()) {
            a = scanner.nextLine();
        }
         c = a.split(" ");

        String[][] b = new String[Integer.parseInt(c[0])][Integer.parseInt(c[1])];


        System.out.println(find(b,0,0));


    }

    public static int find(String[][] b ,int x,int y){
        if (x < b.length && y < b[0].length){
            return find(b,x+1,y) + find(b,x,y+1);
        } else {
            return 1;
        }

    }

}
