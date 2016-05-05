package project.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import project.core.models.entities.Account;
import project.rest.mvc.AccountController;
import project.rest.resources.AccountResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
        temp.setRid(account.getId());
        temp.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        temp.add(linkTo(methodOn(AccountController.class).findAllBlogs(account.getId())).withRel("blogs"));
        return temp;
    }
}
