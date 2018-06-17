package test;

import org.junit.Test;
import wfy.tree.UnderflowException;

import java.io.Serializable;

public class TestPro {
    @Test
    public void testWiteBox(){
        int i =0;
    try {

       i++;
        System.out.println("测试"+i);



    }
    finally {
        i++;
        System.out.println("finally"+i);
    }

    }
}
