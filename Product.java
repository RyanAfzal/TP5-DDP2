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

    public String getProductName(){
        return name;
    }

    public int getStock(){
        return stock;
    }

    public int getPrice(){
        return price;
    }
    
    public abstract int checkDiscount(int quantity);
        // TODO: Implement this method.

    public void setStock(int jumlahDibeli){
        this.stock -= jumlahDibeli;
    }
}
