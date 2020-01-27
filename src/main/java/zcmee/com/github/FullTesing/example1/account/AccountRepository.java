package zcmee.com.github.FullTesing.example1.account;

import java.util.List;

public interface AccountRepository {
    List<Account> getAllAccounts();
    List<String> getByName(String name);
}
