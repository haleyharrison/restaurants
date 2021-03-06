import java.util.List;
import org.sql2o.*;

public class Restaurant {
  private String name;
  private String hours;
  private String contact_info;
  private int rating;
  private int cuisine_id;
  private int id;

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
  public int getId() {
    return id;
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
    String sql = "SELECT id, name, hours, contact_info, rating, cuisine_id FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }
  @Override
  public boolean equals(Object otherRestaurant) {
    if(!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getHours().equals(newRestaurant.getHours()) &&
        this.getContact_info().equals(newRestaurant.getContact_info()) &&
        this.getRating() == newRestaurant.getRating() &&
        this.getCuisine_id() == newRestaurant.getCuisine_id();
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants ( name, hours, contact_info, rating, cuisine_id) VALUES ( :name, :hours, :contact_info, :rating, :cuisine_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("hours", this.hours)
      .addParameter("contact_info", this.contact_info)
      .addParameter("rating", this.rating)
      .addParameter("cuisine_id", this.cuisine_id)
      .executeUpdate()
      .getKey();
    }
  }
  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE id=:id";
      Restaurant restaurant = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
        return restaurant;
    }
  }
  public static List<Restaurant> allByCuisine(int cuisine_id)  {
    String sql = "SELECT id, name, hours, contact_info, rating, cuisine_id FROM restaurants WHERE cuisine_id = :cuisine_id ";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .executeAndFetch(Restaurant.class);
    }
  }
  public void updateRating(int rating) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET rating = :rating WHERE id = :id";
      con.createQuery(sql)
        .addParameter("rating", rating)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void updateContact_info(String contact_info) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET contact_info = :contact_info WHERE id = :id";
      con.createQuery(sql)
        .addParameter("contact_info", contact_info)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void updateHours(String hours) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET hours = :hours WHERE id = :id";
      con.createQuery(sql)
        .addParameter("hours", hours)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM restaurants WHERE id = :id";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }
}
