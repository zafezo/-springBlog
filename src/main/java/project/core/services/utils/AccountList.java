package project.core.services.utils;

import project.core.models.entities.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swen on 5/2/16.
 */
public class AccountList {
    List<Account> accounts = new ArrayList<Account>();

    public AccountList(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
