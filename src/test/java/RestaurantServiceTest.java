import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException
    {
        service.addRestaurant("HELLO","ROYAL NAGAR",LocalTime.of(9,00,00),LocalTime.of(11,00,00));
        assertEquals(service.getRestaurants().get(0),service.findRestaurantByName("HELLO"));
    }
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException
    {
        service.addRestaurant("HELLO","ROYAL NAGAR",LocalTime.of(9,00,00),LocalTime.of(11,00,00));
        service.addRestaurant("DELLO","ROAL NAGAR",LocalTime.of(9,00,00),LocalTime.of(11,00,00));
        assertNotEquals(service.getRestaurants().get(0),service.findRestaurantByName("DELLO"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void  returning_correct_order_value() throws itemNotFoundException
    {
        restaurant = new Restaurant("HELLO","ROYAL NAGAR",LocalTime.of(9,00,00),LocalTime.of(11,00,00));
        restaurant.addToMenu("chineis",200);
        assertEquals(200,restaurant.orderValue("chineis"));
    }
    @Test
    public void failing_test_case_of_returning_correct_order_value() throws itemNotFoundException
    {
        restaurant = new Restaurant("HELLO","ROYAL NAGAR",LocalTime.of(9,00,00),LocalTime.of(11,00,00));
        restaurant.addToMenu("chineis",200);
        assertNotEquals(205,restaurant.orderValue("chineis"));
    }
    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}