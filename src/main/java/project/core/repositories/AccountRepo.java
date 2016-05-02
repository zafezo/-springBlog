package project.core.repositories;

import project.core.models.entities.Account;

import java.util.List;

/**
 * Created by swen on 5/2/16.
 */
public interface AccountRepo {
    public List<Account> findAllAccounts();
    public Account findAccount(Long id);
    public Account findAccountByName(String name);
    public Account createAccount(Account data);
}
