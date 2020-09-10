import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer extends Thread {

    private final Store store;

    private int goods = 0;

    private int purchase = 0;

    private static CyclicBarrier cyclicBarrier;

    public Customer(Store store){
        this.store = store;
    }

    public static void setCyclicBarrier(int quantity){
        cyclicBarrier = new CyclicBarrier(quantity);
    }

    public int getPurchase() {
        return purchase;
    }

    public int getGoods() {
        return goods;
    }

    public void sum(int m){
        goods += m;
    }


    @Override
    public void run() {
        int temp;
        while (store.getBalanceOfGoods() > 0) {
           try {
                cyclicBarrier.await();
                temp = store.changeBalance((int) ((Math.random() * (10)) + 1));
                if(temp !=0) {
                    sum(temp);
                    purchase++;
                }
               cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
               e.printStackTrace();
           }
        }
    }
}
