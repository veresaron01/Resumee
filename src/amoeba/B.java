package amoeba;

public class B extends A{

    int b;
    public B(int d, int e){
        super(d);
        b = e;
    }

    public void task () {
        int k = b;
    }

    A aa = new A (1);
    B bb = new B(2,3);
    //B bbb = new B (4);

    int asd = getit();

}
