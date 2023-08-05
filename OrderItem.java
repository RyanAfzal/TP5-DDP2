public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(){

    }

    public OrderItem(Product product , int quantity) {
        // TODO: Implement this method.
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getFinalPrice() {
        // TODO: Implement this method.
        return (int) (this.product.getPrice() - (this.product.getPrice() * this.product.checkDiscount(this.quantity)/100)) * this.quantity;
    }
}
