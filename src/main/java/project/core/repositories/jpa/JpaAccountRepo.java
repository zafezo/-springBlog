package project.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import project.core.models.entities.Account;
import project.core.repositories.AccountRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by swen on 5/2/16.
 */
@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("select a from Account a");
        return query.getResultList();
    }

    @Override
    public Account findAccount(Long id) {
        return em.find(Account.class,id);
    }

    @Override
    public Account findAccountByName(String name) {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.name=?1");
        query.setParameter(1, name);
        List<Account> accounts = query.getResultList();
        if(accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public Account createAccount(Account data) {
        em.persist(data);
        return data;
    }
}
