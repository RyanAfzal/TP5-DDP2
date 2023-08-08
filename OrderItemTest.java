import static org.junit.Assert.*;
import org.junit.Test;

public class OrderItemTest {
    
    @Test
    public void getFinalPriceTest(){
        OrderItem item = new OrderItem(new Fruit("Apel Malang", 18000, 36, true), 100);
        int expectedOutput = 1260000;
        int actualOutput = item.getFinalPrice();
        assertEquals(expectedOutput, actualOutput);
    }
}
