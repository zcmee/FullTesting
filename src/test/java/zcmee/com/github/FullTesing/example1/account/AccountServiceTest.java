package zcmee.com.github.FullTesing.example1.account;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Test
    void getAllActiveAccount() {
        //given
        List<Account> accounts = prepareAccontData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
//        when(accountService.getAllActiveAccounts()).thenReturn(accounts);
        //mozna zapisyac powyzsza zakcje w stylu BDD
        given(accountService.getAllActiveAccounts()).willReturn(accounts);
        //when
        List<Account> allActiveAccounts = accountService.getAllActiveAccounts();
        //then
        assertThat(allActiveAccounts, hasSize(2));
    }

    @Test
    void getWithoutAccounts() {
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountService.getAllActiveAccounts()).willReturn(Collections.emptyList());
        //when
        List<Account> allActiveAccounts = accountService.getAllActiveAccounts();
        //then
        assertThat(allActiveAccounts, hasSize(0));
    }


    private List<Account> prepareAccontData() {
        Address address = new Address("Street", "666");
        Account account = new Account(address);
        Account account2 = new Account();
        Address address3 = new Address("Watykan", "2137");
        Account account3 = new Account(address);
        return Arrays.asList(account, account2, account3);
    }
}
