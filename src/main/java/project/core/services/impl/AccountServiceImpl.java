package project.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.models.entities.Account;
import project.core.models.entities.Blog;
import project.core.repositories.AccountRepo;
import project.core.repositories.BlogEntryRepo;
import project.core.repositories.BlogRepo;
import project.core.services.AccountService;
import project.core.services.exceptions.AccountDoesNotExsistException;
import project.core.services.exceptions.AccountExsistsException;
import project.core.services.utils.AccountList;
import project.core.services.utils.BlogList;

/**
 * Created by swen on 5/2/16.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Override
    public Account findAccount(Long id) {
        return accountRepo.findAccount(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());
        if(account != null){
            throw  new AccountExsistsException();
        }
        return accountRepo.createAccount(data);
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Account account = accountRepo.findAccount(accountId);
        if(account == null){
            throw new AccountDoesNotExsistException();
        };
        Blog blog = blogRepo.createBlog(data);
        blog.setOwner(account);
        return blog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if(account == null){
            throw new AccountDoesNotExsistException();
        };
        return new BlogList(blogRepo.findBlogsByAccount(accountId));
    }
}
