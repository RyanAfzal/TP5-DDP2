import java.util.ArrayList;

public class Cart {
    private Customer customer;
    private ArrayList<OrderItem> orderList;

    public Cart(){

    }
    
    Cart(Customer customer , ArrayList<OrderItem> orderList) {
        // TODO: Implement this method.
        this.customer = customer;
        this.orderList = orderList;
    }

    public int getTotalPrice() {
        // TODO: Implement this method.
        return 0;
    }

    public void addOrderItem(OrderItem orderItem) {
        // TODO: Implement this method.
    }
}
