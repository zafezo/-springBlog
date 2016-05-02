package project.core.services;

import project.core.models.entities.Account;
import project.core.models.entities.Blog;
import project.core.services.utils.AccountList;
import project.core.services.utils.BlogList;

/**
 * Created by swen on 5/2/16.
 */
public interface AccountService {
    public Account findAccount(Long id);
    public Account createAccount(Account data);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);
    public Blog createBlog(Long accountId, Blog data);
    public BlogList findBlogsByAccount(Long accountId);
}
