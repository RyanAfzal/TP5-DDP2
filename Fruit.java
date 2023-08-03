public class Fruit extends Product {
    private boolean isLocal = false;
    
    // TODO: Implement this class.

    public Fruit(){
        
    }

    public Fruit(String name, int price , int stock, boolean isLocal){
        super(name, price, stock);
        this.isLocal = isLocal;
    }

    private boolean isLocal(){
        return isLocal;
    }

    @Override
    public int checkDiscount(int quantity) {
        // TODO: Implement this method.
        int discount = 0;

        if (isLocal()){
            if(quantity >= 3){
                discount = (int) 20/100;
            }

            else if(quantity >= 5){
                discount = (int) 30/100;
            }
        }

        else{
            if(quantity >= 3){
                discount = (int) 15/100;
            }

            else if(quantity >= 5){
                discount = (int) 20/100;
            }
        }
        return discount;
    }
}
