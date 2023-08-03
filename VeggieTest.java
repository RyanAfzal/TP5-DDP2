import static org.junit.Assert.*;
import org.junit.Test;

public class VeggieTest{

    @Test
    public void checkDiscountFruitTest(){
        Veggie sayur = new Veggie("Wortel Brastagi", 40000, 10, true);
        int expectedOutput = 0;
        int actualOutput = sayur.checkDiscount(36);
        assertEquals(expectedOutput, actualOutput);
    }
}
