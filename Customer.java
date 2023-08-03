public class Customer {
    // TODO: Implement this class.
    private String name;
    private boolean isPremium;

    public Customer (){

    }
    
    public Customer(String name , boolean isPremium){
        this.name = name;
        this.isPremium = isPremium;
    }

    public boolean isPremium(){
        return isPremium;
    }

}
