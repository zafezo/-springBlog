package project.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.core.models.entities.Account;
import project.core.models.entities.Blog;
import project.core.services.AccountService;
import project.core.services.exceptions.AccountDoesNotExsistException;
import project.core.services.exceptions.AccountExsistsException;
import project.core.services.exceptions.BlogExsistsException;
import project.core.services.utils.AccountList;
import project.core.services.utils.BlogList;
import project.rest.exceptions.ConflictException;
import project.rest.exceptions.NotFoundException;
import project.rest.resources.AccountListResource;
import project.rest.resources.AccountResource;
import project.rest.resources.BlogListResource;
import project.rest.resources.BlogResource;
import project.rest.resources.asm.AccountListResourceAsm;
import project.rest.resources.asm.AccountResourceAsm;
import project.rest.resources.asm.BlogListResourceAsm;
import project.rest.resources.asm.BlogResourceAsm;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value = "name", required = false)String name){
        AccountList accountList = null;
        if(name == null){
            accountList = accountService.findAllAccounts();
        }else {
            Account account = accountService.findByAccountName(name);
            if(account == null){
                accountList = new AccountList(new ArrayList<>());
            }else {
                accountList = new AccountList(Arrays.asList(account));
            }
        };
        return new ResponseEntity<AccountListResource>(new AccountListResourceAsm().toResource(accountList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sendedAccount){
        try {
            Account createdAccount = accountService.createAccount(sendedAccount.toAccount());
            AccountResource accountResource = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(accountResource.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(accountResource,httpHeaders,HttpStatus.CREATED);
        }catch (AccountExsistsException e){
            throw new ConflictException(e);
        }
    }

    @RequestMapping( value="/{accountId}",
            method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable Long accountId
    ) {
        Account account = accountService.findAccount(accountId);
        if(account != null)
        {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long accountId,
            @RequestBody BlogResource  sendedBlog
    ) {
     try {
             Blog createdBlog = accountService.createBlog(accountId,sendedBlog.toBlog());
             BlogResource res = new BlogResourceAsm().toResource(createdBlog);
             HttpHeaders httpHeaders = new HttpHeaders();
             httpHeaders.setLocation(URI.create(res.getLink("self").getHref()));
             return new ResponseEntity<BlogResource>(res,httpHeaders,HttpStatus.CREATED);
         }catch (AccountDoesNotExsistException e){
             throw new NotFoundException(e);
         }catch (BlogExsistsException e){
             throw new ConflictException(e);
         }
    }

    @RequestMapping("/{accountId}/blogs")
    public ResponseEntity<BlogListResource> findAllBlogs(
            @PathVariable Long accountId
    ){
        try {
            BlogList blogList = accountService.findBlogsByAccount(accountId);
            BlogListResource resource = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<BlogListResource>(resource,HttpStatus.OK);
        }catch (AccountDoesNotExsistException e){
            throw  new NotFoundException(e);
        }
    }



}
