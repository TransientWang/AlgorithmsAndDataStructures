package dbcon;

public class Test {
    public static void main(String[] args) {


        ddMap ddMap = (dbcon.ddMap) ProxFactory.getProx(dbcon.ddMap.class);


        ddMap.getDD();
        ddMap.getPrep();


    }
}
