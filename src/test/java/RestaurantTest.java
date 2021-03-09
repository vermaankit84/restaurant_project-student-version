import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
  Restaurant restaurant;

  @Test
  void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
    assertTrue(restaurant.isRestaurantOpen(LocalTime.parse("12:32:22")));
  }

  @Test
  void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
    assertFalse(restaurant.isRestaurantOpen(LocalTime.parse("22:32:22")));
  }

  @BeforeEach
  public void init() {
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
    restaurant.addToMenu("Sweet corn soup", 119);
    restaurant.addToMenu("Vegetable lasagne", 269);
  }

  @Test
  void adding_item_to_menu_should_increase_menu_size_by_1() {
    int initialMenuSize = restaurant.getMenu().size();
    restaurant.addToMenu("Sizzling brownie", 319);
    assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
  }

  @Test
  void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
    int initialMenuSize = restaurant.getMenu().size();
    restaurant.removeFromMenu("Vegetable lasagne");
    assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
  }

  @Test
  void removing_item_that_does_not_exist_should_throw_exception() {
    assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
  }
}
