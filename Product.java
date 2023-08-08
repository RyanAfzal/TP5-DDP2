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

    //getter
    public String getProductName(){
        return name;
    }

    public int getStock(){
        return stock;
    }

    public int getPrice(){
        return price;
    }
    
    /**
     * Untuk mendapatkan diskon suatu produk
     * @param quantity jumlah suatu produk
     * @return diskon produk
     */
    public int checkDiscount(int quantity){
        // TODO: Implement this method.
        return 0;
    }

    //setter
    public void setStock(int jumlahDibeli){
        this.stock -= jumlahDibeli;
    }
}
