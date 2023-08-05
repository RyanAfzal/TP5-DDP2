import java.util.ArrayList;

public class Cart {
    private Customer customer;
    private ArrayList<OrderItem> orderList = new ArrayList<>();

    public Cart(){

    }
    
    Cart(Customer customer) {
        // TODO: Implement this method.
        this.customer = customer;
    }

    public int getTotalPrice() {
        // TODO: Implement this method.
        return 0;
    }

    public void addOrderItem(OrderItem orderItem) {
        // TODO: Implement this method.
        this.orderList.add(orderItem);
    }
}
