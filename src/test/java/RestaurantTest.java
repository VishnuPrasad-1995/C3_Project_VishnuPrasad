//import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
   //LocalTime openingTime = LocalTime.now().minusSeconds(60); this can be used whenever we find not to change time manually
   //LocalTime closingTime = LocalTime.now().plusSeconds(60);
    LocalTime openingTime = LocalTime.parse("01:30:00");
    LocalTime closingTime = LocalTime.parse("10:00:00");
    Restaurant restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.now().minusSeconds(60);
        LocalTime closingTime = LocalTime.now().plusSeconds(60);
        Restaurant restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertTrue(restaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.now().plusSeconds(60);
        LocalTime closingTime = LocalTime.now().minusSeconds(60);
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}