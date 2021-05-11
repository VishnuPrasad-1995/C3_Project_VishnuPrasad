//import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
   //LocalTime openingTime = LocalTime.now().minusSeconds(60); this can be used whenever we find not to change time manually
   //LocalTime closingTime = LocalTime.now().plusSeconds(60);
    LocalTime openingTime = LocalTime.parse("01:30:00");
    LocalTime closingTime = LocalTime.parse("17:00:00");
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

    //<<<two failing test case scenario>>>>
    @Test
    public void any_item_selected_in_Menu_order_value_should_not_have_order_value_zero() {
        LocalTime openingTime = LocalTime.parse("01:30:00");
        LocalTime closingTime = LocalTime.parse("19:00:00");
        Restaurant restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        ArrayList<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Sweet corn soup");//one item selected
       // selectedItems.add("Vegetable lasagne");
        assertNotEquals(restaurant.orderValue(selectedItems),0);//order value not equals to zero
    }
    @Test
    public void Zero_item_selected_Menu_should_not_show_any_order_value() {
        LocalTime openingTime = LocalTime.parse("01:30:00");
        LocalTime closingTime = LocalTime.parse("19:00:00");
        Restaurant restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        ArrayList<String> selectedItems = new ArrayList<String>();
        //selectedItems.add("Sweet corn soup");//no item selected
        // selectedItems.add("Vegetable lasagne");
        assertNotEquals(restaurant.orderValue(selectedItems),119);//empty arraylist passed and order value equals to zero

    }
    //<<<pass test case scenario>>>>
    @Test
    public void if_multiple_item_selected_in_Menu_correct_order_value_shown() {
        LocalTime openingTime = LocalTime.parse("01:30:00");
        LocalTime closingTime = LocalTime.parse("19:00:00");
        Restaurant restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        ArrayList<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Sweet corn soup");//multiple item selected
        selectedItems.add("Vegetable lasagne");//multiple item selected
        assertEquals(restaurant.orderValue(selectedItems),388);//correct order value shown
    }

    @Test
    public void if_zero_item_selected_in_Menu_zero_order_value_shown(){
        LocalTime openingTime = LocalTime.parse("01:30:00");
        LocalTime closingTime = LocalTime.parse("19:00:00");
        Restaurant restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        ArrayList<String> selectedItems = new ArrayList<String>();
        //selectedItems.add("Sweet corn soup");//item selected
        //selectedItems.add("Vegetable lasagne");
        assertEquals(restaurant.orderValue(selectedItems),0);//zero order value shown
    }

    @Test
    public void if_one_item_selected_in_Menu_correct_order_value_shown(){
        LocalTime openingTime = LocalTime.parse("01:30:00");
        LocalTime closingTime = LocalTime.parse("19:00:00");
        Restaurant restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        ArrayList<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Sweet corn soup");//one item selected
        //selectedItems.add("Vegetable lasagne");
        assertEquals(restaurant.orderValue(selectedItems),119);//order value shown for one item
    }


}
