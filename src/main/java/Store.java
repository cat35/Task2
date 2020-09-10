

public class Store {
   private int balanceOfGoods = 1000;

    public int getBalanceOfGoods() {
        return balanceOfGoods;
    }

    public synchronized int changeBalance(int count){
        if(balanceOfGoods < count){
            count = balanceOfGoods;
            balanceOfGoods = 0;
        }
        balanceOfGoods -= count;

        return count;
    }
}
