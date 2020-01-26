package zcmee.com.github.FullTesing.example1.cart;

import zcmee.com.github.FullTesing.example1.order.OrderStatus;

public class CartService {
    private final CartHandler cartHandler;

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    Cart processCart(Cart cart) {
        if (cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.PREPARING));
        } else {
            cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.REJECTED));
        }
        return cart;
    }
}
