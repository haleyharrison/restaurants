import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

  public class RestaurantTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
    }

    @Test
    public void equals_returnTrueIfRestaurantsAreTheSame() {
      Restaurant firstRestaurant = new Restaurant("mi mero mole", "9:00-15-00", "23424231", 5, 1);

    }
    //
    // private String name;
    // private String hours;
    // private String contact_info;
    // private int rating;
    // private int cuisine_id;
    //
  }
