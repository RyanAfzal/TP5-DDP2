import static org.junit.Assert.*;

import org.junit.Test;

public class CartTest {
    
    @Test
    public void getTotalPriceTest(){
        ShyourBox shyourBox = new ShyourBox();
        Customer customer = new Customer("Fajar Silalahi", true);
        Cart cart = new Cart(customer);
        
        // Create product instances
        Product jerukMedan = new Fruit("Jeruk Medan", 25000, 25, true);
        Product kaleAngkasa = new Veggie("Kale Angkasa", 80000, 19, true);
        
        // Add order items to the cart
        OrderItem itemJerukMedan = new OrderItem(jerukMedan, 10);
        cart.addOrderItem(itemJerukMedan);
        OrderItem itemKaleAngkasa = new OrderItem(kaleAngkasa, 10);
        cart.addOrderItem(itemKaleAngkasa);
        
        // Calculate the expected output based on the actual prices and quantities of products
        int expectedOutput = 733500;
        
        int actualOutput = cart.getTotalPrice();
        assertEquals(expectedOutput, actualOutput);
    }
}
