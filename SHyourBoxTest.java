import static org.junit.Assert.*;
import org.junit.Test;

public class SHyourBoxTest {

    @Test
    public void searchProductTest(){
        Product expectedOuput = new Fruit("Apel Malang", 18000, 36, true);
        Product actualOuput = ShyourBox.searchProduct("Apel Malang");
        assertEquals(expectedOuput, actualOuput);
    }
}
