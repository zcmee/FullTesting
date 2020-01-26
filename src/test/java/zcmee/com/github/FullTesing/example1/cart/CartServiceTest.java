package zcmee.com.github.FullTesing.example1.cart;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import zcmee.com.github.FullTesing.example1.order.Order;
import zcmee.com.github.FullTesing.example1.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.canHandleCart(cart)).willReturn(true);
        //when
        Cart resultCart = cartService.processCart(cart);
        //then
        verify(cartHandler).canHandleCart(cart);
        verify(cartHandler, times(1)).canHandleCart(cart);
        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));

        //Tutaj sprawdzamy, metody na mocku zostaną wywyałane w tej kolejnosci
        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);
    }

    @Test
    void processCartShouldNotSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.canHandleCart(cart)).willReturn(false);
        //when
        Cart resultCart = cartService.processCart(cart);
        //then
        verify(cartHandler).canHandleCart(cart);
        verify(cartHandler, times(1)).canHandleCart(cart);
        verify(cartHandler, times(0)).sendToPrepare(cart);
        verify(cartHandler, never()).sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }
}
