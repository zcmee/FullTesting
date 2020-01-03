package zcmee.com.github.FullTesing.example1;

import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertFalse;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newAccountShouldNotBeActivateAfterCreation() {
        //given
        //when
        Account account = new Account();
        //then
        assertFalse(account.isActive());
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

}
