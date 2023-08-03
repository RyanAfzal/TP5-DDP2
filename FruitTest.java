import static org.junit.Assert.*;
import org.junit.Test;

public class FruitTest{

    @Test
    public void checkDiscountFruitTest(){
        Fruit fruit = new Fruit("Apel Malang", 18000, 36, true);
        int expectedOutput = 0;
        int actualOutput = fruit.checkDiscount(36);
        assertEquals(expectedOutput, actualOutput);
    }
}