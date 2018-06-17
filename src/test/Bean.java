package test;



public class Bean {
    private int A;
    private int B;
    private int C;
    private int D;

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getA() {

        return A;
    }

    public void setA(int a) {
        A = a;
    }

    protected int whiteBox(int a,int b,int x){
        if (a > A && b == B)
            x/=a;
        if (a == C || x> D)
            x+=1;
        return x;
    }
}
