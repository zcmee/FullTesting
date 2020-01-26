package zcmee.com.github.FullTesing.example1.cart;

public interface CartHandler {
    boolean canHandleCart(Cart cart); //czy jest wstanie zrealizowac wszystkie zamowienia
    void sendToPrepare(Cart cart);
}
