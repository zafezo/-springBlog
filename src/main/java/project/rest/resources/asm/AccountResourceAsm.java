package project.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import project.core.models.entities.Account;
import project.rest.mvc.AccountController;
import project.rest.resources.AccountResource;

/**
 * Created by swen on 5/2/16.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account,AccountResource> {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource temp = new AccountResource();
        temp.setName(account.getName());
        temp.setPassword(account.getPassword());
        return temp;
    }
}
