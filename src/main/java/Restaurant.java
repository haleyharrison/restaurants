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

  //constructor method, initializes an instance of Restaurant
  public Restaurant (String name, String hours, String contact_info, int rating, int cuisine_id) {
    this.name = name;
    this.hours = hours;
    this.contact_info = contact_info;
    this.rating = rating;
    this.cuisine_id = cuisine_id;
  }

  public static List<Restaurant> all()  {
    String sql = "SELECT name, hours, contact_info, rating, cuisine_id FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

}
