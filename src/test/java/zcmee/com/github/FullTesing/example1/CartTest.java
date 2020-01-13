package zcmee.com.github.FullTesing.example1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.jupiter.api.Assertions.*;

//@Disabled //Ignoruje wszystkie testy w klasie
class CartTest {

    //    @Disabled //Ignoruje dany test
    //W JUnit 4 była to adnotacja @Ignore
    @Test
    //JUnit 4 podawało sie w adnotacji i był to czas ms @Test(timeout=10)
    @DisplayName("NAzwa Super Testu")
    //Zmiana nazwy testu na włąśna
    void simulateLargeOrder() {
        //given
        Cart card = new Cart();
        //when
        //then
        assertTimeout(Duration.ofMillis(10), card::simulateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart() {
        //given
        Cart cart = new Cart();
        Meal meal = new Meal(16, "Frytki");
        Order order = new Order();
        order.addMealToOrder(meal);
        //when
        cart.addOrderToCart(order);
        //then
        //Asercja zostanie spełniony, gdy dowolny z warunkow zostanie spełniony. Wystarczy tylko 1 spełniony by było git
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                hasSize(3), // <--- wartosc nieprawdziwa
                is(not(empty()))
        ));

        //Asercja zostanie spełniony, gdy wszystkie z warunkow zostana spełnione. Wszystkie warunki muszą być spełnione by asercja się powiodła
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty()))
        ));

        //w porownaniu do powyzszego matchera allOf, to nie jestemy uwiazani jednym elementem. W tym przypadku cart.getOrders()
        //dodatkowo, ta metoda pokazuje wszystkie nie spełnione warunki
        assertAll(
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class))))
        );
    }


}
