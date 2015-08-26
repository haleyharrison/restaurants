import java.util.List;
import org.sql2o.*;

public class Restaurant {
  private String name;
  private String hours;
  private String contact_info;
  private int rating;
  private int cuisine_id;

  public String getName() {
    return name;
  }
  public String getHours() {
    return hours;
  }
  public String getContact_info() {
    return contact_info;
  }
  public int getRating() {
    return rating;
  }
  public int getCuisine_id() {
    return cuisine_id;
  }
}
