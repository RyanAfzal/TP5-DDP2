public class Fruit extends Product {
    private boolean isLocal;
    
    // TODO: Implement this class.

    public Fruit(){
        
    }

    public Fruit(String name, int price , int stock, boolean isLocal){
        super(name, price, stock);
        this.isLocal = isLocal;
    }

    //getter
    public boolean isLocal(){
        return isLocal;
    }

    @Override
    /**
     * Override method superclass nya (Product)
     */
    public int checkDiscount(int quantity) {
        // TODO: Implement this method.
        int discount = 0;

        if (isLocal()){
            if(quantity >= 5){
                discount = 30;
            }

            else if(quantity >= 3){
                discount = 20;
            }
        }

        else{
            if(quantity >= 5){
                discount = 20;
            }

            else if(quantity >= 3){
                discount = 15;
            }
        }
        return discount;
    }
}
