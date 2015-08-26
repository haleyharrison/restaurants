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
    @Test
    public void update_updatesInformationForAnObject() {
      Cuisine newCuisine = new Cuisine("thai");
      newCuisine.save();
      newCuisine.update("mexican");
      Cuisine savedCuisine = Cuisine.find(newCuisine.getId());
      assertEquals("mexican", savedCuisine.getName());
    }

    @Test
    public void delete_checkThatDeletesFromDatabase_false() {
      Cuisine newCuisine = new Cuisine("Japanese");
      newCuisine.save();
      newCuisine.delete();
      Cuisine otherCuisine = Cuisine.find(newCuisine.getId());
      assertEquals(false, newCuisine.equals(otherCuisine));
    }
}
