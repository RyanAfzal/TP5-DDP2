import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CartTest {
    
    @Test
    public void getTotalPriceTest(){
        Cart cart = new Cart(null, new ArrayList<>());
        int expectedOutput = 0;
        int actualOutput = cart.getTotalPrice();
        assertEquals(expectedOutput, actualOutput);
    }
}
