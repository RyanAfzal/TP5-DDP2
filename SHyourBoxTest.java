import static org.junit.Assert.*;
import org.junit.Test;

public class SHyourBoxTest {

    @Test
    public void searchProductTest(){
        ShyourBox shyourBox = new ShyourBox();
        Product expectedOutput = new Fruit("Apel Malang", 18000, 36, true);
        shyourBox.getProductList().add(expectedOutput);
        Product actualOuput = shyourBox.searchProduct("Apel Malang");
        assertEquals(expectedOutput, actualOuput);
    }
}
