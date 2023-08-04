abstract class Product {
    // TODO: Implement this class.
    private String name;
    private int price;
    private int stock;
    
    protected Product(){

    }
    
    protected Product(String name , int price , int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int checkDiscount(int quantity) {
        // TODO: Implement this method.
        return 0;
    }
}
