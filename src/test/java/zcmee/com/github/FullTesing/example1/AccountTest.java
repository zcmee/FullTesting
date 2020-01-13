package zcmee.com.github.FullTesing.example1;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Test
    void newAccountShouldNotBeActivateAfterCreation() {
        //given
        //when
        Account account = new Account();
        //then
        assertFalse(account.isActive());

        //za pomoca hamcrest
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));
    }

    //BDD - dobra zasada, by testować jendą ścięzke funkcjonalności
    @Test
    void accontShouldBeActiveAfterActivation() {
        //given
        Account account = new Account();
        //when
        account.activate();
        //then
        assertTrue(account.isActive());
    }

    @Test
    void newlyCreatedAccountAccountShouldNotHaveDeliveryAddress() {
        //given
        Account account = new Account();
        //when
        Address address = account.getAddress();
        //then
        assertNull(address);
        assertThat(address, is(nullValue()));
    }

//    @Test
    @RepeatedTest(25) //Tyle razy zostanie uruchomiony test
    void newAccountWithNotNullAddressShouldBeActive() {
        //given
        Address address = new Address("Puławska", "44/12");
        //when
        Account account = new Account(address);
        //then
        //jesli warunek nie zostanie spelniony, to wszystkie asercje zostana pominięte
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }

}
