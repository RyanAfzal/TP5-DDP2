public class Veggie extends Product{
    // TODO: Implement this class.
    private boolean isOrganic = false;

    public Veggie(){

    }
    
    public Veggie(String name , int price , int stock, boolean isOrganic){
        super(name , price, stock);
        this.isOrganic = isOrganic;
    }

    private boolean isOrganic(){
        return isOrganic;
    }

    @Override
    public int checkDiscount(int quantity) {
        // TODO: Implement this method.
        int discount = 0;

        if (isOrganic()){
            if(quantity >= 3){
                discount = (int) 10/100;
            }

            else if(quantity >= 5){
                discount = (int) 20/100;
            }
        }

        else{
            if(quantity >= 3){
                discount = (int) 20/100;
            }

            else if(quantity >= 5){
                discount = (int) 25/100;
            }
        }
        return discount;
    }
}

