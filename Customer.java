public class Customer {
    // TODO: Implement this class.
    private String name;
    private boolean isPremium;
    private Cart cart = new Cart(this);

    public Customer (){

    }
    
    public Customer(String name , boolean isPremium){
        this.name = name;
        this.isPremium = isPremium;
    }

    //getter
    public String getName(){
        return name;
    }
    
    public boolean isPremium(){
        return isPremium;
    }

    public Cart getCart(){
        return cart;
    }
}
