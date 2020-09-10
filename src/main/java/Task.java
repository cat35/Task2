import java.util.ArrayList;
import java.util.List;

public class Task {

    public static void main(String[] args) throws InterruptedException {
        int quantity = Integer.parseInt(args[0]);
        final Store store = new Store();
        Customer.setCyclicBarrier(quantity);
        int sumOfPurchases = 0;
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Customer customer = new Customer(store);
            list.add(customer);
            customer.start();
        }

        for (Customer customer : list) {
            customer.join();
        }


        for (Customer customer : list) {
            sumOfPurchases += customer.getPurchase();
            System.out.println(customer.getGoods() + " " + " | " + customer.getPurchase());
        }

        System.out.println("Всего было соверешено покупок " + sumOfPurchases);

    }

}
