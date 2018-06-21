package Lamda;

@FunctionalInterface
public interface Go {
    public void run();

    default void running(){
        System.out.println("奔跑吧");
    }


}
