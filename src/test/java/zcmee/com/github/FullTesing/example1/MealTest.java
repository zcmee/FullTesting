package zcmee.com.github.FullTesing.example1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {
    private Order order;

    @BeforeEach
        // w JUnit 4 było Before
    void initializeOrder() {
        System.out.println("Inside @BeforeEach method");
        this.order = new Order();
    }

    @AfterEach
        // w JUnit 4 było After
    void cleanUp() {
        System.out.println("Inside @AfterEach method");
        order.cancel();
    }

    @Test
    void shouldDiscountedPrice() throws Exception {
        //given
        Meal meal = new Meal(35);
        //when
        int discountedPrice = meal.getDiscountedPrice(12);
        //then
        assertEquals(23, discountedPrice);
        assertThat(discountedPrice, equalTo(23));
    }

    @Test
    void referencesToSameObjectShouldBeEqual() {
        //given
        Meal meal = new Meal(11);
        Meal meal2 = meal;
        //then
        //sluzy do porownywania referencji
        assertSame(meal, meal2);
        assertThat(meal, sameInstance(meal2));
    }

    @Test
    void referencesToDifferentObjectsShouldNotBeEqual() {
        //given
        Meal meal = new Meal(11);
        Meal meal2 = new Meal(11);
        //then
        //sluzy do porownywania referencji
        assertNotSame(meal, meal2);
        assertThat(meal, not(sameInstance(meal2)));
    }

    @Test
    void twoMealsShouldBeEqualWhenThePriceAndNaneAreTheSame() {
        //given
        Meal meal = new Meal(29, "PizzaPeperonii");
        Meal meal2 = new Meal(29, "PizzaPeperonii");

        //when
        assertEquals(meal, meal2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOrder() {
        //given
        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addMealsToOrderShouldIncreaseOrderSize() {
        //given
        Meal meal1 = new Meal(23, "Burder");
        Meal meal2 = new Meal(18, "Picca");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        //then
        assertThat(order.getMeals(), not(empty()));
        assertThat(order.getMeals().size(), equalTo(2));
        assertThat(order.getMeals(), hasSize(2));

        assertThat(order.getMeals(), contains(meal1, meal2));
        assertThat(order.getMeals(), hasItem(meal1));
    }

    @Test
    void removingMealsFromOrderShouldDecraseOrderSize() {
        //given
        Meal meal1 = new Meal(23, "Burder");
        Meal meal2 = new Meal(18, "Picca");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.removeMealFromOrder(meal1);
        //then
        assertThat(order.getMeals(), not(empty()));
        assertThat(order.getMeals().size(), equalTo(1));
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), not(hasItem(meal1)));
        assertThat(order.getMeals(), hasItem(meal2));
    }

    @Test
    void testIfTwoMealListAreTheSame() {
        //given
        Meal meal1 = new Meal(23, "Burder");
        Meal meal2 = new Meal(18, "Picca");
        Meal meal3 = new Meal(5, "Zapiekanka");

        List<Meal> mealList = new ArrayList<Meal>(Arrays.asList(meal1, meal2));
        List<Meal> mealList2 = new ArrayList<Meal>(Arrays.asList(meal2, meal1));
        //then
        assertThat(mealList, containsInAnyOrder(mealList2.toArray()));
    }

    @Test
    void exceptionShouldBeThrownWhenTheDiscoundIsHigherThanThePrice() {
        //given
        Meal meal = new Meal(10, "Soup");
        //when
        //then
        //W JUnit 4 pisało się w adnotacji klasę wyjątku @Test(excepted = IllegalArgumentException.class) w JUNIT 5 pisze sie assertThrows
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(20));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18, 20})
    void mealPricesShouldBeLowerThan20(int price) {
        assertThat(price, lessThanOrEqualTo(20));
    }


    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price) {
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String cakeName) {
        assertThat(cakeName, notNullValue());
        assertThat(cakeName, endsWithIgnoringCase("cake"));
    }

    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8})
    void mealPricesShouldBeLowerThan10(int price) {
        if (price > 5) {
            throw new IllegalArgumentException();
        }
        assertThat(price, lessThanOrEqualTo(20));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Dynamic test 1", () -> assertThat(5, lessThan(6))),
                DynamicTest.dynamicTest("Dynamic test 2", () -> assertEquals(4, 2 * 2))
        );
    }

    @TestFactory
    Collection<DynamicTest> calculateMealPrice() {
        Order order = new Order();
        order.addMealToOrder(new Meal(12, 2, "Hamburger"));
        order.addMealToOrder(new Meal(7, 4, "Fries"));
        order.addMealToOrder(new Meal(22, 3, "Pizza"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for(int i = 0; i < order.getMeals().size(); ++i){
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(67));
            };

            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }

}
