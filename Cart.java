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
        int totalPrice = 0;

        for (OrderItem item : orderList){
            totalPrice += item.getFinalPrice();
        }

        if(this.customer.isPremium() && totalPrice >= 300000){
            int discount = totalPrice * 10/100;
            totalPrice -= discount;
        }

        return totalPrice;
    }

    public void addOrderItem(OrderItem orderItem) {
        // TODO: Implement this method.
        this.orderList.add(orderItem);
    }

    public ArrayList<OrderItem> getOrderItemList(){
        return orderList;
    }
}
