import java.util.List;
import org.sql2o.*;

public class Cuisine {
  private int id;
  private String name;

  public String getName {
    return name;
  }
  public int getInt() {
    return id;
  }
  public Cuisine(String name) {
    this.name = name;
  }
  public static List<Cuisine> all() {
    String sql = "SELECT name FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }
}
