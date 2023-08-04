import static org.junit.Assert.*;
import org.junit.Test;

public class SHyourBoxTest {

    @Test
    public void searchProductTest(){
        ShyourBox shyourBox = new ShyourBox();
        Product expectedOuput = new Fruit("Apel Malang", 18000, 36, true);
        Product actualOuput = shyourBox.searchProduct("Apel Malang");
        assertEquals(expectedOuput, actualOuput);
    }
}
