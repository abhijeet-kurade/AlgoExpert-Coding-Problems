public class Threading {
    public static void main(String[] args) throws Exception {
        Trd1 t = new Trd1();
        t.start();

        Trd2 t2 = new Trd2();
        Thread td = new Thread(t2);
        td.start();

    }
}


class Trd1 extends Thread{

    @Override
    public void run() {
        for(int i =0; i<10; i++){
            System.out.println("Trd 1 -" + i);
        }
    }
}

class Trd2 implements  Runnable{

    @Override
    public void run() {
        for(int i =0; i<10; i++){
            System.out.println("Trd 2 -" + i);
        }
    }

}
