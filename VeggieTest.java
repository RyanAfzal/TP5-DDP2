import static org.junit.Assert.*;
import org.junit.Test;

public class VeggieTest{

    @Test
    public void checkDiscountFruitTest(){
        Veggie sayur = new Veggie("Wortel Brastagi", 40000, 10, true);
        int expectedOutput = 10;
        int actualOutput = sayur.checkDiscount(4);
        assertEquals(expectedOutput, actualOutput);
    }
}
