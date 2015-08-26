import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

  public class CuisineTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
    }

    @Test
    public void equals_returnTrueIfCuisinesAreTheSame() {
      Cuisine firstCuisine = new Cuisine("mexican");
      Cuisine secondCuisine = new Cuisine("mexican");
      assertTrue(firstCuisine.equals(secondCuisine));
    }
    @Test
    public void save_savesIntoDatabase_true() {
      Cuisine myCuisine = new Cuisine("thai");
      myCuisine.save();
      assertTrue(Cuisine.all().get(0).equals(myCuisine));
    }
    @Test
    public void find_findCuisineInDatabase_true() {
      Cuisine myCuisine = new Cuisine("greek");
      myCuisine.save();
      Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
      assertTrue(myCuisine.equals(savedCuisine));
    }
}
