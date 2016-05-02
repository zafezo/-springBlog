package project.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import project.core.services.utils.AccountList;
import project.rest.mvc.AccountController;
import project.rest.resources.AccountListResource;
import project.rest.resources.AccountResource;

import java.util.List;

/**
 * Created by swen on 5/2/16.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList,AccountListResource> {
    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resourceList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource res = new AccountListResource();
        res.setAccounts(resourceList);
        return res;
    }
}
