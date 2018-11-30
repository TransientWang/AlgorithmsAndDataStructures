package wfy.tree;

import java.util.concurrent.Callable;

public class RealData implements Callable<Integer> {

    @Override
    public Integer call() {
        return 1;
    }
}
