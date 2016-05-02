package project.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.core.models.entities.Account;
import project.core.services.AccountService;
import project.core.services.utils.AccountList;
import project.rest.resources.AccountListResource;
import project.rest.resources.AccountResource;
import project.rest.resources.asm.AccountListResourceAsm;
import project.rest.resources.asm.AccountResourceAsm;

/**
 * Created by swen on 5/2/16.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(){
        AccountList accountList = accountService.findAllAccounts();
        return new ResponseEntity<AccountListResource>(new AccountListResourceAsm().toResource(accountList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sendedAccount){
        Account createdAccount = accountService.createAccount(sendedAccount.toAccount());
        AccountResource accountResource = new AccountResourceAsm().toResource(createdAccount);
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(accountResource.getLink());
        return new ResponseEntity<AccountResource>(accountResource,httpHeaders,HttpStatus.CREATED);
    }


}
