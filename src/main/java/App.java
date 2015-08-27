import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.out;
import java.lang.*;

import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/",  (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cuisines.vtl");

      model.put("cuisines", Cuisine.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/new", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cuisine-form.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisines", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String cuisine = request.queryParams("cuisine");
      Cuisine newCuisine = new Cuisine(cuisine);
      newCuisine.save();

      model.put("cuisine", newCuisine);
      model.put("template", "templates/cuisine.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("cuisines/:id/restaurants/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/restaurant-form.vtl");

      model.put("cuisine", Cuisine.find(Integer.parseInt(request.params(":id"))));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/restaurant.vtl");
      String name = request.queryParams("name");
      String hours = request.queryParams("hours");
      String contact_info = request.queryParams("contact_info");
      int rating = Integer.parseInt(request.queryParams("rating"));
      int cuisine_id = Integer.parseInt(request.queryParams("cuisine_id"));

      Restaurant newRestaurant = new Restaurant(name, hours, contact_info, rating, cuisine_id);
      newRestaurant.save();

      model.put("restaurant", newRestaurant);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("cuisines/:id/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/restaurants.vtl");

      model.put("cuisine", Cuisine.find(Integer.parseInt(request.params(":id"))));
      model.put("restaurants", Restaurant.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
